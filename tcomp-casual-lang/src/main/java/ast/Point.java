package ast;

public class Point {
	
	private int line;
	private int column;
	
	public Point(int line, int column) {
		this.line = line;
		this.column = column;
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}	
	
	public String toString() {
		return "line:" + this.line + " column:" + this.column;
	}

}
