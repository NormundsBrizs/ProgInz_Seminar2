package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.enums.Degree;
import lv.venta.model.Course;
import lv.venta.model.Grade;

public interface ICourseRepo extends CrudRepository<Course, Long> {

	boolean existsByTitle(String title);

	ArrayList<Course> findByProfessorsDegree(Degree degree);

	ArrayList<Course> findByCreditpointsLessThan(int i);
	
}
