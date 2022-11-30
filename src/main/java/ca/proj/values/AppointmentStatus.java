package ca.proj.values;

public enum AppointmentStatus {
  Pending("Pending"),
  Confirmed("Confirmed"),
  Cancelled("Cancelled");

  public final String label;

  private AppointmentStatus(String label) {
    this.label = label;
  }
}
