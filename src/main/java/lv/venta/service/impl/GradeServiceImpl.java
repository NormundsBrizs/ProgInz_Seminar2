package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import lv.venta.model.Course;
import lv.venta.model.Grade;
import lv.venta.model.Student;
import lv.venta.model.dto.GradeDTO;
import lv.venta.repo.ICourseRepo;
import lv.venta.repo.IGradeRepo;
import lv.venta.repo.IStudentRepo;
import lv.venta.service.IGradeService;

public class GradeServiceImpl implements IGradeService {

	@Autowired
	private IStudentRepo studRepo;
	
	@Autowired
	private ICourseRepo courseRepo;
	
	@Autowired
	private IGradeRepo gradeRepo;
	
	@Override
	public void addNewGrade(GradeDTO gradeDTO) throws Exception {
		if (gradeDTO == null) {
			throw new Exception("Invalid data");
		}
		
		if (gradeDTO.getGradeValue() < 1 || gradeDTO.getGradeValue() > 10) {
			throw new Exception("Grade has to be between 1 and 10");
		}
		
		if (!studRepo.existsByNameAndSurname(gradeDTO.getStudentName(), gradeDTO.getStudentSurname())) {
			throw new Exception("Student doesn't exist");
		}
		if (!courseRepo.existsByTitle(gradeDTO.getCourseTitle())) {
			throw new Exception("Course doesn't exist");
		}
		
		Student stud = studRepo.findByNameAndSurname(gradeDTO.getStudentName(), gradeDTO.getStudentSurname());
		Course course = courseRepo.findByTitle(gradeDTO.getCourseTitle());
		
		if (!gradeRepo.existsByGrvalueAndStudentAndCourse(gradeDTO.getGradeValue(), stud, course)) {
			Grade grade = new Grade(gradeDTO.getGradeValue(), stud, course);
			gradeRepo.save(grade);
		}
	}

	@Override
	public void addNewGrades(ArrayList<GradeDTO> gradesDTO) throws Exception {
		if (gradesDTO == null) {
			throw new Exception("Invalid data");
		}
		if (gradesDTO.isEmpty()) {
			throw new Exception("No data");
		}
		
		for (GradeDTO tempG : gradesDTO) {
			addNewGrade(tempG);
		}
	}

}
