import java.util.ArrayList;
import java.util.Collections;

public class State {

	public static int TOTAL_STATES;

	public Pair blackKing, whiteRook, whiteKing;

	public ArrayList<Pair> blackDanger, blackMoves;

	public boolean WHITE_TO_MOVE;

	public State(int wrr, int wrc, int wkr, int wkc, int bkr, int bkc, boolean WHITE_TO_MOVE) {
		whiteRook = new Pair(wrr, wrc);
		whiteKing = new Pair(wkr, wkc);

		blackKing = new Pair(bkr, bkc);
		blackDanger = new ArrayList<Pair>();
		blackMoves = new ArrayList<Pair>();

		this.WHITE_TO_MOVE = WHITE_TO_MOVE;

		TOTAL_STATES++;
	}

	public Pair getNextBlackMove() {
		if (canCapRook()){
			blackMoves.add(whiteRook);
		} else {
			fillBlackDangerList();
			fillBlackMoveList();
		}
		Collections.sort(blackMoves);
		return blackMoves.get(0);
	}

	private void fillBlackDangerList() {
		for (int r = 1; r <= Main.BOARD_SIZE; r++) {
			if (r == whiteRook.row)
				continue;
			if (!occupied(r, whiteRook.col))
				blackDanger.add(new Pair(r, whiteRook.col));
			else
				break;
		}
		for (int c = 1; c <= Main.BOARD_SIZE; c++) {
			if (c == whiteRook.col)
				continue;
			if (!occupied(whiteRook.row, c))
				blackDanger.add(new Pair(whiteRook.row, c));
			else
				break;
		}

		for (int r = whiteKing.row - 1; r <= whiteKing.row + 1; r++) {
			for (int c = whiteKing.col - 1; c <= whiteKing.col + 1; c++) {
				if (r < 1 || r > Main.BOARD_SIZE || c < 1 || c > Main.BOARD_SIZE)
					continue;
				if (r == whiteKing.row && c == whiteKing.col)
					continue;
				blackDanger.add(new Pair(r, c));
			}
		}
	}

	private void fillBlackMoveList() {
		for (int r = blackKing.row - 1; r <= blackKing.row + 1; r++) {
			for (int c = blackKing.col - 1; c <= blackKing.col + 1; c++) {
				if (r < 1 || r > 8 || c < 1 || c > 8)
					continue;
				if (r == blackKing.row && c == blackKing.col)
					continue;
				Pair nextMove = new Pair(r,c);
				if (!blackDanger.contains(nextMove)){
					blackMoves.add(nextMove);
				}
			}
		}
	}

	private boolean occupied(int r, int c) {
		return (r == whiteKing.row && c == whiteKing.col) || (r == whiteRook.row && c == whiteRook.col)
				|| (r == blackKing.row && c == blackKing.col);
	}
	
	private boolean canCapRook(){
		for (int r = blackKing.row - 1; r <= blackKing.row + 1; r++) {
			for (int c = blackKing.col - 1; c <= blackKing.col + 1; c++) {
				if (r < 1 || r > 8 || c < 1 || c > 8)
					continue;
				if (r == blackKing.row && c == blackKing.col)
					continue;
				Pair nextMove = new Pair(r,c);
				if (nextMove.equals(whiteRook)){
					for (int r2 = nextMove.row - 1; r2 <= nextMove.row + 1; r2++) {
						for (int c2 = nextMove.col - 1; c2 <= nextMove.col + 1; c2++) {
							if (r2 < 1 || r2 > Main.BOARD_SIZE || c2 < 1 || c2 > Main.BOARD_SIZE)
								continue;
							if (r2 == nextMove.row && c2 == nextMove.col)
								continue;
							Pair whiteKingCheck = new Pair(r2, c2);
							if (whiteKingCheck.equals(whiteKing))
								return false;
						}
					}
					return true;
				}
			}
		}
		return false;
	}
}
