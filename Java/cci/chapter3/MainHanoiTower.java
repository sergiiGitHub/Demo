import java.util.Stack;

public class MainHanoiTower {

	private Tower[] towers = {new Tower(0), new Tower(1), new Tower(2) };; 
	int ringNumber = 10;
	int towerNumber = 3;
	public MainHanoiTower(){
		for ( int i = ringNumber - 1; i >= 0; --i ){
			towers[ 0 ].add( new Ring(i) );
		}
	}
	
	public void move1Ring(  int moveFrom, int moveTo ){
		Ring moveRing = towers[moveFrom].pop();
		Ring res = towers[moveTo].push(moveRing);
		if ( res == null ){
			throw new IllegalAccessError();
		}
	}
	
	// 1 -> 3
	public void move2Ring(int moveFrom, int moveTo){
		final int other = 3 - moveTo - moveFrom;
		// 1 -> 2
		move1Ring( moveFrom, other );
		// 1 - > 3
		move1Ring( moveFrom, moveTo );
		// 2 -> 3
		move1Ring( other, moveTo );
	}
	
	public void move(int moveFrom, int moveTo){
		
		if ( !towers[moveTo].isEmpty() || towers[moveFrom].isEmpty() ){
			System.out.print("Already moved");
		}

		int size = towers[moveFrom].size();
		move( moveFrom, moveTo, size );
	}
	
	
	private void move(int moveFrom, int moveTo, int size) {
		
		if ( size == 1 ){
			Ring moveRing = towers[moveFrom].pop();
			towers[moveFrom].push(moveRing);
		} else if ( size == 2 ){
			move2Ring(moveFrom, moveTo);
		} else {
			int other = 3 - moveFrom - moveTo;
			move( moveFrom, other, size - 1);
			move1Ring( moveFrom, moveTo );
			move( other, moveTo, size - 1);
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
		m.move(0, 2);
		System.out.println("Time spend: " + (System.currentTimeMillis() - time));
		System.out.println(m.toString());
	}
	
	
	class Ring{
		private int w;
		public Ring( int w ){
			this.w = w;
		}
		
		public int getWeigth(){
			return w;
		}
		
		@Override
		public String toString() {
			return Integer.toString(w);
		}
		
	}
	
	class Tower extends Stack<Ring >{
		private final int id;
		
		public Tower(int id){
			this.id = id;
		}
		
		public Ring push( Ring r ){
			
			if ( isEmpty() ){
				return super.push(r);
			}
			
			Ring last = get(size() - 1);
			if ( last.getWeigth() > r.getWeigth() ){
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
