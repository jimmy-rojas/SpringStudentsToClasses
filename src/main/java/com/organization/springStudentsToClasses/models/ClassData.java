package com.organization.springStudentsToClasses.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.List;

@JsonPropertyOrder({ "id", "code", "title", "description", "students" })
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
public class ClassData {

  private int id;
  private String code;
  private String title;
  private String description;
  private List<StudentData> students;

  public ClassData(int id, String code, String title, String description,
      List<StudentData> students) {
    this.id = id;
    this.code = code;
    this.title = title;
    this.description = description;
    this.students = students;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<StudentData> getStudents() {
    return students;
  }

  public void setStudents(
      List<StudentData> students) {
    this.students = students;
  }
}
