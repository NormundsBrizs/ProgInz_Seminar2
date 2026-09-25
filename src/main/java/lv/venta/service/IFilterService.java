package lv.venta.service;

import java.util.ArrayList;

import lv.venta.enums.Degree;
import lv.venta.model.Course;
import lv.venta.model.Grade;
import lv.venta.model.Student;

public interface IFilterService {
	
	public abstract ArrayList<Grade> filterGradesByStudentId(long id) throws Exception;
	
	public abstract ArrayList<Grade> filterGradesByCourseTitle(String title) throws Exception;
	
	public abstract ArrayList<Course> filterCoursesByProfessorDegree(Degree degree) throws Exception;
	
	public abstract ArrayList<Student> filterStudentsByFailedGrades() throws Exception;
	
	public abstract ArrayList<Grade> filterFailedGradesByCourseTitle(String title) throws Exception;
	
	public abstract ArrayList<Grade> filterAllPerfectGrades() throws Exception;
	
	public abstract ArrayList<Course> filterCoursesByCreditPointsLessThan4() throws Exception;
}
