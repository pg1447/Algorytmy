
public class BST {
	public Node root = null;
	public int compareS = 0;
	public int compareR = 0;

	public int insert(int value) {
		if (root == null) {
			root = new Node(value);
			return root.value;
		}
		Node x = root;
		Node y = null;
		while (x != null) {
			x.size++;
			y = x;
			if (x.value > value){
				x = x.left;
			}
			else{
				x=x.right;
			}
		}
		x = new Node(value);
		if (x.value > y.value)
			y.right = x;
		else
			y.left = x;
		x.parent = y;
		return x.value;
	}

	public int min(Node root) {
		if (root == null) {
			System.out.println("");
			return 0;
		}
		Node x = root;
		while (x.left != null)
			x = x.left;
		System.out.println(x.value);
		return x.value;
	}
	
	private int mind(Node root) {
		if (root == null) {
			System.out.println("");
			return 0;
		}
		Node x = root;
		while (x.left != null)
			x = x.left;
		System.out.println(x.value);
		return x.value;
	}

	public int max(Node root) {
		if (root == null) {
			System.out.println("");
			return 0;
		}
		Node x = root;
		while (x.right != null)
			x = x.right;
		System.out.println(x.value);
		return x.value;
	}

	public int find(int value) {
		if (root == null) {
			System.out.println("");
			return 0;
		}
		Node x = root;
		while (x.value != value) {
			
			if (x.value > value)
				x = x.left;
			else
				x = x.right;
			if (x==null){
				return 0;
			}
		}
		return 1;
	}

	private Node search(int value) {
		if (root == null)
			return null;
		Node x = root;
		while (x.value != value) {
			if (x.value > value)
				x = x.left;
			else if (x.value < value)
				x = x.right;
			if(x==null)
				return null;
		}
		return x;
	}

	public int succerssor(int value) {
		return succerssor(search(value));
	}

	private int succerssor(Node x) {
		if (root == null) {
			System.out.println("");
			return 0;
		}
		if (x.right != null)
			return min(x.right);
		Node parent = x.parent;
		while (parent.right == x) {
			if (parent.parent == null)
				return 0;
			x = parent;
			parent = parent.parent;
		}
		return parent.value;
	}

	public int predecessor(int value) {
		return predecessor(search(value));
	}

	private int predecessor(Node x) {
		if (root == null) {
			System.out.println("");
			return 0;
		}
		if (x.left != null)
			return max(x.left);
		Node parent = x.parent;
		while (parent.left == x) {
			if (parent.parent == null)
				return 0;
			x = parent;
			parent = parent.parent;
		}
		return parent.value;
	}
	
	public int os_select(int i){
		return os_select(root, i);
			
	}
	

	public int os_select(Node x, int i){
		int k;
		if(x==null){
			return -1;
		}
		if(x.left==null)
			k=1;
		else
			k=x.left.size+1;
		if(i==k){
			compareS++;
			return x.value;
		}
		if(i<k){
			compareS++;
			return os_select(x.left, i);
		}
		compareS++;
		return os_select(x.right, i-k);
		
	}
	
	/*private int os_rank(int valueee){
		return os_rank(search(value));
		
	}*/
	public void os_rank(int value){
		Node node = search(value);
		if(node!=null){
			int rank;
			if(node.left==null)
				rank=1;
			else	
				rank=node.left.size+1;
			Node x=node;
			compareR++;
			while(x!=root){
				compareR++;
				if(x==x.parent.right){
					if(x.parent.left!=null)
						rank+=x.parent.left.size+1;
					else
						rank+=1;
				}
				x=x.parent;
			}
		}
		
	}

	public void inorder() {
		inorder(root);
		System.out.println("");
	}

	private void inorder(Node root) {
		if (root != null) {
			inorder(root.left);
			System.out.print(root.value+" ");
			inorder(root.right);
		}
	}

	public void delete(int key) {
		root = delete(root, key);
	}

	private Node delete(Node root, int key) {
		if (root == null)
			return root;
		root.size--;
		if (key < root.value){
			
			root.left = delete(root.left, key);
		}
			
		else if (key > root.value){
			root.right = delete(root.right, key);
		}
			
		else {
			if (root.left == null)
				return root.right;
			else if (root.right == null)
				return root.left;
			root.value = mind(root.right);
			root.right = delete(root.right, root.value);
		}
		return root;
	}

	int minValue(Node root) {
		int minv = root.value;
		while (root.left != null) {
			minv = root.left.value;
			root = root.left;
		}
		return minv;
	}
	public static void main (String args[]){
		BST test = new BST();
		test.insert(50);
		test.insert(17);
		test.insert(72);
		test.insert(12);
		test.insert(23);
		test.insert(9);
		test.insert(14);
		test.insert(19);
		test.insert(54);
		test.insert(76);
		test.insert(67);
		System.out.println(test.root.left.size);
	}
}
