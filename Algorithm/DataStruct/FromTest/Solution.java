import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Solution {
	private MapItem map[][];
	private int dim;
	private int visited[][];
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
			map[parkGposY - 1][parkGposX].add(Direction.UP, Direction.RIGHT);
		} else if (0 == locPx) {
			map[parkGposY][parkGposX].add(Direction.NONE, Direction.LEFT);
			map[parkGposY][parkGposX - 1].add(Direction.UP, Direction.RIGHT);
			map[parkGposY - 1][parkGposX].add(Direction.LEFT, Direction.UP);
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
		// printMap(map);
	}

	public int getDistance(int from, int to) {
		MyPoint start = getPoint(from);
		MyPoint end = getPoint(to);

		visited = new int[dim][dim];

		return dfs(start.p.x, start.p.y, end.p.x, end.p.y, Direction.NONE, 0);
	}

	private int dfs(int x, int y, int endX, int endY, Direction dirFrom,
			int distance) {
		if (x == endX && y == endY) {
			return distance;
		}

		int res = Integer.MAX_VALUE;
		if ((visited[y][x] & dirFrom.value) != 0) {
			return res;
		}

		for (int i = Direction.NONE.ordinal() + 1; i < Direction.values().length; ++i) {
			Direction nextDir = Direction.values()[i];
			if (map[y][x].isNextDir(dirFrom, nextDir)) {

				int newX = x;
				int newY = y;
				if (nextDir == Direction.LEFT) {
					--newX;
				} else if (nextDir == Direction.RIGHT) {
					++newX;
				} else if (nextDir == Direction.UP) {
					--newY;
				} else if (nextDir == Direction.DOWN) {
					++newY;
				}
				visited[y][x] |= nextDir.value;
				printVisited();
				int curRes = dfs(newX, newY, endX, endY, nextDir, distance + 1);
				if (curRes < res) {
					res = curRes;
				}
				visited[y][x] ^= nextDir.value;
				printVisited();
			}
		}

		return res;
	}

	private void printVisited() {
		for (int y = 0; y < visited.length; ++y) {
			for (int x = 0; x < visited[y].length; ++x) {
				System.out.print(Direction.getSimpolV(visited[y][x]));
			}
			System.out.println();
		}
	}

	private MyPoint getPoint(int id) {
		for (MyPoint point : myPoints) {
			if (id == point.id) {
				return point;
			}
		}
		throw new Error();
	}

	public boolean isPossibleMove(Direction from, Direction to, int x, int y) {
		return map[y][x].isNextDir(from, to);
	}

	class MyPoint {
		private final Point p;
		private final int id;

		public MyPoint(int id, int x, int y) {
			this.id = id;
			p = new Point(x, y);
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
					sb.append(Direction.getSimpolV(Direction.values()[c].value));
					sb.append(dirPair[c]);
					sb.append("[");

					for (int i = 0; i < Direction.values().length; ++i) {
						if ((dirPair[c] & Direction.values()[i].value) != 0) {
							sb.append(Direction.getSimpolV(Direction.values()[i].value));
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

		public static char getSimpolV(int i) {
			if (i == Direction.LEFT.value) {
				return '<';
			} else if (i == Direction.RIGHT.value) {
				return '>';
			} else if (i == Direction.UP.value) {
				return '^';
			} else if (i == Direction.DOWN.value) {
				return 'v';
			} else {
				return '.';
			}
		}
	}
}
