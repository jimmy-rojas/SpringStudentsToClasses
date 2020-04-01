package com.organization.springStudentsToClasses.services;

import com.organization.springStudentsToClasses.exceptions.InvalidOperationException;
import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.StudentData;
import com.organization.springStudentsToClasses.storage.IStudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * StudentSaveService is a service class to manage Students features
 */
@Service
public class StudentSaveService implements IStudentRepository {

  private final IStudentRepository repository;

  @Autowired
  public StudentSaveService(IStudentRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<StudentData> getAll() {
    return repository.getAll();
  }

  @Override
  public StudentData getById(int id) throws NotFoundException {
    return repository.getById(id);
  }

  @Override
  public StudentData save(StudentData studentBase) {
    return repository.save(studentBase);
  }

  @Override
  public StudentData update(StudentData studentBase)
      throws NotFoundException {
    StudentData studentData = getById(studentBase.getId());
    studentData.setFirstName(studentBase.getFirstName());
    studentData.setLastName(studentBase.getLastName());
    return repository.update(studentData);
  }

  @Override
  public void delete(int id)
      throws NotFoundException, InvalidOperationException {
    StudentData studentData = getById(id);
    if (studentData.getClasses().isEmpty()) {
      repository.delete(id);
    } else {
      throw new InvalidOperationException("Unable to delete student with assigned classes");
    }
  }

  @Override
  public List<StudentData> getAllSearch(String firstName, String lastName) {
    return repository.getAllSearch(firstName, lastName);
  }
}
