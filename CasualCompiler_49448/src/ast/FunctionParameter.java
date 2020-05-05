package ast;

public class FunctionParameter implements Node {
	
	private String varName;
	private String datatype;
	private Position pos;
	
	public FunctionParameter(String varName, String datatype) {
		new FunctionParameter(varName, datatype, null);
	}
	
	public FunctionParameter(String varName, String datatype, Position pos) {
		this.varName = varName;
		this.datatype = datatype;
		this.pos = pos;
	}
		

	public String getVarName() {
		return varName;
	}

	public String getDatatype() {
		return datatype;
	}

	@Override
	public Position getPosition() {
		return pos;
	}
	
	
	

}
