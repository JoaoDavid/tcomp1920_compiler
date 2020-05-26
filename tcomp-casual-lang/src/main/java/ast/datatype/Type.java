package ast.datatype;

public abstract class Type {
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!this.getClass().equals(obj.getClass())) {
			return false;
		}
		return true;
	}


}
