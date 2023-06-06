
public class QucikInsert extends TablicaMaker{
	
	QucikInsert()
	{
		name="QuickInsert";
	}
	
	public void sort(int left, int right) {

		if(right-left<8)
		{
			insert(left,right);
		}
			
		else{
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
			sort(i, right);}
	}
	public void insert(int left, int right){
		int key;
		int i;
		for (int j = left+1; j <=right; j++)
		{
			key = tab[j];
			for(i = j - 1; (i >= left) && (tab[i] > key); i--)
			{
				setCompare();
				setReplace();
				tab[i+1] = tab[i];
			}
			setReplace();
			tab[i+1] = key;
		}
		
	}

}
