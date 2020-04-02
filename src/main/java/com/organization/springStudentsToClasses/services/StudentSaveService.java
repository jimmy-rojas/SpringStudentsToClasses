package com.organization.springStudentsToClasses.services;

import com.organization.springStudentsToClasses.exceptions.InvalidOperationException;
import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.FullClassData;
import com.organization.springStudentsToClasses.models.FullStudentData;
import com.organization.springStudentsToClasses.models.StudentData;
import com.organization.springStudentsToClasses.storage.IStudentRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * StudentSaveService is a service class to manage Students features
 */
@Service
public class StudentSaveService {

  private final IStudentRepository repository;

  @Autowired
  public StudentSaveService(IStudentRepository repository) {
    this.repository = repository;
  }

  public List<FullStudentData> getAll() {
    return repository.getAll();
  }

  public FullStudentData getById(int id) throws NotFoundException {
    return repository.getById(id);
  }

  public FullStudentData save(StudentData studentBase) {
    FullStudentData studentData = new FullStudentData(0, studentBase.getFirstName(),
        studentBase.getLastName(), new ArrayList<>());
    return repository.save(studentData);
  }

  public FullStudentData update(StudentData studentBase)
      throws NotFoundException {
    FullStudentData studentData = getById(studentBase.getId());
    studentData.setFirstName(studentBase.getFirstName());
    studentData.setLastName(studentBase.getLastName());
    return repository.update(studentData);
  }

  public void delete(int id)
      throws NotFoundException, InvalidOperationException {
    FullStudentData studentData = getById(id);
    if (studentData.getClasses().isEmpty()) {
      repository.delete(id);
    } else {
      throw new InvalidOperationException("Unable to delete student with assigned classes");
    }
  }

  public List<FullStudentData> getAllSearch(String firstName, String lastName) {
    return repository.getAllSearch(firstName, lastName);
  }
}
