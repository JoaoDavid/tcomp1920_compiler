package ast;

public class ImportDefinition implements Node {
	
	private String name;
	private Position pos;
	
	
	public ImportDefinition(String name) {
		this(name, null);
	}

	public ImportDefinition(String name, Position pos) {
		this.name = name;
		this.pos = pos;
	}
		

	public String getImportName() {
		return name;
	}

	@Override
	public Position getPosition() {
		return pos;
	}

}
