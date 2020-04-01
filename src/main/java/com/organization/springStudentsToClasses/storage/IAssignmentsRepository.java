package com.organization.springStudentsToClasses.storage;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.ClassData;
import com.organization.springStudentsToClasses.models.StudentData;
import java.util.List;

public interface IAssignmentsRepository {

  ClassData getClassStudents(int id) throws NotFoundException;

  List<ClassData> getAllClasses();

  StudentData getStudentClasses(int id) throws NotFoundException;

  List<StudentData> getAllStudents();
}
