
public class Node {
	public Node right, left, parent;
	public int value, size;
	public Node(int value) {
		right = null;
		left = null;
		parent = null;
		size = 1;
		this.value = value;
	}
}
