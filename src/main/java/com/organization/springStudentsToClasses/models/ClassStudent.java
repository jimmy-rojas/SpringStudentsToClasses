package com.organization.springStudentsToClasses.models;

import java.util.List;

public final class ClassStudent extends ClassWithId {

  private List<StudentWithId> studentList;

  public ClassStudent(int id, String code, String title, String description,
      List<StudentWithId> studentList) {
    super(id, code, title, description);
    this.studentList = studentList;
  }

  public List<StudentWithId> getStudentList() {
    return studentList;
  }

  public void setStudentList(
      List<StudentWithId> studentList) {
    this.studentList = studentList;
  }
}
