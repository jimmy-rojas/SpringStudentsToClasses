package com.organization.springStudentsToClasses.storage;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.ClassBase;
import com.organization.springStudentsToClasses.models.ClassWithId;
import java.util.List;

public interface IClassRepository {

  List<ClassWithId> getAll();

  ClassWithId save(ClassBase classBase);

  ClassWithId update(int id, ClassBase classBase) throws NotFoundException;

  void delete(int id) throws NotFoundException;

}
