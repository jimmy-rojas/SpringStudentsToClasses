package com.organization.springStudentsToClasses.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.organization.springStudentsToClasses.exceptions.InvalidOperationException;
import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.ClassData;
import com.organization.springStudentsToClasses.storage.IClassRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClassSaveServiceTest {

  private ClassSaveService instance;
  private IClassRepository classRepository;
  private ClassData classBase;

  @Before
  public void setUp() {
    classBase = new ClassData(1, "code", "title", "description", new ArrayList<>());
    classRepository = new IClassRepository() {
      @Override
      public List<ClassData> getAllSearch(String code, String title, String description) {
        List<ClassData> data = new ArrayList<>();
        data.add(new ClassData(1, code, title, description, new ArrayList<>()));
        return data;
      }

      @Override
      public List<ClassData> getAll() {
        return new ArrayList<>();
      }

      @Override
      public ClassData getById(int id) throws NotFoundException {
        return new ClassData(id, "code", "title", "description", new ArrayList<>());
      }

      @Override
      public ClassData save(ClassData classBase) {
        return new ClassData(1, "code", "title", "description", new ArrayList<>());
      }

      @Override
      public ClassData update(ClassData classBase) throws NotFoundException {
        if (classBase.getId() > 0) {
          return new ClassData(classBase.getId(), classBase.getCode(), classBase.getTitle(),
              classBase.getDescription(), new ArrayList<>());
        }
        throw new NotFoundException("Not Found");
      }

      @Override
      public void delete(int classId) throws NotFoundException, InvalidOperationException {
        if (classId < 1) {
          throw new NotFoundException("Not Found");
        }
      }
    };
    instance = new ClassSaveService(classRepository);
  }

  @Test
  public void testGetAll() {
    List<ClassData> allClasses = instance.getAll();
    assertNotNull(allClasses);
    assertEquals(0, allClasses.size());
  }

  @Test
  public void getAllSearch() throws Exception {
    List<ClassData> allClasses = instance.getAllSearch("code", "title", "description");
    assertNotNull(allClasses);
    Assert.assertEquals(1, allClasses.size());
  }

  @Test
  public void testCreate() {
    ClassData classSaved = instance.save(classBase);
    assertNotNull(classSaved);
    assertEquals(1, classSaved.getId());
  }

  @Test (expected = NotFoundException.class)
  public void testUpdate_NotFoundException() throws Exception {
    classBase.setId(-1);
    instance.update(classBase);
  }

  @Test
  public void testUpdate() throws Exception {
    ClassData classUpdated = instance.update(classBase);
    assertNotNull(classUpdated);
    assertEquals(1, classUpdated.getId());
  }

  @Test (expected = NotFoundException.class)
  public void testDelete_NotFoundException() throws Exception {
    instance.delete(-1);
  }

  @Test
  public void testDelete() throws Exception {
    instance.delete(1);
  }
}