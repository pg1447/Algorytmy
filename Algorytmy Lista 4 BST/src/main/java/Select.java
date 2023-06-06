import java.util.Random;

public class Select {
	public int array[];
	int compare = 0;
	
	public Select(int n) {
		array = new int [n];
	}
	
	public void create(int n, boolean ok){
		array = new int [n];
		compare=0;
		if(ok==true){
			getNumber();
		}
		else{
			permutatnion();
		}
	}
	public int select(int position){
		return select(array, 0, array.length-1, position);
	}
	private int select(int[] arr, int start, int end, int position) {
		int m = partitionArray(arr, start, end);
		int index = m - start + 1;
		if (position == index)
			return arr[m];
		if (index > position)
			return select(arr, start, m - 1, position);
		
		if (index < position)
			return select(arr, m + 1, end, position - index);
		
		return -99999;
	}

	public void getNumber() {
		Random rand = new Random();
		for (int i = 0; i < array.length; i++) {
			array[i] = rand.nextInt(array.length) + 1;
		}
	}
	

	
	public void permutatnion(){
		Random r = new Random();
		int n = array.length;
		for(int i=0; i<n; i++){
				array[i]=n-1-i;
		}
		int k = n;
		while(k>0){
			int l=r.nextInt(k--);
			int temp=array[k];
			array[k]=array[l];
			array[l]=temp;
		}
	}

	public int partitionArray(int[] arr, int start, int end) {
		int pivot = median(arr, start, end);
		int index = foundindex(arr, start, end, pivot);
		pivot = arr[index];
		swap(arr, index, end);
		int i = start;
		int j = end - 1;
		while (i < j) {
			
			while (arr[i] < pivot) {
				compare++;
				i++;
			}
			while (j>=0 && arr[j] >= pivot) {
				compare++;
				j--;
			}
			if (i < j) {
				swap(arr, i, j);
			}
		}
		swap(arr, i, end);
		return i;

	}

	private int foundindex(int tab[], int start, int end, int pivot) {
		int i;
		for (i = start; i <= end && tab[i] != pivot; i++)
			;
		return i;
	}

	public int median(int[] arr, int start, int end) {
		if (end - start < 5) {
			return partition5(arr, start, end);
		}
		int k = ((end - start) / 5) + 1;
		int[] temp = new int[k];
		int counter = 0;
		int s = start;
		for (int i = start; i <= end; i += 5) {
			int border = i + 4;
			if (border > end)
				border = end;
			temp[counter] = partition5(arr, i, border);
			counter++;
		}
		return median(temp, 0, counter-2);
	}

	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public void show(int arr[], int start, int end) {
		System.out.print("tab321 ");
		for (int i = start; i <= end; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println("");
	}

	public int partition5(int arr[], int start, int end) {
		if (start == end)
			return arr[start];
		for (int i = start + 1; i <= end; i++) {
			int key = arr[i];
			int j = i - 1;
			while (j >= start && arr[j] > key) {
				compare++;
				swap(arr, j + 1, j);
				j--;
			}
			arr[j + 1] = key;
		}
		int k = ( (start + end) / 2);
		return arr[k];
	}
	
	public void sort(int[] arr) {
		sort(arr, 0, arr.length - 1);
	}

	private void sort(int[] arr, int left, int right) {
		int v = arr[(left + right) / 2];
		int i, j, x;
		i = left;
		j = right;
		do {
			while (arr[i] < v) {
				i++;
			}
			while (arr[j] > v) {
				j--;
			}
			if (i <= j) {
				swap(arr, i, j);
				i++;
				j--;
			}
		} while (i <= j);
		if (j > left)
			sort(arr, left, j);
		if (i < right)
			sort(arr, i, right);
	}
	
	public void show(int[] arr) {
		System.out.println("");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println("");
	}
}
