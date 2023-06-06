import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;


public class Main {
	/*private static Scanner skaner;

	public static void read(BST node, String s) throws FileNotFoundException {

		String[] str = s.split(" ");
		int n;
			switch (str[0]) {
			case "insert":
				n = Integer.parseInt(str[1]);
				node.insert(n);
				break;
			case "delete":
				n = Integer.parseInt(str[1]);
				node.delete(n);
				break;
			case "find":
				n = Integer.parseInt(str[1]);
				System.out.println(node.find(n));
				break;
			case "min":
				node.min(node.root);
				break;
			case "max":
				node.max(node.root);
				break;
			case "inorder":
				node.inorder();
				break;
			default:
				System.out.println("Wrong argument");
				break;
			}
		} 
	*/
	public static void main (String args[]){
		BST bst = new BST();
		bst.insert(7);
		bst.insert(12);
		bst.insert(9);
		bst.insert(10);
		bst.insert(8);
		bst.insert(15);
		bst.insert(17);
		bst.insert(14);
		bst.insert(5);
		bst.insert(6);
		bst.insert(3);
		bst.insert(4);
		bst.insert(1);
		bst.inorder();
	/*	System.out.println(bst.root.size);
	/*	try {
			System.out.println(bst.os_select(19));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}*/
		/*try {
			System.out.println(bst.os_rank(17));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		*/

		/*File plik = new File("read.txt");
		PrintStream os = null;
		try {
			os = new PrintStream("siema.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.setOut(os);
		try {
			skaner = new Scanner(plik);
			int counter = Integer.parseInt(skaner.nextLine());
			while (counter > 0) {
				read(bst, skaner.nextLine());
				counter--;
			}
		} catch (FileNotFoundException ex) {
			System.out.println("ERROR");
		}
		*/
	}
}
