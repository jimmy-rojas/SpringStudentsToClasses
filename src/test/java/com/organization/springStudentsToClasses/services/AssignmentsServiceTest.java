package com.organization.springStudentsToClasses.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.ClassData;
import com.organization.springStudentsToClasses.models.StudentData;
import com.organization.springStudentsToClasses.storage.IClassRepository;
import com.organization.springStudentsToClasses.storage.IStudentRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class AssignmentsServiceTest {

  private AssignmentsService instance;

  private IClassRepository classRepository;
  private IStudentRepository studentRepository;

  @Before
  public void setUp() {
    classRepository = new IClassRepository() {
      @Override
      public List<ClassData> getAllSearch(String code, String title, String description) {
        return new ArrayList<>();
      }

      @Override
      public List<ClassData> getAll() {
        return new ArrayList<>();
      }

      @Override
      public ClassData getById(int id) throws NotFoundException {
        if (id > 0) {
          return new ClassData(id, "code", "title", "description",
              new ArrayList<>());
        }
        throw new NotFoundException("Not Found");
      }

      @Override
      public ClassData save(ClassData dataToSave) {
        return dataToSave;
      }

      @Override
      public ClassData update(int id, ClassData dataToUpdate) throws NotFoundException {
        return dataToUpdate;
      }

      @Override
      public void delete(int id) throws NotFoundException {

      }
    };

    studentRepository = new IStudentRepository() {
      @Override
      public List<StudentData> getAllSearch(String firstName, String lastName) {
        return new ArrayList<>();
      }

      @Override
      public List<StudentData> getAll() {
        return new ArrayList<>();
      }

      @Override
      public StudentData getById(int id) throws NotFoundException {
        if (id > 0) {
          return new StudentData(id,"firstName", "lastName",
              new ArrayList<>());
        }
        throw new NotFoundException("Not Found");
      }

      @Override
      public StudentData save(StudentData dataToSave) {
        return dataToSave;
      }

      @Override
      public StudentData update(int id, StudentData dataToUpdate) throws NotFoundException {
        return dataToUpdate;
      }

      @Override
      public void delete(int id) throws NotFoundException {

      }
    };

    instance = new AssignmentsService(classRepository, studentRepository);
  }

  @Test
  public void getAllStudents() throws Exception {
    List<StudentData> allStudentsList = instance.getAllStudents();
    assertNotNull(allStudentsList);
    assertEquals(0, allStudentsList.size());
  }

  @Test (expected = NotFoundException.class)
  public void getStudentClasses_NotFoundException() throws Exception {
    instance.getStudentClasses(-1);
  }

  @Test
  public void getStudentClasses() throws Exception {
    StudentData studentClasses = instance.getStudentClasses(1);
    assertNotNull(studentClasses);
    assertEquals(1, studentClasses.getId());
  }

  @Test
  public void getAllClasses() throws Exception {
    List<ClassData> allClassesList = instance.getAllClasses();
    assertNotNull(allClassesList);
    assertEquals(0, allClassesList.size());
  }

  @Test (expected = NotFoundException.class)
  public void getClassStudents_NotFoundException() throws Exception {
    instance.getClassStudents(0);
  }

  @Test
  public void getClassStudents() throws Exception {
    ClassData classStudents = instance.getClassStudents(1);
    assertNotNull(classStudents);
    assertEquals(1, classStudents.getId());
  }
}
