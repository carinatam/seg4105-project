package ca.proj.controller;

public enum Page {
  ADMIN_HOME("admin-index"), REDIRECT("redirect:"), ADMIN_MANAGE_EMPLOYEES("admin-manage-employees"),
  ADMIN_VIEW_PATIENTS("admin-view-patients"), ADMIN_MANAGE_PATIENTS("admin-manage-patients");

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
