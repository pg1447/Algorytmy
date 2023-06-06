import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Chart {
	JTextField text;
	JCheckBox tab;
	JButton start;

	int n = 0;
	boolean ok = true;

	public Chart() {
		JFrame frame = new JFrame();
		frame.setSize(200, 200);
		frame.setLayout(null);
		frame.setTitle("Sort");
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		text = new JTextField();
		text.setHorizontalAlignment(JTextField.CENTER);
		text.setBounds(10, 10, 160, 40);
		frame.add(text);

		tab = new JCheckBox("Permutacja");
		tab.setBounds(10, 60, 150, 20);
		tab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tab.isSelected()) {
					ok = false;
				} else {
					ok = true;
				}
			}
		});
		frame.add(tab);
		start = new JButton("GENERUJ");
		start.setBounds(10, 100, 150, 20);
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					n = Integer.parseInt(text.getText());
					if (n > 100000) {
						text.setText("Podano zle dane");
					} else {
						text.setText(Integer.toString(n));
						createPlot();
					}
				} catch (NumberFormatException ex) {
					text.setText("Error");
				}

			}
		});
		frame.add(start);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private XYSeries[] createSeries() {
		XYSeries tab[] = new XYSeries[4];
		tab[0] = new XYSeries("BST os_select");
		tab[1] = new XYSeries("BST os_rank");
		tab[2] = new XYSeries("Select avg");
		tab[3] = new XYSeries("R_Select avg");
		Compare statistic = new Compare();
		Random r = new Random();
		for (int i = 1; i <= n; i += 50) {
			int os_select = 0, os_rank = 0, select = 0, rselect = 0;
			for (int j = 1; j <= 100; j++) {
				int pos = r.nextInt(i)+1;
				statistic.create(i, pos, this.ok);
				os_select += statistic.bst.compareS;
				os_rank += statistic.bst.compareR;
				select += statistic.select.compare;
				rselect += statistic.rselect.compare;
			}
			tab[0].add(i, os_select / 100);
			tab[1].add(i, os_rank / 100);
			tab[2].add(i, select / 100);
			tab[3].add(i, rselect / 100);
		}

		return tab;
	}

	private void createPlot() {
		XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
		XYSeries[] sort = createSeries();
		for (int i = 0; i < sort.length; i++)
			xySeriesCollection.addSeries(sort[i]);

		XYDataset xyDataset = xySeriesCollection;

		JFreeChart lineGraph = ChartFactory.createXYLineChart("Statystyki", "n", "Ilosc porownan", xyDataset,
				PlotOrientation.VERTICAL, true, true, false);
		ChartFrame frame1 = new ChartFrame("Porownania", lineGraph);
		Color[] color = { Color.BLACK, Color.RED, Color.BLUE, Color.GREEN};
		XYPlot plot = (XYPlot) lineGraph.getPlot();
		XYItemRenderer renderer = plot.getRenderer();
		for (int i = 0; i < color.length; i++)
			renderer.setSeriesPaint(i, color[i]);

		frame1.pack();
		frame1.setVisible(true);
	}

	public static void main(String args[]) {
		new Chart();

	}
}
