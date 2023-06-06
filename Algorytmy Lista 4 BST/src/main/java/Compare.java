import java.util.Random;

public class Compare {
	BST bst;
	Select select;
	RandomizedSelect rselect;
	Random rand;	
	
	public void getNumber(int n){
		for(int i=0;i<n;i++){
			select.array[i] = rand.nextInt(n)+1;
			rselect.array[i]=select.array[i];
			bst.insert(rselect.array[i]);
		}
	}
	
	public void create(int n, int position,boolean ok){
		bst = new BST();
		select = new Select(n);
		rselect = new RandomizedSelect(n);
		rand = new Random();
		getNumber(n);
		bst.os_select(position);
		bst.os_rank(position);
		select.select(position);
		rselect.rs(position);
		
	}
	public void getDecreasing(int n) {
		int range = n - 1;
		for (int i = 0; i < n; i++) {
			select.array[range] = i + 1;
			rselect.array[range]=select.array[range];
			bst.insert(rselect.array[range]);
			range--;
		}
	}
}
