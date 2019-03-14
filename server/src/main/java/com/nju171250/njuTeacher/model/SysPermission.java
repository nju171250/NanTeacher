package com.nju171250.njuTeacher.model;

public class SysPermission {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_permission.permission_id
     *
     * @mbggenerated Mon Mar 11 22:34:17 CST 2019
     */
    private String permissionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_permission.name
     *
     * @mbggenerated Mon Mar 11 22:34:17 CST 2019
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_permission.description
     *
     * @mbggenerated Mon Mar 11 22:34:17 CST 2019
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_permission.url
     *
     * @mbggenerated Mon Mar 11 22:34:17 CST 2019
     */
    private String url;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbggenerated Mon Mar 11 22:34:17 CST 2019
     */
    public SysPermission(String permissionId, String name, String description, String url) {
        this.permissionId = permissionId;
        this.name = name;
        this.description = description;
        this.url = url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbggenerated Mon Mar 11 22:34:17 CST 2019
     */
    public SysPermission() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_permission.permission_id
     *
     * @return the value of sys_permission.permission_id
     *
     * @mbggenerated Mon Mar 11 22:34:17 CST 2019
     */
    public String getPermissionId() {
        return permissionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_permission.permission_id
     *
     * @param permissionId the value for sys_permission.permission_id
     *
     * @mbggenerated Mon Mar 11 22:34:17 CST 2019
     */
    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_permission.name
     *
     * @return the value of sys_permission.name
     *
     * @mbggenerated Mon Mar 11 22:34:17 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_permission.name
     *
     * @param name the value for sys_permission.name
     *
     * @mbggenerated Mon Mar 11 22:34:17 CST 2019
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_permission.description
     *
     * @return the value of sys_permission.description
     *
     * @mbggenerated Mon Mar 11 22:34:17 CST 2019
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_permission.description
     *
     * @param description the value for sys_permission.description
     *
     * @mbggenerated Mon Mar 11 22:34:17 CST 2019
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_permission.url
     *
     * @return the value of sys_permission.url
     *
     * @mbggenerated Mon Mar 11 22:34:17 CST 2019
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_permission.url
     *
     * @param url the value for sys_permission.url
     *
     * @mbggenerated Mon Mar 11 22:34:17 CST 2019
     */
    public void setUrl(String url) {
        this.url = url;
    }
}