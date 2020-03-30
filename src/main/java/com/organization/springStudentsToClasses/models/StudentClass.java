package com.organization.springStudentsToClasses.models;

import java.util.List;

public final class StudentClass extends StudentWithId {

  private List<ClassWithId> classList;

  public StudentClass(int id, String firstName, String lastName,
      List<ClassWithId> classList) {
    super(id, firstName, lastName);
    this.classList = classList;
  }

  public List<ClassWithId> getClassList() {
    return classList;
  }

  public void setClassList(
      List<ClassWithId> classList) {
    this.classList = classList;
  }
}
