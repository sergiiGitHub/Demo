import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author sergii
 * 
 *         https://acmp.ru/index.asp?main=task&id_task=400
 * 
 *         #input 5 1345 2584 2584 683 2584 1345 683 1345 683 1345 2584 683 1
 *         1234 4567 1234 4567 4567 4321 4322 4567 4321 1234 4321 1234 0 12 12
 *         12 12 12 13 12 13 12 13 12 13 1 12 12 12 12 12 12 12 12 12 12 12 12 1
 *         12 12 12 12 12 13 12 13 12 14 12 13 0
 * 
 */
public class Main {
	private static final boolean DEBUG = false;
	private static final String IDS_INPUT_FILE_NAME = "sample.txt";

	public void run() {
		Scanner scanner = getScanner(DEBUG);
		if (DEBUG) {
			int n = scanner.nextInt();
			while (0 < n) {
				--n;
				boolean res = runSub(scanner);
				boolean expectec = scanner.nextInt() == 1;
				if (expectec == res) {
					System.out.println("PATH");
				} else {
					System.out.println("!!! FAIL !!!");
				}
			}
		} else {
			runSub(scanner);
		}
	}

	private boolean runSub(Scanner scanner) {
		// TODO Auto-generated method stub
		final int SIZE = 6;
		Point points[] = new Point[SIZE];
		for (int i = 0; i < 6; ++i) {
			Point point = new Point();
			point.x = scanner.nextInt();
			point.y = scanner.nextInt();
			if (point.x > point.y) {
				int temp = point.x;
				point.x = point.y;
				point.y = temp;
			}
			points[i] = point;
		}

		Point pointAb = null;
		Point pointAc = null;
		Point pointBc = null;
		Point shellBePointBc = null;
		for (int i = 0; i < 6; ++i) {
			Point right = points[i];
			if (right == null) {
				continue;
			}
			for (int j = i + 1; j < 6; ++j) {
				Point left = points[j];
				if (right.equals(left)) {
					points[i] = points[j] = null;
					if (pointAb == null) {
						pointAb = right;
						break;
					} else if (pointAc == null) {
						if (pointAb.x == right.x) {
							pointAc = right;
							shellBePointBc = new Point(Math.min(pointAb.y,
									right.y), Math.max(pointAb.y, right.y));
						} else if (pointAb.x == right.y) {
							pointAc = right;
							shellBePointBc = new Point(Math.min(pointAb.y,
									right.x), Math.max(pointAb.y, right.x));
						} else if (pointAb.y == right.x) {
							pointAc = right;
							shellBePointBc = new Point(Math.min(pointAb.x,
									right.y), Math.max(pointAb.x, right.y));
						} else if (pointAb.y == right.y) {
							pointAc = right;
							shellBePointBc = new Point(Math.min(pointAb.x,
									right.x), Math.max(pointAb.x, right.x));
						}
						if (shellBePointBc != null) {
							if (shellBePointBc.x > shellBePointBc.y) {
								int t = shellBePointBc.y;
								shellBePointBc.y = shellBePointBc.x;
								shellBePointBc.x = t;
							}
						}
						break;
					} else if (right.equals(shellBePointBc)) {
						pointBc = right;
						break;
					}
				}
			}
		}

		boolean res = (pointAb != null) && (pointAc != null)
				&& (pointBc != null);
		if (res) {
			System.out.println("POSSIBLE");
		} else {
			System.out.println("IMPOSSIBLE");
		}
		return res;
	}

	private Scanner getScanner(boolean isDebug) {

		if (!isDebug) {
			return new Scanner(System.in);
		}

		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(IDS_INPUT_FILE_NAME));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return scanner;
	}

	public static void main(String[] args) {
		new Main().run();
	}
}

