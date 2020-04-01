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
  public StudentData save(StudentData student) {
    int newId = super.counterStudent.incrementAndGet();
    StudentData studentData = new StudentData(newId, student.getFirstName(),
        student.getLastName(), new ArrayList<>());
    super.studentMap.put(newId, studentData);
    return studentData;
  }

  @Override
  public StudentData update(int studentId, StudentData student) throws NotFoundException {
    if (!super.studentMap.containsKey(studentId)) {
      throw new NotFoundException("unable to find student");
    }
    StudentData studentData = super.studentMap.get(studentId);
    studentData.setFirstName(student.getFirstName());
    studentData.setLastName(student.getLastName());
    return studentData;
  }

  @Override
  public void delete(int studentId) throws NotFoundException {
    if (!super.studentMap.containsKey(studentId)) {
      throw new NotFoundException("unable to find student");
    }
    super.studentMap.remove(studentId);
  }

  @Override
  public List<StudentData> getAllSearch(String firstName, String lastName) {
    return super.studentMap.values()
        .stream()
        .filter((student) ->
          student.getFirstName().equalsIgnoreCase(firstName)
              || student.getLastName().equalsIgnoreCase(lastName)
    ).collect(Collectors.toList());
  }
}
