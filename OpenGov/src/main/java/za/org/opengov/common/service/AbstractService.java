package za.org.opengov.common.service;

import java.io.Serializable;
import java.util.List;

public interface AbstractService<E, I extends Serializable> {

	public E get(I identifier);
	
	public void remove(E entity);
	
	public List<E> getAll();
	
	public void put(E entity);
	
	public List<E> getPage(int page, int resultsPerPage);
	
}
