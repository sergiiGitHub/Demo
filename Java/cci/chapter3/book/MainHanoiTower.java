import java.util.Stack;

public class MainHanoiTower {

	private Tower[] towers = {new Tower(0), new Tower(1), new Tower(2) };; 
	int ringNumber = 5;
	int towerNumber = 3;

	public MainHanoiTower(){
		for ( int i = ringNumber - 1; i >= 0; --i ){
			towers[ 0 ].push( i );
		}
	}

	private void move(int n, int from, int to) {
		if ( n > 0 ){
			int other = 3 - from - to;
			move( n - 1, from, other );
			//move
			Integer disc = towers[from].pop();
			towers[to].push(disc);
			
			move( n - 1, other, to);
		}
	}	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for ( int i = 0; i < towers.length; ++i ){
			sb.append(towers[i]);
			sb.append("\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		
		MainHanoiTower m = new MainHanoiTower();
		System.out.println(m.toString());
		long time = System.currentTimeMillis();
		m.move(m.ringNumber, 0, 2);
		System.out.println("Time spend: " + (System.currentTimeMillis() - time));
		System.out.println(m.toString());
	}

	class Tower extends Stack<Integer>{
		private final int id;
		
		public Tower(int id){
			this.id = id;
		}
		
		public Integer push( Integer r ){
			
			if ( isEmpty() || peek() > r ){
				return super.push(r);
			}
			return null;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder( "id: " );
			sb.append(id);
			sb.append("{");
			for ( int i = 0; i < size(); ++i  ){
				sb.append(get(i));
				sb.append(" ");
			}
			sb.append("}");
			return sb.toString();
		}
	}
}
