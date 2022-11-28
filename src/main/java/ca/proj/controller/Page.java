package ca.proj.controller;

public enum Page {
  ADMIN_DASHBOARD("admin-dashboard"), ADMIN_ADD_EMPLOYEE("admin-add-employee"), ADMIN_ADD_PATIENT("admin-add-patient"),
  ADMIN_ADD_APPOINTMENT("admin-add-appointment"), REDIRECT("redirect:"),
  RECEPTIONIST_DASHBOARD("receptionist-dashboard"), RECEPTIONIST_ADD_APPOINTMENT("receptionist-add-appointment"),
  RECEPTIONIST_ADD_PATIENT("receptionist-add-patient"),
  RECEPTIONIST_ADD_EMPLOYEE("receptionist-add-employee"), DOCTOR_DASHBOARD("doctor-dashboard"),
  PATIENT_DASHBOARD("patient-dashboard"), PATIENT_ADD_APPOINTMENT("patient-add-appointment"), LOGIN("login"),
  ADMIN_VIEW_EMPLOYEE("admin-view-employee"), ADMIN_EDIT_APPOINTMENT("admin-edit-appointment"),
  ADMIN_VIEW_PATIENT("admin-view-patient"), ADMIN_VIEW_APPOINTMENT("admin-view-appointment");

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
