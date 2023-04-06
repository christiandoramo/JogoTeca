package br.jogoteca.system.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.jogoteca.system.exceptions.ElementAlreadyExistsException;
import br.jogoteca.system.exceptions.ElementDoesNotExistException;

public class GenericRepository<T> implements IGenericRepository<T> {
	protected List<T> elements;
	private String fileName;
	
	@SuppressWarnings("unchecked")
	public GenericRepository(String fileName) {
		this.fileName = fileName;
		this.elements = new ArrayList<>();
		
		Object elementList = FileUtilRepository.ReadFromFile(this.fileName);
		if(elementList != null && elementList instanceof List<?>) {
			this.elements = (List<T>) elementList;
		}
		
	}
	
	
	public void insert(T obj) throws ElementAlreadyExistsException{
        if (!this.elements.contains(obj)) {
            this.elements.add(obj);
        } else {
            throw new ElementAlreadyExistsException(obj);
        }
        
        FileUtilRepository.saveFile(elements, this.fileName);
	}
	public List<T> read() {
		return Collections.unmodifiableList(this.elements);
	}
	public void update(T obj)throws ElementDoesNotExistException{
        if (this.elements.contains(obj)) {
            int indice = this.elements.indexOf(obj);
            this.elements.set(indice, obj);
        } else {
            throw new ElementDoesNotExistException(obj);
        }
        FileUtilRepository.saveFile(elements, this.fileName);
	}
	
	public void delete(T obj) throws ElementDoesNotExistException{
        if (this.elements.contains(obj)) {
            this.elements.remove(this.elements.indexOf(obj));
        } else {
            throw new ElementDoesNotExistException(obj);
        }
        
        FileUtilRepository.saveFile(elements, this.fileName);
	}
}
