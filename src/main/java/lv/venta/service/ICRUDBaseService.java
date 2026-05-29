package lv.venta.service;

import java.util.ArrayList;

public interface ICRUDBaseService<Ttype> {
	
	public abstract ArrayList<Ttype> retrieveAll() throws Exception;
	
	public abstract Ttype retrieveById(long id) throws Exception;
	
	public abstract Ttype deleteById(long id) throws Exception;
}
