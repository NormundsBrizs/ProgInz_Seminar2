package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.dto.GradeDTO;

public interface IGradeService {
	
	public abstract void addNewGrade(GradeDTO gradeDTO) throws Exception;
	
	public abstract void addNewGrades(ArrayList<GradeDTO> gradesDTO) throws Exception;

}
