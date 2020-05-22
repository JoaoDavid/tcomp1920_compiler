package sut;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ast.exception.SyntacticException;
import casual.CasualC;


@DisplayName("Testing .cas files")
public class CasFilesTest {

	@ParameterizedTest(name = "check if file {0} is Valid")
	@ValueSource(strings = { 
			"hello_world.cas",
			"benchmark.cas",
			"valid_example1.cas",
			"valid_example2.cas",
			"valid_example4.cas",
			"valid_example5.cas"})
	public void validTest(String file) {
		assertDoesNotThrow( () -> {
			CasualC.mainTest(".\\cas_files\\" + file);
		});
	}

	@ParameterizedTest(name = "check if file {0} is INvalid")
	@ValueSource(strings = { 
			"invalid_example1.cas", 
			"invalid_example2.cas", 
			"invalid_example4.cas",
			"invalid_example5.cas"})
	public void invalidTest(String file) {
		assertThrows(SyntacticException.class, () -> {
			CasualC.mainTest(".\\cas_files\\" + file);
		});
	}

}
