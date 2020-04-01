package com.organization.springStudentsToClasses.services;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.ClassData;
import com.organization.springStudentsToClasses.models.StudentData;
import com.organization.springStudentsToClasses.storage.ClassRepository;
import com.organization.springStudentsToClasses.storage.IClassRepository;
import com.organization.springStudentsToClasses.storage.IStudentRepository;
import com.organization.springStudentsToClasses.storage.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AssignmentsService is a service class to manage assignments for Students and Classes
 */
@Service
public class AssignmentsService {

  private final IClassRepository classRepository;
  private final IStudentRepository studentRepository;

  @Autowired
  public AssignmentsService(IClassRepository classRepository, IStudentRepository studentRepository) {
    this.classRepository = classRepository;
    this.studentRepository = studentRepository;
  }

  public ClassData getClassStudents(int id)
      throws NotFoundException {
    return this.classRepository.getById(id);
  }

  public List<ClassData> getAllClasses() {
    return this.classRepository.getAll();
  }

  public StudentData getStudentClasses(int id)
      throws NotFoundException {
    return this.studentRepository.getById(id);
  }

  public List<StudentData> getAllStudents() {
    return this.studentRepository.getAll();
  }
}
