package com.organization.springStudentsToClasses.services;

import com.organization.springStudentsToClasses.exceptions.InvalidOperationException;
import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.ClassData;
import com.organization.springStudentsToClasses.models.FullClassData;
import com.organization.springStudentsToClasses.models.FullStudentData;
import com.organization.springStudentsToClasses.models.StudentData;
import com.organization.springStudentsToClasses.storage.IClassRepository;
import com.organization.springStudentsToClasses.storage.IStudentRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * StudentSaveService is a service class to manage Students features
 */
@Service
public class StudentSaveService {

  Logger logger = LoggerFactory.getLogger(StudentSaveService.class);

  private final IStudentRepository repository;
  private final IClassRepository classRepository;

  @Autowired
  public StudentSaveService(IStudentRepository repository, IClassRepository classRepository) {
    this.repository = repository;
    this.classRepository = classRepository;
  }

  public List<FullStudentData> getAll() {
    return repository.getAll();
  }

  public FullStudentData getById(int id) throws NotFoundException {
    return repository.getById(id);
  }

  public FullStudentData save(StudentData studentBase) {
    FullStudentData studentData = new FullStudentData(0, studentBase.getFirstName(),
        studentBase.getLastName(), new HashSet<>());
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

  public FullStudentData assignClassesToStudent(int id, Set<Integer> classIds) throws NotFoundException {
    FullStudentData studentData = getById(id);
    Set<ClassData> classes = new HashSet<>();
    classIds.forEach(classId -> {
      try {
        FullClassData fullClassData = classRepository.getById(classId);
        classes.add(new ClassData(fullClassData.getId(), fullClassData.getCode(),
            fullClassData.getTitle(), fullClassData.getDescription()));
      } catch (NotFoundException e) {
        logger.warn("Unable to find class with id: " + classId);
      }
    });
    studentData.setClasses(classes);
    return repository.update(studentData);
  }
}
