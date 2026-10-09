package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Student;

public interface IStudentRepo extends CrudRepository<Student, Long> {

	ArrayList<Student> findByGradesGrvalueLessThan(int i);

	boolean existsByName(String name);

	Student findByUserUsername(String sessionUsername);
	
	
}
