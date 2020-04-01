package com.organization.springStudentsToClasses.services;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.ClassData;
import com.organization.springStudentsToClasses.models.StudentData;
import com.organization.springStudentsToClasses.storage.IAssignmentsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AssignmentsService is a service class to manage assignments for Students and Classes
 */
@Service
public class AssignmentsService implements IAssignmentsRepository {

  private IAssignmentsRepository repository;

  @Autowired
  public AssignmentsService(IAssignmentsRepository repository) {
    this.repository = repository;
  }

  @Override
  public ClassData getClassStudents(int id)
      throws NotFoundException {
    return this.repository.getClassStudents(id);
  }

  @Override
  public List<ClassData> getAllClasses() {
    return this.repository.getAllClasses();
  }

  @Override
  public StudentData getStudentClasses(int id)
      throws NotFoundException {
    return this.repository.getStudentClasses(id);
  }

  @Override
  public List<StudentData> getAllStudents() {
    return this.repository.getAllStudents();
  }
}
