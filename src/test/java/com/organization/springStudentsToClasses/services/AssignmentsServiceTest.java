package com.organization.springStudentsToClasses.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.ClassData;
import com.organization.springStudentsToClasses.models.StudentData;
import com.organization.springStudentsToClasses.storage.IAssignmentsRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class AssignmentsServiceTest {

  private AssignmentsService instance;
  private IAssignmentsRepository repository;

  @Before
  public void setUp() {
    repository = new IAssignmentsRepository() {
      @Override
      public ClassData getClassStudents(int id) throws NotFoundException {
        if (id > 0) {
          return new ClassData(id, "code", "title", "description",
              new ArrayList<>());
        }
        throw new NotFoundException("Not Found");
      }

      @Override
      public List<ClassData> getAllClasses() {
        return new ArrayList<>();
      }

      @Override
      public StudentData getStudentClasses(int id) throws NotFoundException {
        if (id > 0) {
          return new StudentData(id,"firstName", "lastName",
              new ArrayList<>());
        }
        throw new NotFoundException("Not Found");
      }

      @Override
      public List<StudentData> getAllStudents() {
        return new ArrayList<>();
      }
    };
    instance = new AssignmentsService(repository);
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
