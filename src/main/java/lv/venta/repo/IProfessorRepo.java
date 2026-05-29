package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.enums.Degree;
import lv.venta.model.Professor;

public interface IProfessorRepo extends CrudRepository<Professor, Long> {

	boolean existsByDegree(Degree degree);
	
}
