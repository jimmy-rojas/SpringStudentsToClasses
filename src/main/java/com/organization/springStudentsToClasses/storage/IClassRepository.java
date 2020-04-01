package com.organization.springStudentsToClasses.storage;

import com.organization.springStudentsToClasses.models.ClassData;
import java.util.List;

public interface IClassRepository extends IRepository<ClassData> {

  List<ClassData> getAllSearch(String code, String title, String description);
}
