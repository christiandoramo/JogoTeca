package com.example.jogotecaintellij.data;

import java.util.List;

import com.example.jogotecaintellij.exception.ElementAlreadyExistsException;
import com.example.jogotecaintellij.exception.ElementDoesNotExistException;

public interface IGenericRepository<T> {
	void insert(T obj) throws ElementAlreadyExistsException;
	List<T> read();
	void update(T obj) throws ElementDoesNotExistException;
	void delete(T obj) throws ElementDoesNotExistException;
}
