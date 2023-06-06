import java.util.Random;

public class RandomizedSelect {
	int [] array;
	Random rand = new Random();
	int compare=0;
	int replace=0;
	public RandomizedSelect(int n) {
		// TODO Auto-generated constructor stub
		array = new int [n];
	}
	public void create(int n, boolean ok){
		array=null;
		array = new int [n];
		compare=0;
		if(ok==true){
			getNumber();
		}
	}
	
	public void getNumber(){
		for(int i=0;i<array.length;i++){
			array[i] = rand.nextInt(array.length)+1;
		}
	}
	public int rs(int position){
		return rs(0,array.length-1,position);
	}
	
	private int rs(int start, int end, int position){
		if(position==0)
			return -999999999;
		int r = rsp(start, end);
		int index = r-start+1;
		if(position==index)
			return array[r];
		if(index>position){
			return rs (start,r-1,position);
		}
		if(index<position){
			return rs(r+1,end,position-index);
		}
		
		return -999999999;
	}
	
	private void getRandom(int n){
		for(int i=0;i<n;i++){
			array[i]=rand.nextInt(n)+1;
		}
	}
	
	private void permutation(int n){
		for(int i=0;i<n;i++){
			array[i]=i+1;
		}
		for(int i=0;i<n;i++){
			int temp = rand.nextInt(n);
			int temp2=array[i];
			array[i]=array[temp];
			array[temp]=temp2;
		}
	}
	
	
	
	
	private int rsp(int start, int end){
		int pivotindex = rand.nextInt(end-start+1)+start;
		int pivot = array[pivotindex];
		swap(pivotindex, end);
		int i=start;
		int j=end-1;
		while (i < j) {
			
			while (array[i] < pivot) {
				compare++;
				i++;
			}
			while (j>=0 && array[j] >= pivot) {
				compare++;
				j--;
			}
			if (i < j) {
				swap(i, j);
			}
		}
		swap(i, end);
		return i;
	}
	private void show(){
		System.out.println("tablica po rsp ");
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
		System.out.println("end");
	}
	
	private void swap(int p, int q){
		int temp=array[p];
		array[p]=array[q];
		array[q]=temp;
		replace++;
		replace++;
		
	}
	
	/*public static void main (String args[]){
		RandomizedSelect test = new RandomizedSelect();
		test.create(100, true);
		test.show();
		System.out.println(test.rs(7));
		test.show();
	
	}*/
}
