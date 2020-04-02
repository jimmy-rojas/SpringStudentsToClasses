package com.organization.springStudentsToClasses.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonPropertyOrder({ "id", "firstName", "lastName", "classes" })
public class FullStudentData extends StudentData {

  private List<ClassData> classes;

  public FullStudentData(int id, String firstName, String lastName,
      List<ClassData> classes) {
    super(id,firstName,lastName);
    this.classes = classes;
  }

  public List<ClassData> getClasses() {
    return classes;
  }

  public void setClasses(
      List<ClassData> classes) {
    this.classes = classes;
  }
}
