package zad1.sort;
import java.util.Random;


public class RadixSort2 {
	private int tab[];
	private boolean worst;
	private boolean log;
	private int range;
	private int numbersys;
	private int genmask;
	private int nreplace;
	public RadixSort2(boolean p, boolean log){
		this.log = log;
		worst = p;
	}
	public void reset(int n, int range){
		this.range = range;
		tab = null;
		tab = new int[n];
		nreplace=0;
		if(worst)
			for(int i=0; i<n;++i)
				tab[i]=(range*(n-i-1))/n;
		else{
			Random gen = new Random();
			for(int i=0; i<n; ++i)
				tab[i]=gen.nextInt(range);
		}
		
	}
	public void show(){
		System.out.print("["+tab[0]);
		for(int i=1; i<tab.length; ++i){
			System.out.print(", "+tab[i]);
		}
		System.out.println("]");
	}
	public void sort(){

		numbersys = log2(tab.length);
		int mask = 1, bits = log2(range);
		mask <<= numbersys;
		genmask=mask;
		if(log){
			show();
			System.out.println("Maska "+mask);
		}
		mask--;

		int numOfRounds = (int)(bits/numbersys)+1;
		for(int i=0 ; i<numOfRounds ; i++){
			count_sort(i, mask);
			mask <<= numbersys;
			if(log)
				show();
		}
	}
	void count_sort(int i, int mask){
		int temp[] = new int[genmask];
		for(int j=0; j<tab.length; ++j ){
			temp[((tab[j] & mask) >> (numbersys * i))]++; 
			nreplace++;
		}
		
		for(int j=1; j<temp.length; ++j){
			temp[j]+=temp[j-1];
		}
		
		int out[] = new int[tab.length];
		for(int j = tab.length-1 ; j >= 0 ; j--){ 
			out[--temp[((tab[j] & mask) >> (numbersys * i))]] = tab[j];   
			nreplace++;
		}
		tab = out;
	}
	
	public int getReplace(){
		return nreplace;
	}
	
	public int log2(int x ){
		return (int) (Math.log(x)/Math.log(2));
	}
	
	public static void main(String argv[]){
		RadixSort2 x = new RadixSort2(false, true);
		x.reset(10,20);
		x.sort();
		System.out.println(x.getReplace());
	}
	
}
