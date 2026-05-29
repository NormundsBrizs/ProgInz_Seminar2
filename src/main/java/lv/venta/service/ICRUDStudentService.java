package lv.venta.service;

import lv.venta.model.Student;

public interface ICRUDStudentService extends ICRUDBaseService<Student> {
	
	public abstract void updateById(long id, String name, String surname) throws Exception;
}
