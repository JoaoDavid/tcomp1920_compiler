package ast;

import java.util.List;


public class CasualFile implements Node {
	
	private List<DefDecl> statements;
	private Position pos;
	
	
	public CasualFile(List<DefDecl> statements) {
		new CasualFile(statements, null);
	}

	public CasualFile(List<DefDecl> statements, Position pos) {
		this.statements = statements;
		this.pos = pos;
	}
		

	public List<DefDecl> getStatements() {
		return statements;
	}

	@Override
	public Position getPosition() {
		return pos;
	}

}
