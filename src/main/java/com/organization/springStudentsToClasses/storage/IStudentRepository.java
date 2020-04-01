package com.organization.springStudentsToClasses.storage;

import com.organization.springStudentsToClasses.models.StudentData;
import java.util.List;

public interface IStudentRepository extends IRepository<StudentData> {

  List<StudentData> getAllSearch(String firstName, String lastName);
}
