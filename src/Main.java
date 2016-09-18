import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static final int BOARD_SIZE = 8;

	public static void main(String[] args) throws IOException {
		Scanner fileReader = new Scanner(new File("res/data.txt"));
		int numTests = fileReader.nextInt();
		fileReader.nextLine();
		for (int i = 0; i < numTests; i++) {
			System.out.println(fileReader.nextLine());
			State.TOTAL_STATES = 0;
			RunTest(fileReader);
		}
	}

	public static void RunTest(Scanner instream) {
		int wrr = 0, wrc = 0;
		int wkr = 0, wkc = 0;
		int bkr = 0, bkc = 0;
		for (int r = 1; r <= BOARD_SIZE; r++) {
			for (int c = 1; c <= BOARD_SIZE; c++) {
				String input = instream.next();
				if (input.equals("BK")) {
					bkr = r;
					bkc = c;
				} else if (input.equals("WK")) {
					wkr = r;
					wkc = c;
				} else if (input.equals("WR")) {
					wrr = r;
					wrc = c;
				}
			}
		}
		try {
			instream.nextLine();
			instream.nextLine();
		} catch (Exception e) {
		}
		State initialState = new State(wrr, wrc, wkr, wkc, bkr, bkc, true);
		System.out.println(initialState.getNextBlackMove());
	}
	
	public static void printUtilityGrid() {
		for (int r = 1; r <= Main.BOARD_SIZE; r++) {
			for (int c = 1; c <= Main.BOARD_SIZE; c++) {
				Pair pair = new Pair(r, c);
				System.out.printf("%04.1f ", pair.getUtilityValue());
			}
			System.out.println();
		}
	}

}
