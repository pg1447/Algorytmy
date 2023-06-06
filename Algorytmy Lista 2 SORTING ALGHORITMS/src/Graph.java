import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;



public class Graph extends JFrame implements ActionListener{

	JTextField text;
	JCheckBox insert, quick, merge, worst, insquick, insmerg;
	JButton start;
	int choice =1;
	
	Graph()
	{
		JFrame frame = new JFrame();
		frame.setSize(200, 280);
		frame.setLayout(null);
		frame.setTitle("Sort");
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		text = new JTextField();
		text.setHorizontalAlignment(JTextField.CENTER);
		text.setBounds(10, 10, 100, 40);
		frame.add(text);
		
		insert = new JCheckBox("INSERTION SORT");
		insert.setBounds(10,60,150,20);
		frame.add(insert);
		
		quick = new JCheckBox("QUICK SORT");
		quick.setBounds(10,80,150,20);
		frame.add(quick);
		
		merge = new JCheckBox("MERGE SORT");
		merge.setBounds(10,100,150,20);
		frame.add(merge);
		
		worst = new JCheckBox("TABLICA MALEJACA");
		worst.setBounds(10,120,150,20);
		frame.add(worst);
		
		insquick = new JCheckBox("INSERT QUICK");
		insquick.setBounds(10,140,150,20);
		frame.add(insquick);
		
		insmerg = new JCheckBox("INSERT MERG");
		insmerg.setBounds(10,160,150,20);
		frame.add(insmerg);
		
		start = new JButton("GENERUJ");
		start.setBounds(10,190,150,20);
		start.addActionListener(this);
		frame.add(start);
		
		
		frame.setVisible(true);
	}
	
	public XYSeries[] generateSort(TablicaMaker s, int n){
		if(n<0||n>10000){
			System.out.println("N poza zakresem"); 
			return null;
		}
		
	
		XYSeries tab[] = new XYSeries[2];
		tab[0] = new XYSeries(s.getName() + " compare");
		tab[1] = new XYSeries(s.getName() + " replace");
		for(int i=0; i<=n; i+=1){
	
			int c=0,r=0;
			for(int j=0;j<10;++j)
			{
				
					if(i==0)
						break;
					s.reset(i,choice);
					s.sort(0,i-1);
					r+=s.getReplace();
					c+=s.getCompare();

			}
			
			tab[0].add(i, c/10);
			tab[1].add(i, r/10);
			
		}
		
		return tab;
	}
	public static void main(String[] args) {
		
		Graph testowy = new Graph();
		//Log a = new Log();
		//a.logInsert(a.tablica(6,1));
		//a.logQuick(a.tablica(8,1), 0, 7);
		
		
	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		int liczba=0;
		Object e = arg0.getSource();
		
		if(e==start)
		{
			
			
			try {liczba=Integer.parseInt(text.getText().toString());}
			catch(NumberFormatException a)
			{
				text.setText("Brak liczby");
			}
			
			XYSeriesCollection compare = new XYSeriesCollection();
			XYSeriesCollection replace = new XYSeriesCollection();
			
			if(insert.isSelected())
			{
				
				if(worst.isSelected())
				{
					choice=0;
					TablicaMaker test  = new InsertSort();
					XYSeries tab[] = this.generateSort(test, liczba);
					compare.addSeries(tab[0]);
					replace.addSeries(tab[1]);
					System.out.println("INSERT Przestawainie " + test.getReplace()+ " Porownanie "+ test.getCompare());
				}
				else
				{
					choice=1;
					InsertSort test  = new InsertSort();
					XYSeries tab[] = this.generateSort(test, liczba);
					compare.addSeries(tab[0]);
					replace.addSeries(tab[1]);
					System.out.println("INSERT Przestawainie " + test.getReplace() + " Porownanie "+ test.getCompare());
				}
			}
			if(quick.isSelected())
			{
				if(worst.isSelected())
				{
					choice=0;
					QuickSort test  = new QuickSort();
					XYSeries tab[] = this.generateSort(test, liczba);
					compare.addSeries(tab[0]);
					replace.addSeries(tab[1]);
					System.out.println("QUICK Przestawainie " + test.getReplace() + " Porownanie "+ test.getCompare());
					
				}
				else
				{
					choice=1;
					QuickSort test  = new QuickSort();
					XYSeries tab[] = this.generateSort(test, liczba);
					compare.addSeries(tab[0]);
					replace.addSeries(tab[1]);
					System.out.println("QUICK Przestawainie " + test.getReplace() + " Porownanie "+ test.getCompare());
					
				}
			}
			if(merge.isSelected())
			{
				if(worst.isSelected())
				{
					choice=0;
					MergeSort test  = new MergeSort();
					XYSeries tab[] = this.generateSort(test, liczba);
					compare.addSeries(tab[0]);
					replace.addSeries(tab[1]);
					System.out.println("MERGE Przestawainie " + test.getReplace() + " Porownanie "+ test.getCompare());
					
				}
				else
				{
					choice=1;
					MergeSort test  = new MergeSort();
					XYSeries tab[] = this.generateSort(test, liczba);
					compare.addSeries(tab[0]);
					replace.addSeries(tab[1]);
					System.out.println("MERGE Przestawainie " + test.getReplace() + " Porownanie "+ test.getCompare());
					
				}
			}
			if(insmerg.isSelected())
			{
				if(worst.isSelected())
				{
					choice=0;
					MergeInsert test  = new MergeInsert();
					XYSeries tab[] = this.generateSort(test, liczba);
					compare.addSeries(tab[0]);
					replace.addSeries(tab[1]);
					System.out.println("INSERT-MERGE Przestawainie " + test.getReplace() + " Porownanie "+ test.getCompare());
					
				}
				else
				{
					choice=1;
					MergeInsert test  = new MergeInsert();
					XYSeries tab[] = this.generateSort(test, liczba);
					compare.addSeries(tab[0]);
					replace.addSeries(tab[1]);
					System.out.println("INSERT-MERGE Przestawainie " + test.getReplace() + " Porownanie "+ test.getCompare());
					
				}
			}
			
			if(insquick.isSelected())
			{
				if(worst.isSelected())
				{
					choice=0;
					QucikInsert test  = new QucikInsert();
					XYSeries tab[] = this.generateSort(test, liczba);
					compare.addSeries(tab[0]);
					replace.addSeries(tab[1]);
					System.out.println("INSERT-QUICK Przestawainie " + test.getReplace() + " Porownanie "+ test.getCompare());
					
				}
				else
				{
					choice=1;
					QucikInsert test  = new QucikInsert();
					XYSeries tab[] = this.generateSort(test, liczba);
					compare.addSeries(tab[0]);
					replace.addSeries(tab[1]);
					System.out.println("INSERT-QUICK Przestawainie " + test.getReplace() + " Porownanie "+ test.getCompare());
					
				}
			}
				JFreeChart chart1 = ChartFactory.createXYLineChart("Porównania", "n", "kroki", compare, PlotOrientation.VERTICAL, true, true, false);
			    ChartFrame frame1 = new ChartFrame("Porównania", chart1); 
			    frame1.pack(); 
			    frame1.setVisible(true);
			    
			    JFreeChart chart2 = ChartFactory.createXYLineChart("Zamiany", "n", "kroki", replace, PlotOrientation.VERTICAL, true, true, false);
			    ChartFrame frame2 = new ChartFrame("Zamiany", chart2); 
			    frame2.pack(); 
			    frame2.setVisible(true);
		}
		
	}

}
