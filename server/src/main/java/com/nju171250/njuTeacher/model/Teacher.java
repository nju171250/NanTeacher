package com.nju171250.njuTeacher.model;

public class Teacher {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.teacher_id
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    private String teacherId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.teacher_name
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    private String teacherName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.teacher_collegeName
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    private String teacherCollegename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.teacher_department
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    private String teacherDepartment;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.teacher_score
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    private Double teacherScore;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    public Teacher(String teacherId, String teacherName, String teacherCollegename, String teacherDepartment, Double teacherScore) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherCollegename = teacherCollegename;
        this.teacherDepartment = teacherDepartment;
        this.teacherScore = teacherScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    public Teacher() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.teacher_id
     *
     * @return the value of teacher.teacher_id
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.teacher_id
     *
     * @param teacherId the value for teacher.teacher_id
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.teacher_name
     *
     * @return the value of teacher.teacher_name
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.teacher_name
     *
     * @param teacherName the value for teacher.teacher_name
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.teacher_collegeName
     *
     * @return the value of teacher.teacher_collegeName
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    public String getTeacherCollegename() {
        return teacherCollegename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.teacher_collegeName
     *
     * @param teacherCollegename the value for teacher.teacher_collegeName
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    public void setTeacherCollegename(String teacherCollegename) {
        this.teacherCollegename = teacherCollegename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.teacher_department
     *
     * @return the value of teacher.teacher_department
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    public String getTeacherDepartment() {
        return teacherDepartment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.teacher_department
     *
     * @param teacherDepartment the value for teacher.teacher_department
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    public void setTeacherDepartment(String teacherDepartment) {
        this.teacherDepartment = teacherDepartment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.teacher_score
     *
     * @return the value of teacher.teacher_score
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    public Double getTeacherScore() {
        return teacherScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.teacher_score
     *
     * @param teacherScore the value for teacher.teacher_score
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    public void setTeacherScore(Double teacherScore) {
        this.teacherScore = teacherScore;
    }
}