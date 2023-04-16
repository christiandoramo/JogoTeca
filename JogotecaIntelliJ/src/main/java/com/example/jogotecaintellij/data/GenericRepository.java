package com.example.jogotecaintellij.data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.jogotecaintellij.exception.ElementAlreadyExistsException;
import com.example.jogotecaintellij.exception.ElementDoesNotExistException;

public class GenericRepository<T> implements IGenericRepository<T> {
    // ESSA É A CLASSE DE GERENCIAMENTO DOS DADOS
    protected List<T> elements;
    private final String fileName;
    private int ultimoId;

    @SuppressWarnings("unchecked")
    public GenericRepository(String fileName) {
        this.fileName = fileName;
        this.elements = new ArrayList<>();

        Object elementList = FileUtilRepository.ReadFromFile(this.fileName);
        if (elementList instanceof List<?>) {
            this.elements = (List<T>) elementList;
        }
        this.ultimoId = elements.size() + 1;
    }

    public void insert(T obj) throws ElementAlreadyExistsException {
        if (!this.elements.contains(obj))
            try {
                Field idField = obj.getClass().getDeclaredField("id");
                idField.setAccessible(true);
                idField.setInt(obj, ultimoId++);
                idField.setAccessible(false);
            } catch (Exception e) {
                throw new IllegalArgumentException("ALGUM ATRIBUTO id escrito ERRADO!!! - " + e);
            } finally {
                this.elements.add(obj);
            }
        else
            throw new ElementAlreadyExistsException(obj);
        FileUtilRepository.saveFile(elements, this.fileName);
    }

    public List<T> read() {
        return Collections.unmodifiableList(this.elements);
    }

    public void update(T obj) throws ElementDoesNotExistException {
        if (this.elements.contains(obj)) {
            int indice = this.elements.indexOf(obj);
            this.elements.set(indice, obj);
        } else {
            throw new ElementDoesNotExistException(obj);
        }
        FileUtilRepository.saveFile(elements, this.fileName);
    }

    public void delete(T obj) throws ElementDoesNotExistException {
        if (this.elements.contains(obj)) {
            this.elements.remove(this.elements.indexOf(obj));
        } else {
            throw new ElementDoesNotExistException(obj);
        }
        FileUtilRepository.saveFile(elements, this.fileName);
    }
}