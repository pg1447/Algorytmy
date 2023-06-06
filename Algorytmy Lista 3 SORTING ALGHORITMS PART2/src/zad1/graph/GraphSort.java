package zad1.graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import zad1.sort.RadixSort2;


public class GraphSort implements ActionListener {
	JCheckBox  worst;
	JTextArea text;
	JTextArea range;
	
	GraphSort(){
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(20,40,200,250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		JLabel info = new JLabel("System liczb ");
		info.setBounds(0,60,150,20);
		
		range = new JTextArea();
		range.setBounds(0,80,50,20);
		
		text  = new JTextArea();
		text.setBounds(0,20,50,20);
		
		JLabel infotext = new JLabel("Podaj wielkosc tablicy ");
		infotext.setBounds(0,0,150,20);
		
		worst = new JCheckBox  ("Posortowane malejaco?");
		worst.setBounds(0,40,200,20);
		
			
		JButton gene = new JButton("Generuj");
		gene.setBounds(0,120,200,40);
		
		gene.addActionListener(this);
		frame.add(gene);
		frame.add(worst);
		frame.add(infotext);
		frame.add(text);
		frame.add(range);
		frame.add(info);
		frame.repaint();
		
	}
	
	
	public XYSeries generateSort(RadixSort2 s, int n, int range, int step){
		XYSeries ser = new XYSeries("radixsort replace");
		for(int i=10; i<=n; i+=step){
//			long start=System.currentTimeMillis();
			int r=0;
			//for(int j=0;j<100;++j){
				s.reset(i,range);
				s.sort();
				r+=s.getReplace();
		//	}
			ser.add(i, r);
//			long stop=System.currentTimeMillis();
//			System.out.println("Czas wykonania "+i+" elementowego sortowania "+(stop-start));
		}
		return ser;
	}
	
	 public static void main(String[] args) throws Exception {
		new GraphSort();
	 }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		boolean x=false;
		if(worst.isSelected())
			x=true;
		int n = Integer.parseInt(text.getText());
		int ranges = Integer.parseInt(range.getText());
		XYSeriesCollection replace = new XYSeriesCollection();
		XYSeries tab = this.generateSort(new RadixSort2(x, false), n, ranges, 1);
		replace.addSeries(tab);
	    JFreeChart chart2 = ChartFactory.createXYLineChart("Replace", "n", "steps", replace, PlotOrientation.VERTICAL, true, true, false);
	    ChartFrame frame2 = new ChartFrame("Replace", chart2); 
	    frame2.pack(); 
	    frame2.setVisible(true);
	}
}