import java.util.List;
import java.util.ArrayList;

public class FindSubTree {

	public static void main(String[] args) {
		Utils utils = new Utils();
		TreeNode tree = utils.createTree123();
		System.out.print(tree);
		
		ArrayList<Integer> array = new ArrayList<>();
		for ( int i = 0; i < 7; ++i ){
			array.add(i);
		}
		TreeNode tree2 = utils.createTree( array );
		System.out.print(tree2);
		System.out.println( tree2.isSubTree(tree) );
	}	
}

class Utils{

	public TreeNode createTree( List<Integer> list ){
		TreeNode treeNode = new TreeNode();
		addToTree(treeNode, 0, list.size() -1, list);
		return treeNode;
	}
	
	private void addToTree( TreeNode treeNode, int start, int end, List<Integer> list){
		
		if ( start > end ){
			return;
		}
		int mid = (start + end)/2;
		
		treeNode.add(list.get(mid));
		addToTree(treeNode, start, mid - 1, list);
		addToTree(treeNode, mid + 1, end, list);
	}

	public TreeNode createTree123( ){
		TreeNode treeNode = new TreeNode();
		treeNode.add(3);
		treeNode.add(1);
		treeNode.add(5);
		return treeNode;
	}
}

class Node {
	private int data;
	public Node left;
	public Node right;

	public Node(int aData) {
		data = aData;
		left = null;
		right = null;
	}

	public int getData(){
		return data;
	}
}

class TreeNode{
	private Node root = null;
	public void add( int aData ){
		if ( root == null ){
			root = new Node(aData);
			return;
		}
		Node tmp = root;
		while ( true ){
			if ( tmp.getData() < aData ){
				if ( tmp.right == null ){
					tmp.right = new Node( aData );
					//System.out.print(this);
					break;
				}
				tmp = tmp.right; 
			} else {
				if ( tmp.left == null ){
					tmp.left = new Node( aData );
					//System.out.print(this);
					break;
				}
				tmp = tmp.left;
			}		
		}
	}
	
	public boolean isSubTree(TreeNode other){
		return isSubTree(root, other.root);
	}

	private boolean isSubTree(Node root2, Node other) {
		
		if ( other == null ){
			return true;
		}
		if ( root2 == null ){
			return false;
		}
		if ( root2.getData() == other.getData() ){
			System.out.println("equals:" +  root2.getData()  + " " + other.getData() );
			return isSubTree( root2.left, other.left ) && isSubTree( root2.right, other.right ); 
		} else {
			return isSubTree( root2.left, other ) && isSubTree( root2.right, other );
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder( );
		fill( root, sb ); 
		return sb.toString();
	}

	private void fill(Node root2, StringBuilder sb) {
		if ( root2 != null ){
			sb.append(root2.getData());
			sb.append("{");
			if ( root2.left != null ){
				sb.append(root2.left.getData());
			}
			sb.append(", ");
			if ( root2.right != null ){
				sb.append(root2.right.getData());
			}
			sb.append("}");
			sb.append("\n");
			fill( root2.left, sb );
			fill( root2.right, sb );
		}
	}
}

