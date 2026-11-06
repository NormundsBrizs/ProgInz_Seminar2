package lv.venta.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class StudentValidTest {

	private Validator validator;
	
	@BeforeEach
	public void initialize() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void validateGoodStudent() {
		Student student = new Student("Normunds", "Brizs");
		Set<ConstraintViolation<Student>> violations = validator.validate(student);
		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void validateWrongStudent() {
		Student student = new Student("", "A123");
		Set<ConstraintViolation<Student>> violations = validator.validate(student);
		assertFalse(violations.isEmpty());
		
		for (ConstraintViolation<Student> tempS : violations) {
			System.out.println(tempS.getMessage());
		}
	}

}
