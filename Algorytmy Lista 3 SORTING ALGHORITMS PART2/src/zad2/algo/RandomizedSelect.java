package zad2.algo;

import java.util.Random;

public class RandomizedSelect {
	private int tab[];
	private boolean permutation;
	private boolean log;
	private int nreplace;
	private int ncompare;
	
	public RandomizedSelect(boolean p, boolean log){
		this.log = log;
		permutation = p;
	}
	
	protected boolean compare(int n, int m){
		ncompare++;
		return tab[n]>tab[m];
	}
	
	protected void replace(int n, int m){
		nreplace++;
		int temp=tab[n];
		tab[n]=tab[m];
		tab[m]=temp;
	}
	
	
	public void show(){
		System.out.print("["+tab[0]);
		for(int i=1; i<tab.length; ++i){
			System.out.print(", "+tab[i]);
		}
		System.out.println("]");
	}
	
	public void show(int p, int q){
		System.out.print("["+tab[p]);
		for(int i=p+1; i<=q; ++i){
			System.out.print(", "+tab[i]);
		}
		System.out.println("]");
	}
	
	public void reset(int n){
		nreplace = 0;
		ncompare = 0;
		tab = null;
		tab = new int[n];
		Random g = new Random();
		for(int i=0; i<n; i++){
			if(permutation)
				tab[i]=n-1-i;
			else
				tab[i]=g.nextInt(n);
		}
		if(permutation){
			int k = n;
			while(k>0){
				int l=g.nextInt(k--);
				int temp=tab[k];
				tab[k]=tab[l];
				tab[l]=temp;
			}
		}
	}
	public int search(int n){
		if(log)
			show();
		return search(n-1,0,tab.length-1);
	}
	
	public int search(int n, int p, int r){

		int x = partition(p,r);

		if(x==n){
			if(log){
				System.out.println("znaleziono n element " + x);
				show();
			}	
			return tab[x];
		}
		if(x<n){
			if(log){
				System.out.println("szukany element jest wiekszy od " + x);
				show(x+1,r);
			}
			return search(n,x+1,r);
		}
		else{
			if(log){
				System.out.println("szukany element jest mniejszy od " + x);
				show(p,x-1);
			}
			return search(n,p,x-1);
		}
	}
	
	public int partition(int p, int r){
		Random g = new Random();
		int rand = g.nextInt(r-p+1);
		rand = p+rand;
		replace(rand,r);
		
		int i=p, k=r-1;
		while(i<=k){
			if(compare(i,r)){
				replace(i,k--);
			}else
				i++;
		}
		if(compare(i,r)){
			replace(i,r);
			return i;
			
		}
		return r;
	}
	public int getReplace(){
		return nreplace;
	}
	public int getCompare(){
		return ncompare;
	}
	public static void main(String args[]){
		RandomizedSelect x = new RandomizedSelect(true, true);		
		x.reset(10);
		x.search(5);
	}
	
}
