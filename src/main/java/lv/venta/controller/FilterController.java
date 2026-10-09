package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lv.venta.enums.Degree;
import lv.venta.model.Course;
import lv.venta.model.Grade;
import lv.venta.model.Student;
import lv.venta.service.IFilterService;

@RestController
@RequestMapping("/filter")
public class FilterController {

	@Autowired
	private IFilterService filterService;
	
	@GetMapping("/grade/student/{id}") // localhost:8080/filter/grade/student/1
	public ResponseEntity<?> getControllerGradesByStudentId(@PathVariable(name = "id") long id) {
		try {
			return new ResponseEntity<ArrayList<Grade>>(filterService.filterGradesByStudentId(id), HttpStatusCode.valueOf(200));
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(409));
		}
	}
	
	@GetMapping("/grade/course/{title}") // localhost:8080/filter/grade/course/Tīmekļa tehnoloģijas
	public ResponseEntity<?> getControllerGradesByCourseTitle(@PathVariable(name = "title") String title) {
		try {
			return new ResponseEntity<ArrayList<Grade>>(filterService.filterGradesByCourseTitle(title), HttpStatusCode.valueOf(200));
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(409));
		}
	}
	
	@GetMapping("/course/professor/{degree}") // localhost:8080/filter/course/professor/master
	public ResponseEntity<?> getControllerCoursesByProfessorDegree(@PathVariable(name = "degree") Degree degree) {
		try {
			return new ResponseEntity<ArrayList<Course>>(filterService.filterCoursesByProfessorDegree(degree), HttpStatusCode.valueOf(200));
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(409));
		}
	}
	
	@GetMapping("/student/failed") // localhost:8080/filter/student/failed
	public ResponseEntity<?> getControllerStudentsByFailedGrades() {
		try {
			return new ResponseEntity<ArrayList<Student>>(filterService.filterStudentsByFailedGrades(), HttpStatusCode.valueOf(200));
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(409));
		}
	}
	
	// atzimes nesekmigas konkreta prieksmeta
	@GetMapping("/grade/course/{title}/failed") // localhost:8080/filter/grade/course/Tīmekļa tehnoloģijas/failed
	public ResponseEntity<?> getControllerFailedGradesByCourseTitle(@PathVariable(name = "title") String title) {
		try {
			return new ResponseEntity<ArrayList<Grade>>(filterService.filterFailedGradesByCourseTitle(title), HttpStatusCode.valueOf(200));
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(409));
		}
	}
}
