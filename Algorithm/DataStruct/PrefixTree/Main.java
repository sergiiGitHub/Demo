import java.util.Scanner;

public class Main {

	// time complexity O(n)
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PrefixTree pt = new PrefixTree();
		pt.add("abc");
		pt.add("abd");
		pt.add("abx");
		pt.add("aab");

		String word = "aaa";
		System.out.println("has: " + word + ": " + pt.hasWort(word));

		word = "abx";
		System.out.println("has: " + word + ": " + pt.hasWort(word));

		word = "aab";
		System.out.println("has: " + word + ": " + pt.hasWort(word));

		word = "aa";
		System.out.println("isPrefix " + word + ": " + pt.isPrefix(word));
		word = "bb";
		System.out.println("isPrefix " + word + ": " + pt.isPrefix(word));
	}

	static class PrefixTree {
		TreeNode root = new TreeNode();

		boolean hasWort(String word) {
			TreeNode node = containe(word);
			return node != null ? node.isEnd() : false;
		}

		boolean isPrefix(String word) {
			return containe(word) != null;
		}

		private TreeNode containe(String word) {
			TreeNode currentNode = root;
			for (char c : word.toCharArray()) {
				if (!currentNode.containe(c)) {
					return null;
				}
				currentNode = currentNode.get(c);
			}
			return currentNode;
		}

		void add(String word) {
			TreeNode currentNode = root;
			for (char c : word.toCharArray()) {
				if (!currentNode.containe(c)) {
					currentNode.put(c, new TreeNode());
				}
				currentNode = currentNode.get(c);
			}
			currentNode.setIsEnd();
		}
	}

	static class TreeNode {
		static int S = 26;
		boolean isEnd;
		TreeNode chars[] = new TreeNode[S];

		void put(char ch, TreeNode node) {
			chars[ch - 'a'] = node;
		}

		boolean containe(char ch) {
			return get(ch) != null;
		}

		TreeNode get(char ch) {
			return chars[ch - 'a'];
		}

		boolean isEnd() {
			return isEnd;
		}

		void setIsEnd() {
			isEnd = true;
		}
	}
}