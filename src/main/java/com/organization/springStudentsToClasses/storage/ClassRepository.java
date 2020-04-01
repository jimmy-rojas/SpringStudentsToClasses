package com.organization.springStudentsToClasses.storage;

import com.organization.springStudentsToClasses.exceptions.NotFoundException;
import com.organization.springStudentsToClasses.models.ClassData;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("default")
public class ClassRepository extends MockDataStorage implements IClassRepository {

  @Override
  public List<ClassData> getAll() {
    return new ArrayList<ClassData>(super.classMap.values());
  }

  @Override
  public ClassData save(ClassData classBase) {
    int newId = super.counterClass.incrementAndGet();
    ClassData classData = new ClassData(newId, classBase.getCode(), classBase.getTitle(),
        classBase.getDescription(), new ArrayList<>());
    super.classMap.put(newId, classData);
    return classData;
  }

  @Override
  public ClassData update(int classId, ClassData classBase) throws NotFoundException {
    if (!super.classMap.containsKey(classId)) {
      throw new NotFoundException("unable to find student");
    }
    ClassData classData = super.classMap.get(classId);
    classData.setCode(classBase.getCode());
    classData.setTitle(classBase.getTitle());
    classData.setDescription(classBase.getDescription());
    return classData;
  }

  @Override
  public void delete(int classId) throws NotFoundException {
    if (!super.classMap.containsKey(classId)) {
      throw new NotFoundException("unable to find Class");
    }
    super.classMap.remove(classId);
  }

  @Override
  public List<ClassData> getAllSearch(String code, String title, String description) {
    return super.classMap.values()
        .stream()
        .filter((classWithId) ->
            classWithId.getCode().equalsIgnoreCase(code)
                || classWithId.getTitle().equalsIgnoreCase(title)
                || classWithId.getDescription().equalsIgnoreCase(description)
        ).collect(Collectors.toList());
  }
}
