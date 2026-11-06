package lv.venta.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import lv.venta.model.Student;
import lv.venta.repo.IStudentRepo;
import lv.venta.service.impl.CRUDStudentServiceImpl;

class StudentServiceTest {

	@Mock
	private IStudentRepo studRepo;
	
	@InjectMocks
	private CRUDStudentServiceImpl studService;
	
	@BeforeEach
	public void initialize() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testRetrieveAll() {
		Student s1 = new Student("Normunds", "Brizs");
		Student s2 = new Student("Name", "Surname");
		
		ArrayList<Student> testStudents = new ArrayList<Student>();
		testStudents.addAll(Arrays.asList(s1, s2));
		
		when(studRepo.count()).thenReturn(2l);
		when(studRepo.findAll()).thenReturn(testStudents);
		
		try {
			assertEquals(testStudents, studService.retrieveAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertDoesNotThrow(() -> studService.retrieveAll());
	}
	
	@Test
	public void testRetrieveAllThrowException() {
		when(studRepo.count()).thenReturn(0l);
		
		assertThrows(Exception.class, () -> studService.retrieveAll());
	}

}
