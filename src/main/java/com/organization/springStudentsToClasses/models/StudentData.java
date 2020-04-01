package com.organization.springStudentsToClasses.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.List;

@JsonPropertyOrder({ "id", "firstName", "lastName", "classes" })
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
public class StudentData {

  private int id;
  private String firstName;
  private String lastName;
  private List<ClassData> classes;

  public StudentData(int id, String firstName, String lastName,
      List<ClassData> classes) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.classes = classes;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public List<ClassData> getClasses() {
    return classes;
  }

  public void setClasses(
      List<ClassData> classes) {
    this.classes = classes;
  }
}
