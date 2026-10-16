package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lv.venta.model.dto.GradeDTO;
import lv.venta.service.IGradeService;

@RestController
@RequestMapping("/grade")
public class GradeController {

	@Autowired
	private IGradeService gradeService;
	
	@PostMapping("/add/one") //localhost:8080/grade/add/one
	public ResponseEntity<?> postControllerAddOneGrade(@RequestBody GradeDTO gradeDTO){
		try {
			gradeService.addNewGrade(gradeDTO);
			return new ResponseEntity<ArrayList<GradeDTO>>(gradeService.retrieveAll(), HttpStatusCode.valueOf(200));
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(409));
		}
	}
	
	@PostMapping("/add/multiple") //localhost:8080/grade/add/multiple
	public ResponseEntity<?> postControllerAddMultipleGrades(@RequestBody ArrayList<GradeDTO> gradesDTO){
		try {
			gradeService.addNewGrades(gradesDTO);
			return new ResponseEntity<ArrayList<GradeDTO>>(gradeService.retrieveAll(), HttpStatusCode.valueOf(200));
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(409));
		}
	}
}
