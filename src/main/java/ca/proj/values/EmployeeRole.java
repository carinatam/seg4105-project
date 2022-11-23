package ca.proj.values;

public enum EmployeeRole {
  DOCTOR("Doctor"),
  RECEPTIONIST("Receptionist"),
  ADMIN("Admin");

  public final String label;

  private EmployeeRole(String label) {
    this.label = label;
  }
}
