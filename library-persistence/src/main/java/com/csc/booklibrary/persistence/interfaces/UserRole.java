package com.csc.booklibrary.persistence.interfaces;

public enum UserRole {
  ADMIN(1, "admin"), USER(2, "user");

  private final int roleId;
  private final String roleName;

  private UserRole(final int roleId, final String roleName) {
    this.roleId = roleId;
    this.roleName = roleName;
  }

  public int getRoleId() {
    return roleId;
  }

  public String getRoleName() {
    return roleName;
  }

  public static String getRoleFromId(int roleId) {
    for (UserRole role : UserRole.values()) {
      if (role.getRoleId() == roleId) {
        return role.getRoleName();
      }
    }
    return null;
  }
}
