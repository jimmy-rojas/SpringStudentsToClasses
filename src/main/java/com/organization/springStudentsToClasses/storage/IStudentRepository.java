package com.organization.springStudentsToClasses.storage;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.StudentBase;
import com.organization.springStudentsToClasses.models.StudentWithId;
import java.util.List;

public interface IStudentRepository {

  List<StudentWithId> getAll();

  StudentWithId save(StudentBase studentBase);

  StudentWithId update(int id, StudentBase studentBase) throws NotFoundException;

  void delete(int id) throws NotFoundException;

}
