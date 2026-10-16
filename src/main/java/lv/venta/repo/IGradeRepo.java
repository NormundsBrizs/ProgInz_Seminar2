package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Course;
import lv.venta.model.Grade;
import lv.venta.model.Student;

public interface IGradeRepo extends CrudRepository<Grade, Long> {

	ArrayList<Grade> findByStudentIds(long id);

	ArrayList<Grade> findByCourseTitle(String title);

	ArrayList<Grade> findByCourseTitleLessThan(String title, int i);

	boolean existsByGrvalueAndStudentAndCourse(int gradeValue, Student stud, Course course);
	
}
