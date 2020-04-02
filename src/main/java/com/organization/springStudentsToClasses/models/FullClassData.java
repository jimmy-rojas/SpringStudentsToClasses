package com.organization.springStudentsToClasses.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonPropertyOrder({ "id", "code", "title", "description", "students" })
public class FullClassData extends ClassData {

  private List<StudentData> students;

  public FullClassData(int id, String code, String title, String description,
      List<StudentData> students) {
    super(id, code, title, description);
    this.students = students;
  }

  public List<StudentData> getStudents() {
    return students;
  }

  public void setStudents(
      List<StudentData> students) {
    this.students = students;
  }
}
