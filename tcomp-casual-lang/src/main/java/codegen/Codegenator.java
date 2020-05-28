package codegen;

import java.io.File;
import java.io.IOException;

import ast.Node;
import codegen.exception.CompileException;

public class Codegenator {
	
	private final static String defaultPath = System.getProperty("user.home") + File.separator + "Desktop";
	private final static String sufix = ".ll";
	
	private Node n;
	private File file;

	public Codegenator(Node n, String fileName, String path) {
		file = new File(path + File.separator + fileName + sufix);
		this.n = n;
	}
	
	public Codegenator(Node n, String fileName) {
		this(n, fileName, defaultPath);
	}
	
	public void generateLL() throws CompileException {
		if(file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void visitAST() throws CompileException {
		
	}

}
