package zad2.graph;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

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

import zad2.algo.DeterministicSelect;
import zad2.algo.RandomizedSelect;


public class Graph implements ActionListener {
	JCheckBox  worst;
	JTextArea text;
	
	Graph(){
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(20,40,200,250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		
		text  = new JTextArea();
		text.setBounds(0,20,50,20);
		
		JLabel infotext = new JLabel("Podaj wielkosc tablicy ");
		infotext.setBounds(0,0,150,20);
		
		worst = new JCheckBox  ("Permutacja?");
		worst.setBounds(0,40,200,20);
		
		JButton gene = new JButton("Generuj");
		gene.setBounds(0,120,200,40);
		
		gene.addActionListener(this);
		frame.add(gene);
		frame.add(worst);
		frame.add(infotext);
		frame.add(text);
		frame.repaint();
	}
	
	
	public XYSeries[] generate(int n, boolean wrost, int step){
		
		XYSeries tab[] = new XYSeries[6];
		
		tab[0] = new XYSeries("Random Select min");
		tab[1] = new XYSeries("Random Select max");
		tab[2] = new XYSeries("Random Select average");
		tab[3] = new XYSeries("Deterministic Select min");
		tab[4] = new XYSeries("Deterministic Select max");
		tab[5] = new XYSeries("Deterministic Select average");
		
		RandomizedSelect ran = new RandomizedSelect(wrost,false);
		DeterministicSelect sel = new DeterministicSelect(wrost,false,5);
		
		for(int i=1; i<=n; i+=step){
			int temp[]= new int[6];
			temp[0]=Integer.MAX_VALUE;
			temp[1]=-1;
			temp[2]=0;
			temp[3]=Integer.MAX_VALUE;
			temp[4]=-1;
			temp[5]=0;
			for(int j=0;j<100;++j){
				Random gen = new Random();
				ran.reset(i);
				int x=gen.nextInt(i)+1;
				ran.search(x);
				if(temp[0] > ran.getCompare())
					temp[0]=ran.getCompare();
				if(temp[1] < ran.getCompare())
					temp[1]=ran.getCompare();
				temp[2]+=ran.getCompare();
				
				sel.reset(i);
				sel.search(x);
				if(temp[3] > sel.getCompare())
					temp[3]=sel.getCompare();
				if(temp[4] < sel.getCompare())
					temp[4]=sel.getCompare();
				temp[5]+=sel.getCompare();
			}
			for(int k=0; k<6; ++k){
				if(k==2||k==5)
					tab[k].add(i,temp[k]/100);
				else
					tab[k].add(i,temp[k]);
			}
		}
		return tab;
	}
	
	
	 public static void main(String[] args) throws Exception {
		new Graph();
	 }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		boolean x=false;
		if(worst.isSelected())
			x=true;
		int n = Integer.parseInt(text.getText());
		XYSeriesCollection compare = new XYSeriesCollection();
		
		XYSeries tab[] = generate(n,x,100);
		for(int i=0; i<6;++i)
			compare.addSeries(tab[i]);
		
	    JFreeChart chart1 = ChartFactory.createXYLineChart("Compare", "n", "steps", compare, PlotOrientation.VERTICAL, true, true, false);
	    ChartFrame frame1 = new ChartFrame("Compare", chart1); 
	    frame1.pack(); 
	    frame1.setVisible(true);
	}
}