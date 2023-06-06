
public class MergeInsert  extends TablicaMaker{
	
	MergeInsert()
	{
		name="MergeInsert";
	}
	


	public void sort(int left, int right) {
		  if (right-left>8){
		    int q = (left+right)/2;
			sort(left, q);
		    sort(q+1, right);
		    merge(left, q+1, right);
	      	  }
		  else
		  {
			  insert(left,right);
		  }
		}
	  
	    public void merge(int p, int q, int r) {
	        int result[] = new int[r-p+1];
	        int i = p, j = q, ri = 0;
	        while( i<q && j<=r ){
	        	setCompare();
	        	if(tab[i]<= tab[j]){
	        		result[ri] = tab[i];
	        		setReplace();
	        		i++;
	        	}
	        	else{
	        		result[ri] = tab[j];
	        		setReplace();
	        		j++;
	        	}
	        	ri++;
	        }
	       
	        while(i<q){
	        	result[ri] = tab[i];
	        	setReplace();
	        	ri++;
	        	i++;
	        }
	       
	        while(j<=r){
	        	result[ri] = tab[j];
	        	setReplace();
	        	ri++;
	        	j++;
	        }
	        
	        for(int k=p, l=0 ; k<=r ; k++, l++){
	        	setReplace();
	        	tab[k] = result[l];	
	        }
	        
	    }
	    public void insert(int left, int right){
			int key;
			int i;
			for (int j = left+1; j <= right; j++)
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
