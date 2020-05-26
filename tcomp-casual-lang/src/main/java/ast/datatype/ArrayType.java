package ast.datatype;

public class ArrayType extends Type {
	
	private Type content;
	private int numNestedArr;

	public ArrayType(int numNestedArr, Type inside) {
		this.numNestedArr = numNestedArr;
		this.content = inside;
	}	

	public int getNumNestedArr() {
		return numNestedArr;
	}

	public Type getInside() {
		return content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + numNestedArr;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ArrayType)) {
			return false;
		}
		ArrayType other = (ArrayType) obj;
		if (content == null) {
			if (other.content != null) {
				return false;
			}
		} else if (!content.equals(other.content)) {
			return false;
		}
		if (numNestedArr != other.numNestedArr) {
			return false;
		}
		return true;
	}	
	
	

}
