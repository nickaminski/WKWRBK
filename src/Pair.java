public class Pair implements Comparable{
	public int row,col;
	
	public Pair(int r, int c){
		row = r;
		col = c;
	}
	
	public double getUtilityValue(){
		return 1.0 * Math.abs(col - 5) * 5 + Math.abs(row - 5) * 3 + (col + row) * 0.1;
	}
	
	public boolean equals(Object other){
		if (!(other instanceof Pair)) return false;
		Pair otherPair = (Pair)other;
		return (row==otherPair.row && col==otherPair.col);
	}
	
	public String toString(){
		return new String("("+row+", "+col+")");
	}

	public int compareTo(Object other) {
		if (!(other instanceof Pair))
			return -1;
		Pair otherPair = (Pair)other;
		return (int)(100 * (getUtilityValue() - otherPair.getUtilityValue()));
	}
}
