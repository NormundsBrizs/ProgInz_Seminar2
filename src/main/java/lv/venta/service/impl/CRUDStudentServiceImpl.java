package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Grade;
import lv.venta.model.Student;
import lv.venta.repo.IGradeRepo;
import lv.venta.repo.IStudentRepo;
import lv.venta.service.ICRUDStudentService;

@Service
public class CRUDStudentServiceImpl implements ICRUDStudentService {

	@Autowired
	private IStudentRepo studRepo;
	
	@Autowired
	private IGradeRepo gradeRepo;
	
	@Override
	public void create(Student newObject) throws Exception {
		// TODO Auto-generated method stub
		
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
