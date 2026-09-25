package lv.venta.service.impl;

import java.util.ArrayList;

import lv.venta.repo.ICourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Grade;
import lv.venta.model.Student;
import lv.venta.repo.IGradeRepo;
import lv.venta.repo.IStudentRepo;
import lv.venta.service.ICRUDStudentService;

@Service
public class CRUDStudentServiceImpl implements ICRUDStudentService {

	private final ICourseRepo ICourseRepo;

	@Autowired
	private IStudentRepo studRepo;
	
	@Autowired
	private IGradeRepo gradeRepo;

	CRUDStudentServiceImpl(ICourseRepo ICourseRepo) {
		this.ICourseRepo = ICourseRepo;
	}
	
	@Override
	public void create(Student newObject) throws Exception {
		if (newObject == null) {
			throw new Exception("No student given");
		}
		if (studRepo.existsByName(newObject.getName()) && studRepo.existsByName(newObject.getSurname())) {
			throw new Exception("Student already exists");
		}
		
		if (newObject.getName() == null || newObject.getSurname() == null) {
			throw new Exception("Invalid data");
		}
		
		studRepo.save(newObject);
	}

	@Override
	public ArrayList<Student> retrieveAll() throws Exception {
		if (studRepo.count() == 0) {
			throw new Exception("Student table empty");
		}
		return (ArrayList<Student>) studRepo.findAll();
	}

	@Override
	public Student retrieveById(long id) throws Exception {
		if (studRepo.count() == 0) {
			throw new Exception("No students found");
		}
		if (id < 1) {
			throw new Exception("ID can't be below 1");
		}
		if (!studRepo.existsById(id)) {
			throw new Exception("ID " + id + " doesn't exist");
		}
		return studRepo.findById(id).get();
	}

	@Override
	public Student deleteById(long id) throws Exception {
		Student studentToDelete = retrieveById(id);
		
		ArrayList<Grade> allGradesForStudent = gradeRepo.findByStudentIds(id);
		for (Grade tempG : allGradesForStudent) {
			tempG.setStudent(null);
			gradeRepo.save(tempG);
		}
		
		studRepo.delete(studentToDelete);
		return studentToDelete;
	}

	@Override
	public void updateById(long id, String name, String surname) throws Exception {
		
	}

}
