package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lv.venta.model.Student;
import lv.venta.service.ICRUDStudentService;

@RestController
@RequestMapping("/student/crud")
public class StudentCRUDController {
	
	@Autowired
	private ICRUDStudentService studService;
	
	@GetMapping("/all") // localhost:8080/student/crud/all
	public ResponseEntity<?> getControllerAllStudents() {
		try {
			return new ResponseEntity<ArrayList<Student>>(studService.retrieveAll(), HttpStatusCode.valueOf(200));
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(409));
		}
	}
	
	@GetMapping("/all/{id}") //localhost:8080/student/crud/all/1
	public ResponseEntity<?> getControllerRetrieveStudentById(@PathVariable(name = "id") long id) {
		try {
			return new ResponseEntity<Student>(studService.retrieveById(id), HttpStatusCode.valueOf(200));
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(409));
		}
	}
	
	@GetMapping("/delete/{id}")//localhost:8080/student/crud/delete/2
	public ResponseEntity<?> getDeleteStudentById(@PathVariable(name = "id") long id) {
		try {
			studService.deleteById(id);
			return new ResponseEntity<ArrayList<Student>>(studService.retrieveAll(), HttpStatusCode.valueOf(200));
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(409));
		}
	}
	
	@PutMapping("/update/{id}") // localhost:8080/student/crud/update/1
	public ResponseEntity<?> putUpdateStudentById(@PathVariable(name = "id") long id, @Valid @RequestBody Student student,
			BindingResult result) {
		try {
			studService.updateById(id, student.getName(), student.getSurname());
			return new ResponseEntity<ArrayList<Student>>(studService.retrieveAll(), HttpStatusCode.valueOf(200));
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(409));
		}
	}
	
	@PostMapping("/add")//localhost:8080/student/crud/add
	public ResponseEntity<?> postController(@Valid @RequestBody Student student, 
			BindingResult result) {
		if(result.hasErrors()) {
			return new ResponseEntity<Integer>(result.getErrorCount(),
					HttpStatusCode.valueOf(409));
		}
		
		try
		{
			studService.create(student);
			return 
			new ResponseEntity<ArrayList<Student>>
			(studService.retrieveAll(), HttpStatusCode.valueOf(200));
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),
			HttpStatusCode.valueOf(409));
		}
	}
}
