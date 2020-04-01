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
    StudentData studentData = new StudentData(newId, student.getFirstName(),
        student.getLastName(), new ArrayList<>());
    storage.getStudentClassMap().put(newId, studentData);
    return studentData;
  }

  @Override
  public StudentData update(StudentData student)
      throws NotFoundException {
    StudentData studentData = getById(student.getId());
    studentData.setFirstName(student.getFirstName());
    studentData.setLastName(student.getLastName());
    return studentData;
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
