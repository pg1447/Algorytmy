package zad1.sort;
import java.util.Random;


public class RadixSort {
	private int tab[];
	private boolean worst;
	private boolean log;
	private int numbersys;
	private int nreplace;
	public RadixSort(boolean p, int s, boolean log){
		this.log = log;
		worst = p;
		numbersys=s;
	}
	public void reset(int n){
		tab=null;
		tab = new int[n];
		nreplace=0;
		if(worst)
			for(int i=0; i<n;++i)
				tab[i]=n-i-1;
		else{
			Random gen = new Random();
			for(int i=0; i<n; ++i)
				tab[i]=gen.nextInt(n);
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
		if(log)
			show();
		for(int i=1; i<tab.length;i*=numbersys ){
			count_sort(i);
			if(log)
				show();
			
		}
	}
	void count_sort(int number){
		int temp[] = new int[numbersys];
		for(int i=0; i<tab.length; ++i ){
			temp[(tab[i]/number)%numbersys]++;
			nreplace++;
		}
		for(int i=1; i<temp.length; ++i){
			temp[i]+=temp[i-1];
			nreplace++;
		}
		int out[] = new int[tab.length];
		for(int i=tab.length-1; i>=0; --i){
			int x = (tab[i]/number)%numbersys;
			out[temp[x]-1] = tab[i];
			temp[x]--;
			nreplace++;
		}
		tab = out;
	}
	
	public int getReplace(){
		return nreplace;
	}
	
	
	
	public static void main(String argv[]){
		RadixSort x = new RadixSort(true, 10, true);
		x.reset(100);
		x.sort();
	}
	
}
