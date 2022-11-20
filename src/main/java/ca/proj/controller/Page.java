package ca.proj.controller;

public enum Page {
  ADMIN_DASHBOARD("admin-dashboard"), ADMIN_ADD_EMPLOYEE("admin-add-employee"), ADMIN_ADD_PATIENT("admin-add-patient"),
  ADMIN_ADD_APPOINTMENT("admin-add-appointment"), REDIRECT("redirect:");

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
