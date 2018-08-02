
class UserSolution {

	static PrefixTreeBor pt;
	public static void init() {
		pt = new PrefixTreeBor();
    }

    public static void add_serial(String sn) {
    	pt.add(sn);
    }

    public static boolean present(String sn) {
    	return pt.isContain(sn);
    }
    
    static class PrefixTreeBor {
    	Bor root = new Bor(0, -1, 0, 0);
    	String strings[] = new String[100_000];
    	int stringCount = 0;
    	
    	void add(String str) {
    		
    		strings[stringCount] = str;
    		addToTree();
    		++stringCount;
    	}
    	
		private void addToTree() {
			Bor cur = root;
			int minLen = strings[stringCount].length();
			int i = 0;
    		while (i < minLen) {
    			char charAtNew = strings[stringCount].charAt(i);
				int index = Bor.getIndex(charAtNew);
    			Bor temp = cur.get(index);
    			if (temp == null) {
    				cur.add(new Bor(stringCount, index, i, minLen - 1));
    				return;
    			}
    			
    			while (i <= temp.end) {
    				char charAtOld = strings[temp.stringId].charAt(i);
    				charAtNew = strings[stringCount].charAt(i);
					if (i == strings[stringCount].length() - 1 || charAtOld != charAtNew) {
    					
    					Bor bor = new Bor(temp.stringId, index, temp.begin, i - 1);
						cur.add(bor);
						bor.isEnd = false;

    					if (i < minLen) {
    						//new 
							int newIndex = Bor.getIndex(charAtNew);
							bor.add(new Bor(stringCount, newIndex, i, minLen -1));
    					}
    					
						//old
    					temp.begin = i;
    					temp.indexId = Bor.getIndex(charAtOld);
    					bor.add(temp);
    					return;
    				}
    				++i;
    			}
    			cur = temp;
    		}
		}

		public boolean isContain(String str) {
			Bor cur = root;
			int i = 0;
			while (i < str.length()) {
				int index = Bor.getIndex(str.charAt(i));
				Bor tmp = cur.get(index);
				if(tmp == null) {
					return false;
		        }
	            while (i <= tmp.end) {
	                if (i == str.length()) {
	                    return tmp.isEnd;
	                }
	                if(strings[tmp.stringId].charAt(i) != str.charAt(i)) {
	                    return false;
	                }
	                ++i;
	            }
	            cur = tmp;
			}
			return cur.isEnd;
		}
    }
    
    static class Bor {
    	List nodes = new List();
		private boolean isEnd;
		int stringId;
		int end;
		int begin;
		private int indexId;

		public Bor(int stringId, int indexId, int index, int size) {
			this.stringId = stringId;
			this.begin= index;
			this.end = size;
			this.indexId = indexId;
			isEnd = true;
		}
		
		public void add(Bor bor) {
			nodes.add(bor);
		}

		public Bor get(int index) {
			MyIterator iter = new MyIterator(nodes.head);
			while(iter.hasNext()) {
				Bor bor = iter.next();
				if (bor.indexId == index) {
					return bor;
				}
			}
			return null;
		}

		static int getIndex(char ch) {
			if ('0' <= ch && ch <= '9') {
				return ch - '0';
			} else if ('a' <= ch && ch <= 'z') {
				return ch - 'a' + 10;
			} else if ('A' <= ch && ch <= 'Z') {
				return ch - 'A' + 36;
			} else {
				throw new Error("ch : "  + ch);
			}
		}
    }
    
    static class List {
    	Node head;
    	
    	void add(Bor bor) {
    		if (head == null) {
    			head = new Node(bor);
    		} else {
    			MyIterator iter = new MyIterator(head);
    			while(iter.hasNext()) {
    				if (iter.cur.data.indexId == bor.indexId) {
    					iter.cur.data = bor;
    					return;
    				}
    				iter.next();
    			}
    			
        		Node temp = head;
        		head = new Node(bor);
        		head.next = temp;
    			
    		}
    	}
    }
    
    static class MyIterator {
    	Node cur;
    	MyIterator(Node head) {
    		cur = head;
    	}
    	
    	boolean hasNext() {
    		return cur != null;
    	}
    	
    	Bor next() {
    		Bor res = cur.data;
    		cur = cur.next;
    		return res;
    	}
    }
    
    static class Node {
    	Node(Bor aBor) {
    		data = aBor;
    	}
    	
    	Node next;
    	Bor data;
    }
}
