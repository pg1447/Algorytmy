import java.util.Random;

public abstract class TablicaMaker {

		Random r = new Random();
		protected String name;
		protected int replace=0, compare=0;
		public int tab [];
		
		
		public void reset(int ile, int x)
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
		}
		
		public String getName() {
			return name;
		}
		public int getReplace() {
			return replace;
		}
		public void setReplace() {
			replace++;
		}
		public int getCompare() {
			return compare;
		}
		public void setCompare() {
			compare++;
		}

		public void sort(int l, int r){
			// TODO Auto-generated method stub
		}
	

		
		 
}
