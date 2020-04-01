package com.organization.springStudentsToClasses.storage;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.StudentData;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("default")
public class StudentRepository implements IStudentRepository {

  @Autowired
  private MockDataStorage storage;

  @Override
  public List<StudentData> getAll() {
    return new ArrayList<>(storage.getStudentClassMap().values());
  }

  @Override
  public StudentData getById(int id)
      throws NotFoundException {
    if (!storage.getStudentClassMap().containsKey(id)) {
      throw new NotFoundException("unable to find student");
    }
    return storage.getStudentClassMap().get(id);
  }

  @Override
  public StudentData save(StudentData student) {
    int newId = storage.counterStudent.incrementAndGet();
    student.setId(newId);
    storage.getStudentClassMap().put(newId, student);
    return student;
  }

  @Override
  public StudentData update(StudentData student)
      throws NotFoundException {
    storage.getStudentClassMap().put(student.getId(), student);
    return student;
  }

  @Override
  public void delete(int studentId) throws NotFoundException {
    StudentData studentData = getById(studentId);
    storage.getStudentClassMap().remove(studentData.getId());
  }

  @Override
  public List<StudentData> getAllSearch(String firstName, String lastName) {
    return storage.getStudentClassMap().values()
        .stream()
        .filter((student) ->
          student.getFirstName().equalsIgnoreCase(firstName)
              || student.getLastName().equalsIgnoreCase(lastName)
    ).collect(Collectors.toList());
  }
}
