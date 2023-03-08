package br.jogoteca.system.data;

import java.util.List;

import br.jogoteca.system.exceptions.ElementAlreadyExistsException;
import br.jogoteca.system.exceptions.ElementDoesNotExistException;

public interface IGenericRepository<T> {
	void insert(T obj) throws ElementAlreadyExistsException;
	List<T> read();
	void update(T obj) throws ElementDoesNotExistException;
	void delete(T obj) throws ElementDoesNotExistException;
}
