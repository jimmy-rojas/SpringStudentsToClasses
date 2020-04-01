package com.organization.springStudentsToClasses.storage;

import com.organization.springStudentsToClasses.models.ClassData;
import com.organization.springStudentsToClasses.models.StudentData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class MockDataStorage {

  private static Map<Integer, ClassData> classStudentMap = new HashMap<>();
  private static Map<Integer, StudentData> studentClassMap = new HashMap<>();

  public static final AtomicInteger counterStudent = new AtomicInteger();
  public static final AtomicInteger counterClass = new AtomicInteger();

  {
    Map<Integer, StudentData> studentMap = new HashMap<>();
    Map<Integer, ClassData> classMap = new HashMap<>();

    for (int i=0; i<5; i++) {
      StudentData studentData = new StudentData(i,"firstName-"+i, "lastName-"+i, new ArrayList<>());
      studentMap.put(i, studentData);
      counterStudent.incrementAndGet();

      StudentData studentClass = new StudentData(studentData.getId(),
          studentData.getFirstName(), studentData.getLastName(), new ArrayList<>());
      studentClassMap.put(i, studentClass);
    }
    for (int i=0; i<3; i++) {
      ClassData classData = new ClassData(i,"code-"+i, "title-"+i, "description-"+i, new ArrayList<>());
      classMap.put(i, classData);
      counterClass.incrementAndGet();
      int rand = new Random().nextInt(studentMap.size());
      List<StudentData> studentList = new ArrayList<>();
      for (int j=0; j<=rand; j++) {
        studentList.add(studentMap.get(j));
        studentClassMap.get(j).getClasses().add(classData);
      }
      ClassData classStudent = new ClassData(classData.getId(), classData.getCode(),
          classData.getTitle(), classData.getDescription(), studentList);
      classStudentMap.put(i, classStudent);
    }
  }

  public Map<Integer, ClassData> getClassStudentMap() {
    return classStudentMap;
  }

  public Map<Integer, StudentData> getStudentClassMap() {
    return studentClassMap;
  }
}
