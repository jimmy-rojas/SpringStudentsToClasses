package com.organization.springStudentsToClasses.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.StudentData;
import com.organization.springStudentsToClasses.storage.IStudentRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class StudentSaveServiceTest {

  private StudentSaveService instance;
  private IStudentRepository studentRepository;
  private StudentData studentBase;

  @Before
  public void setUp() {
    studentBase = new StudentData(1, "firstName", "lastName", new ArrayList<>());
    studentRepository = new IStudentRepository() {
      @Override
      public List<StudentData> getAllSearch(String firstName, String lastName) {
        List<StudentData> data = new ArrayList<>();
        data.add(new StudentData(1, firstName, lastName, new ArrayList<>()));
        return data;
      }

      @Override
      public List<StudentData> getAll() {
        return new ArrayList<>();
      }

      @Override
      public StudentData getById(int id) throws NotFoundException {
        return null;
      }

      @Override
      public StudentData save(StudentData student) {
        return new StudentData(1, "firstName", "lastName", new ArrayList<>());
      }

      @Override
      public StudentData update(int id, StudentData student) throws NotFoundException {
        if (id > 0) {
          return new StudentData(id, "firstName", "lastName", new ArrayList<>());
        }
        throw new NotFoundException("Not Found");
      }

      @Override
      public void delete(int studentId) throws NotFoundException {
        if (studentId < 1) {
          throw new NotFoundException("Not Found");
        }
      }
    };
    instance = new StudentSaveService(studentRepository);
  }

  @Test
  public void testGetAll() throws Exception {
    List<StudentData> allStudents = instance.getAll();
    assertNotNull(allStudents);
    assertEquals(0, allStudents.size());
  }

  @Test
  public void getAllSearch() throws Exception {
    List<StudentData> allStudents = instance.getAllSearch("firstName", "lastName");
    assertNotNull(allStudents);
    assertEquals(1, allStudents.size());
  }

  @Test
  public void testCreate() throws Exception {
    StudentData studentSaved = instance.save(studentBase);
    assertNotNull(studentSaved);
    assertEquals(1, studentSaved.getId());
  }

  @Test (expected = NotFoundException.class)
  public void testUpdate_NotFoundException() throws Exception {
    instance.update(0, studentBase);
  }

  @Test
  public void testUpdate() throws Exception {
    StudentData studentUpdated = instance.update(1, studentBase);
    assertNotNull(studentUpdated);
    assertEquals(1, studentUpdated.getId());
  }

  @Test (expected = NotFoundException.class)
  public void testDelete_NotFoundException() throws Exception {
    instance.delete(0);
  }

  @Test
  public void testDelete() throws Exception {
    instance.delete(1);
  }
}