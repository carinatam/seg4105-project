package ca.proj.controller;

public enum Page {
  ADMIN_DASHBOARD("admin-dashboard"), ADD_EMPLOYEE("add-employee"), ADD_PATIENT("add-patient"),
  ADD_APPOINTMENT("add-appointment"), REDIRECT("redirect:"),
  RECEPTIONIST_DASHBOARD("receptionist-dashboard"),
  DOCTOR_DASHBOARD("doctor-dashboard"),
  PATIENT_DASHBOARD("patient-dashboard"), LOGIN("login"),
  VIEW_EMPLOYEE("view-employee"), EDIT_APPOINTMENT("edit-appointment"),
  VIEW_PATIENT("view-patient"), VIEW_APPOINTMENT("view-appointment"), DOCTOR_ADD_PRESCRIPTION("doctor-add-prescription"),
  PATIENT_MAKE_PAYMENT("patient-make-payment");

  public final String url;

  private Page(String url) {
    this.url = url.toLowerCase();
  }

  public static String redirect(Page page) {
    return REDIRECT.getUrl() + page.getUrl();
  }

  public String getUrl() {
    return this.url;
  }

}
