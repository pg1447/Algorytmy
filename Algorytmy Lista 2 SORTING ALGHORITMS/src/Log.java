
public class Log extends TablicaMaker{

	public void logInsert(int tab[])
	{
		System.out.print("Pocz¹tek 	");
		for(int x: tab)
			System.out.print(" ["+x+"] ");
		System.out.println();
		int key;
		int i;
		for (int j = 1; j < tab.length; j++)
		{
			key = tab[j];
			System.out.print("Kluczem jest: "+ key + " ");
			for(i = j - 1; (i >= 0) && (tab[i] > key); i--)
			{
				setCompare();
				setReplace();
				System.out.print("Podstawiamy "+tab[i]+" pod " + tab[i+1]+ " ");
				tab[i+1] = tab[i];
			}
			setReplace();
			System.out.println();
			System.out.print("Klucz zamiast "+ tab[i+1]);
			tab[i+1] = key;
			for(int x: tab)
				System.out.print(" ["+x+"] ");
			System.out.println();
		}
		
	}
	public void logQuick(int tab[], int left, int right)
	{
		for(int i: tab )
			System.out.print("["+i+"] ");
		System.out.println();
		int v = tab[(left + right) / 2];
		System.out.println("Pivot: "+v);
			int i, j, x;
			i = left;
			j = right;
			do {
				while (tab[i] < v)
					{ setCompare(); System.out.print("Porownanie " + tab[i] +" z "+ v+ " ");i++;}
				System.out.println("Wyjscie z while tab[i]<v ");
				while (tab[j] > v)
					{ setCompare(); System.out.print("Porownanie " + tab[j] +" z "+ v+" ");j--;}
				System.out.println("Wyjscie z while tab[j]>v ");
			
				setCompare();
				setCompare();
				if (i <= j) {
					System.out.print("Zamiana " + tab[i] + " z " + tab[j]+" ");
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
			System.out.println();
			
			if (j > left)
				logQuick(tab,left, j);
				
			if (i < right)
				logQuick(tab,i, right);
		}

	public int[] tablica(int ile, int x)
	{
		
		tab = new int [ile];
		if(x==1)
		{	
			int liczba;
			for(int i=0; i<ile; i++)
			{
				liczba = r.nextInt(100)+1;
				tab[i]=liczba;
			}
		}
		else
		{
			for(int i=1;i<=ile;i++)
			{
				
				tab[ile-i]=i;
			}
		}
	
		replace =0;
		compare =0;
		return tab;
	}
	
	
}
