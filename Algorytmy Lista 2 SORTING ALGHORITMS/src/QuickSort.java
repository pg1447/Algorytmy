
public class QuickSort extends TablicaMaker {

	
	QuickSort() {
		name="QuickSort";	
	}

	public void sort(int left, int right) {

			
		int v = tab[(left + right) / 2];
		
		int i, j, x;
		i = left;
		j = right;
		do {
			while (tab[i] < v)
				{i++; setCompare();}
			while (tab[j] > v)
				{j--; setCompare();}
			setCompare();
			setCompare();
			if (i <= j) {
				x = tab[i];
				tab[i] = tab[j];
				tab[j] = x;
				setReplace();
				setReplace();
				setReplace();
				i++;
				j--;
			}
		} while (i <= j);
		if (j > left)
			sort(left, j);
		if (i < right)
			sort(i, right);
	}
	
}
