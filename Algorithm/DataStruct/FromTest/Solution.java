import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Solution {
	private MapItem map[][];
	private int dim;
	private List<MyPoint> myPoints;

	public void init(int dimention) {
		dim = dimention;
		map = new MapItem[dim][dim];
		myPoints = new ArrayList<>();

		for (int y = 0; y < map.length; ++y) {
			for (int x = 0; x < map[y].length; ++x) {
				map[y][x] = new MapItem();
			}
		}
	}

	public void printMap(MapItem map[][]) {
		for (int y = 0; y < map.length; ++y) {
			for (int x = 0; x < map[y].length; ++x) {
				System.out.print(map[y][x]);
			}
			System.out.println();
		}
	}

	public void addBuilding(int id, int posX, int posY, int w, int h,
			int locPx, int locPy) {

		// add parking
		int parkGposX = posX + locPx;
		int parkGposY = posY + locPy;
		myPoints.add(new MyPoint(id, parkGposX, parkGposY));

		if (0 == locPy) {
			map[parkGposY][parkGposX].add(Direction.NONE, Direction.UP);
			map[parkGposY - 1][parkGposX].add(Direction.RIGHT, Direction.DOWN);
		} else if (0 == locPx) {
			map[parkGposY][parkGposX].add(Direction.NONE, Direction.LEFT);
			map[parkGposY][parkGposX - 1].add(Direction.UP, Direction.RIGHT);
		} else {
			throw new Error("not implemented");
		}

		// add all dir
		map[posY - 1][posX - 1].setAllDir();
		map[posY - 1][posX + w].setAllDir();
		map[posY + h][posX - 1].setAllDir();
		map[posY + h][posX + w].setAllDir();

		{
			int y = posY - 1;
			int y2 = posY + h;
			for (int x = posX; x < (posX + w); ++x) {
				map[y][x].add(Direction.RIGHT, Direction.RIGHT);
				map[y2][x].add(Direction.LEFT, Direction.LEFT);
			}
		}
		{
			int x = posX - 1;
			int x2 = posX + w;
			for (int y = posY; y < (posY + h); ++y) {
				map[y][x].add(Direction.UP, Direction.UP);
				map[y][x2].add(Direction.DOWN, Direction.DOWN);
			}
		}
		printMap(map);
	}

	public int getDistance(int i, int j) {
		return -1;
	}

	public boolean isPossibleMove(Direction from, Direction to, int x, int y) {
		return map[y][x].isNextDir(from, to);
	}

	class MyPoint {
		private final Point p;
		private final int id;

		public MyPoint(int id, int x, int y) {
			this.id = id;
			p = new Point();
		}

		public Point getPoint() {
			return p;
		}

		public int getId() {
			return id;
		}
	}

	class MapItem {
		final int SIZE = Direction.values().length;
		private final int dirPair[] = new int[SIZE];
		private boolean isAllDir;
		private int totalAdd = 0;

		public void add(Direction from, Direction to) {
			dirPair[from.ordinal()] |= to.value;
			totalAdd += to.value;
		}

		// 1 + 2 + 4 + 8 = 15
		public void setAllDir() {
			isAllDir = true;
		}

		public boolean isNextDir(Direction from, Direction to) {
			if (isAllDir) {
				return true;
			} else {
				return (dirPair[from.ordinal()] & to.value) != 0;
			}
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			if (isAllDir) {
				sb.append("+");
			} else if (totalAdd == 0) {
				sb.append(".");
			} else {
				int printed = totalAdd;
				for (int c = 0; c < SIZE; ++c) {
					if (dirPair[c] == 0) {
						continue;
					}
					sb.append(getSimpol(c));
					sb.append(dirPair[c]);
					sb.append("[");

					for (int i = 0; i < Direction.values().length; ++i) {
						if ((dirPair[c] & Direction.values()[i].value) != 0) {
							sb.append(getSimpol(i));
							sb.append(",");
						}
					}
					sb.append("] ");
					printed -= dirPair[c];
					if (printed == 0) {
						break;
					}
				}
			}
			sb.append("\t");
			return sb.toString();
		}

		private char getSimpol(int i) {
			if (i == Direction.LEFT.ordinal()) {
				return '<';
			} else if (i == Direction.RIGHT.ordinal()) {
				return '>';
			} else if (i == Direction.UP.ordinal()) {
				return '^';
			} else if (i == Direction.DOWN.ordinal()) {
				return 'v';
			} else {
				return '.';
			}
		}
	}

	public enum Direction {
		NONE(0), LEFT(1), RIGHT(1 << 1), UP(1 << 2), DOWN(1 << 3);

		private final int value;

		private Direction(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
}
