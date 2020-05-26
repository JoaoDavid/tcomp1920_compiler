package ast.datatype;

public class CustomType extends Type {
	
	private String name;

	public CustomType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
