package lv.venta.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StudentTest {

	@Test
	public void testStudentConstructor() {
		Student student = new Student("Normunds", "Brizs");
		assertEquals("Normunds", student.getName());
		assertEquals("Brizs", student.getSurname());
	}
	
	@Test
	public void testToString() {
		Student student = new Student("Normunds", "Brizs");
		assertNotNull(student.toString());
	}
	
	@Test
	public void testDefaultConstructor() {
		Student student = new Student();
		student.setName("Janis");
		student.setSurname("Berzins");
		assertEquals("Janis", student.getName());
		assertEquals("Berzins", student.getSurname());
	}

}
