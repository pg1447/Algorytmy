
public class MergeSort extends TablicaMaker{
	

	MergeSort()
	{
		name="MergeSort";
	}

	public void sort(int left, int right) {
		  if (left<right){
		    int q = (left+right)/2;
			sort(left, q);
		    sort(q+1, right);
		    merge(left, q+1, right);
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
}
