package ast;

import java.util.List;


public class CasualFile implements Node {
	
	private List<ImportDefinition> imports;
	private List<DefDecl> statements;
	private Position pos;	
	
	public CasualFile(List<DefDecl> statements) {
		this(null, statements, null);
	}
	
	public CasualFile(List<ImportDefinition> imports, List<DefDecl> statements) {
		this(imports, statements, null);
	}

	public CasualFile(List<ImportDefinition> imports, List<DefDecl> statements, Position pos) {
		this.imports = imports;
		this.statements = statements;
		this.pos = pos;
	}		

	public List<ImportDefinition> getImports() {
		return imports;
	}

	public List<DefDecl> getStatements() {
		return statements;
	}

	@Override
	public Position getPosition() {
		return pos;
	}

}
