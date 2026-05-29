package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.enums.Degree;
import lv.venta.model.Course;
import lv.venta.model.Grade;
import lv.venta.model.Student;
import lv.venta.repo.ICourseRepo;
import lv.venta.repo.IGradeRepo;
import lv.venta.repo.IProfessorRepo;
import lv.venta.repo.IStudentRepo;
import lv.venta.service.IFilterService;

@Service
public class FilterServiceImpl implements IFilterService {

	@Autowired
	private IStudentRepo studRepo;
	
	@Autowired
	private IGradeRepo gradeRepo;
	
	@Autowired
	private ICourseRepo courseRepo;
	
	@Autowired
	private IProfessorRepo profRepo;
	
	@Override
	public ArrayList<Grade> filterGradesByStudentId(long id) throws Exception {
		if (id < 1) {
			throw new Exception("ID can't be less than 1");
		}
		if (!studRepo.existsById(id)) {
			throw new Exception("Student with id " + id + " does not exist");
		}
		if (gradeRepo.count() == 0) {
			throw new Exception("No grades in DB");
		}
		
		ArrayList<Grade> resultFromDB = gradeRepo.findByStudentIds(id);
		
		if (resultFromDB.isEmpty()) {
			throw new Exception("No grades found for student with id " + id);
		}
		
		return resultFromDB;
	}

	@Override
	public ArrayList<Grade> filterGradesByCourseTitle(String title) throws Exception {
		if (gradeRepo.count() == 0) {
			throw new Exception("No grades found");
		}
		if (title == null || title.isEmpty() || !title.matches("[A-Ž]{1}[A-Ža-ž0-9 ]{3,40}")) {
			throw new Exception("Invalid input");
		}
		if (!courseRepo.existsByTitle(title)) {
			throw new Exception("Course with title " + title + " does not exist");
		}
		
		ArrayList<Grade> resultFromDB = gradeRepo.findByCourseTitle(title);
		
		if (resultFromDB.isEmpty()) {
			throw new Exception("No grades found for course with title " + title);
		}
		
		return resultFromDB;
	}

	@Override
	public ArrayList<Course> filterCoursesByProfessorDegree(Degree degree) throws Exception {
		if (courseRepo.count() == 0) {
			throw new Exception("No courses found");
		}
		if (degree == null) {
			throw new Exception("Invalid input");
		}
		if (!profRepo.existsByDegree(degree)) {
			throw new Exception("Professor(s) with " + degree + " degree do(es)n't exist");
		}
		
		ArrayList<Course> resultFromDB = courseRepo.findByProfessorDegree(degree);
		
		if (resultFromDB.isEmpty()) {
			throw new Exception("No courses found for degree: " + degree);
		}
		
		return resultFromDB;
	}

	@Override
	public ArrayList<Student> filterStudentsByFailedGrades() throws Exception {
		if (studRepo.count() == 0) {
			throw new Exception("No students found");
		}
		if (gradeRepo.count() == 0) {
			throw new Exception("No grades found");
		}
		
		ArrayList<Student> resultFromDB = studRepo.findByGradesGrvalueLessThan(4);
		
		if (resultFromDB.isEmpty()) {
			throw new Exception("No students with failing grades");
		}
		
		return resultFromDB;
	}
}
