package ca.proj.values;

public enum AppointmentStatus {
  PENDING("Pending"),
  CONFIRMED("Confirmed"),
  CANCELLED("Cancelled");

  public final String label;

  private AppointmentStatus(String label) {
    this.label = label;
  }
}
