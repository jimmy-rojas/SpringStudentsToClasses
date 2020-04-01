package com.organization.springStudentsToClasses.storage;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.ClassData;
import com.organization.springStudentsToClasses.models.StudentData;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("default")
public class AssignmentsRepository extends MockDataStorage implements IAssignmentsRepository {

  @Override
  public ClassData getClassStudents(int classId) throws NotFoundException {
    if (!super.classStudentMap.containsKey(classId)) {
      throw new NotFoundException("unable to find Class");
    }
    return super.classStudentMap.get(classId);
  }

  @Override
  public List<ClassData> getAllClasses() {
    return new ArrayList<>(super.classStudentMap.values());
  }

  @Override
  public StudentData getStudentClasses(int studentId) throws NotFoundException {
    if (!super.studentClassMap.containsKey(studentId)) {
      throw new NotFoundException("unable to find Student");
    }
    return super.studentClassMap.get(studentId);
  }

  @Override
  public List<StudentData> getAllStudents() {
    return new ArrayList<>(super.studentClassMap.values());
  }
}
