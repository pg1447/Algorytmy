
public class InsertSort extends TablicaMaker{
	
	
	
	InsertSort()
	{
		name="InsertSort";
	}
	
	public void sort(int left, int right){
		int key;
		int i;
		for (int j = 1; j < tab.length; j++)
		{
			key = tab[j];
			for(i = j - 1; (i >= 0) && (tab[i] > key); i--)
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
