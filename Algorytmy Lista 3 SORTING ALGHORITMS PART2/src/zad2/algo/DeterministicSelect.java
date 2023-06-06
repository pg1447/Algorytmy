package zad2.algo;

import java.util.Random;

public class DeterministicSelect {
	private int tab[];
	private boolean permutation;
	private boolean log;
	private int nsubset;
	private int nreplace;
	private int ncompare;
	
	public DeterministicSelect(boolean p, boolean log, int nsubset){
		this.log = log;
		this.permutation = p;
		this.nsubset = nsubset;
	}
	
	protected boolean compare(int tab[], int n, int m){
		ncompare++;
		return tab[n]>tab[m];
	}
	
	protected void replace(int tab[], int n, int m){
		nreplace++;
		int temp=tab[n];
		tab[n]=tab[m];
		tab[m]=temp;
	}
	
	
	public void show(int tab[],int p,int r){
		System.out.print("["+tab[p]);
		for(int i=p+1; i<=r; ++i){
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
		return search(tab,n-1,0,tab.length-1);
	}
	
	public void insertionSort(int tab[], int p, int r){
		if(log){
			System.out.println("InsertSort in Tab:");
			show(tab, p,r );
		}
		for(int i=p; i<r; ++i)
			if(compare(tab, i, i+1)){
				replace(tab, i,i+1);
				int j=i;
				while(j>p && compare(tab, j-1,j))
					replace(tab, j-1,j--);
			}
	}
	
	public int search(int tab[], int n, int p, int r){
		
		if(r-p<nsubset){
			insertionSort(tab, p, r);
			if(log){
				System.out.println("Return the "+ tab[n]+ " middle element in tab ");
				show(tab, p,r );
			}
			return tab[n];
		}
		if(log){
			System.out.println("Creating median");
			show(tab, p,r );
		}
		int t=((r-p)/nsubset)+1;
		int temp[] = new int [t];
		int p1=p;
		for(int i=0; i<t-1; ++i){
			temp[i]=search(tab,p1+(nsubset-1)/2,p1,p1+nsubset-1);
			p1+=nsubset;
		}
		temp[t-1]=search(tab,r-(r%nsubset)/2,r-r%nsubset,r);
		
		int med = search(temp, t/2, 0, t-1);
		if(log){
			System.out.println("Median is "+ med + " in tab: ");
			show(tab, p,r );
		}
		int number;
		for(number=0; number<r; number++){
			if(tab[number]==med)
				break;
		}
		int x = partition(tab,number,p,r);
	
		if(x==n){
			if(log){
				System.out.println("Pivow = "+ tab[x] + " is searching way ");
				show(tab, p,r );
			}
			return tab[x];
		}
		if(x<n){
			if(log){
				System.out.println("Pivow is smallest "+ tab[x] + " go to right part of tab ");
				show(tab, p,r );
			}
			return search(tab,n,x+1,r);
		}
			
		else{
			if(log){
				System.out.println("Pivow is bigger "+ tab[x] + " go to left part of tab ");
				show(tab, p,r );
			}
			return search(tab,n,p,x-1);
		}
	}
	
	public int partition(int tab[], int n, int p, int r){
		replace(tab, n,r);	
		int i=p, k=r-1;
		while(i<=k){
			if(compare(tab, i,r)){
				replace(tab, i,k--);
			}else
				i++;
		}
		if(compare(tab, i,r)){
			replace(tab, i,r);
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
		DeterministicSelect x = new DeterministicSelect(true, true, 5);
		x.reset(10);
		System.out.println(x.search(5));
	}
	
}
