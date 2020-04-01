package com.organization.springStudentsToClasses.services;

import com.organization.springStudentsToClasses.exceptions.InvalidOperationException;
import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.ClassData;
import com.organization.springStudentsToClasses.storage.IClassRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassSaveService is a service class to manage Classes features
 */
@Service
public class ClassSaveService implements IClassRepository {

  private final IClassRepository repository;

  @Autowired
  public ClassSaveService(IClassRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<ClassData> getAll() {
    return repository.getAll();
  }

  @Override
  public ClassData getById(int id) throws NotFoundException {
    return repository.getById(id);
  }

  @Override
  public ClassData save(ClassData classBase) {
    return repository.save(classBase);
  }

  @Override
  public ClassData update(ClassData classBase)
      throws NotFoundException {
    ClassData classData = getById(classBase.getId());
    classData.setCode(classBase.getCode());
    classData.setTitle(classBase.getTitle());
    classData.setDescription(classBase.getDescription());
    return repository.update(classData);
  }

  @Override
  public void delete(int id)
      throws NotFoundException, InvalidOperationException {
    ClassData classData = getById(id);
    if (classData.getStudents().isEmpty()) {
      repository.delete(id);
    } else {
      throw new InvalidOperationException("Unable to delete class with assigned students");
    }
  }

  @Override
  public List<ClassData> getAllSearch(String code, String title, String description) {
    return repository.getAllSearch(code, title, description);
  }
}
