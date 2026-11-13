package lv.venta.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import lv.venta.model.Student;
import lv.venta.service.ICRUDStudentService;
import lv.venta.service.impl.CRUDStudentServiceImpl;

class StudentControllerTest {

	@Mock
	private CRUDStudentServiceImpl studService;

	@InjectMocks
	private StudentCRUDController studController;
	private MockMvc mockMVC;

	@BeforeEach
	public void initialize() {
		MockitoAnnotations.openMocks(this);
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		mockMVC = MockMvcBuilders.standaloneSetup(studController).setValidator(validator).build();
	}

	@Test
	public void testAllStudents() throws Exception {
		Student s1 = new Student("Normunds", "Brizs");
		Student s2 = new Student("Name", "Surname");
		ArrayList<Student> allStudents = new ArrayList<>(Arrays.asList(s1, s2));

		when(studService.retrieveAll()).thenReturn(allStudents);

		mockMVC.perform(get("/student/crud/all")).andExpect(status().isOk()).andExpect(view().name("show-all-students"))
				.andExpect(model().attributeExists("package")).andExpect(model().attribute("package", allStudents));
	}

	@Test
	public void testAddController() throws Exception {
	    Student s1 = new Student("Normunds", "Brizs");
	    Student s2 = new Student("Name", "Surname");
	    Student s3 = new Student("Oskars", "Domigais");
	    ArrayList<Student> allStudents = new ArrayList<>(Arrays.asList(s1, s2, s3));

	    when(studService.create(any(Student.class))).thenReturn(s3);
	    when(studService.retrieveAll()).thenReturn(allStudents);

	    mockMVC.perform(post("/student/crud/add")
	            .param("name", "Oskars")
	            .param("surname", "Domigais"))
	        .andExpect(status().is3xxRedirection())
	        .andExpect(redirectedUrl("/student/crud/all"));

	    verify(studService).create(any(Student.class));
	}

}
