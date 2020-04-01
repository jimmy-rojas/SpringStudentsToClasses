package com.organization.springStudentsToClasses.storage;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.StudentData;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("default")
public class StudentRepository extends MockDataStorage implements IStudentRepository {

  @Override
  public List<StudentData> getAll() {
    return new ArrayList<StudentData>(super.studentMap.values());
  }

  @Override
  public StudentData getById(int id)
      throws NotFoundException {
    if (!super.studentMap.containsKey(id)) {
      throw new NotFoundException("unable to find student");
    }
    return super.studentMap.get(id);
  }

  @Override
  public StudentData save(StudentData student) {
    int newId = super.counterStudent.incrementAndGet();
    StudentData studentData = new StudentData(newId, student.getFirstName(),
        student.getLastName(), new ArrayList<>());
    super.studentMap.put(newId, studentData);
    return studentData;
  }

  @Override
  public StudentData update(int studentId, StudentData student)
      throws NotFoundException {
    StudentData studentData = getById(studentId);
    studentData.setFirstName(student.getFirstName());
    studentData.setLastName(student.getLastName());
    return studentData;
  }

  @Override
  public void delete(int studentId) throws NotFoundException {
    StudentData studentData = getById(studentId);
    super.studentMap.remove(studentData.getId());
  }

  @Override
  public List<StudentData> getAllSearch(String firstName, String lastName) {
    return super.studentMap.values()
        .parallelStream()
        .filter((student) ->
          student.getFirstName().equalsIgnoreCase(firstName)
              || student.getLastName().equalsIgnoreCase(lastName)
    ).collect(Collectors.toList());
  }
}
