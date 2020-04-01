package com.organization.springStudentsToClasses.storage;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import java.util.List;

public interface IRepository<T> {

  List<T> getAll();

  T save(T dataToSave);

  T update(int id, T dataToUpdate) throws NotFoundException;

  void delete(int id) throws NotFoundException;

}
