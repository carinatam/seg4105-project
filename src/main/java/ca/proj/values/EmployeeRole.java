package ca.proj.values;

public enum EmployeeRole {
  Doctor("Doctor"),
  Receptionist("Receptionist"),
  Admin("Admin");

  public final String label;

  private EmployeeRole(String label) {
    this.label = label;
  }
}
