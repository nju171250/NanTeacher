package com.nju171250.njuTeacher.model;

import java.util.ArrayList;
import java.util.List;

public class TeacherExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table teacher
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table teacher
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table teacher
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    public TeacherExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table teacher
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andTeacherIdIsNull() {
            addCriterion("teacher_id is null");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIsNotNull() {
            addCriterion("teacher_id is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherIdEqualTo(String value) {
            addCriterion("teacher_id =", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotEqualTo(String value) {
            addCriterion("teacher_id <>", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdGreaterThan(String value) {
            addCriterion("teacher_id >", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdGreaterThanOrEqualTo(String value) {
            addCriterion("teacher_id >=", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdLessThan(String value) {
            addCriterion("teacher_id <", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdLessThanOrEqualTo(String value) {
            addCriterion("teacher_id <=", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdLike(String value) {
            addCriterion("teacher_id like", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotLike(String value) {
            addCriterion("teacher_id not like", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIn(List<String> values) {
            addCriterion("teacher_id in", values, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotIn(List<String> values) {
            addCriterion("teacher_id not in", values, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdBetween(String value1, String value2) {
            addCriterion("teacher_id between", value1, value2, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotBetween(String value1, String value2) {
            addCriterion("teacher_id not between", value1, value2, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherNameIsNull() {
            addCriterion("teacher_name is null");
            return (Criteria) this;
        }

        public Criteria andTeacherNameIsNotNull() {
            addCriterion("teacher_name is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherNameEqualTo(String value) {
            addCriterion("teacher_name =", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotEqualTo(String value) {
            addCriterion("teacher_name <>", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameGreaterThan(String value) {
            addCriterion("teacher_name >", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameGreaterThanOrEqualTo(String value) {
            addCriterion("teacher_name >=", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameLessThan(String value) {
            addCriterion("teacher_name <", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameLessThanOrEqualTo(String value) {
            addCriterion("teacher_name <=", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameLike(String value) {
            addCriterion("teacher_name like", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotLike(String value) {
            addCriterion("teacher_name not like", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameIn(List<String> values) {
            addCriterion("teacher_name in", values, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotIn(List<String> values) {
            addCriterion("teacher_name not in", values, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameBetween(String value1, String value2) {
            addCriterion("teacher_name between", value1, value2, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotBetween(String value1, String value2) {
            addCriterion("teacher_name not between", value1, value2, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherCollegenameIsNull() {
            addCriterion("teacher_collegeName is null");
            return (Criteria) this;
        }

        public Criteria andTeacherCollegenameIsNotNull() {
            addCriterion("teacher_collegeName is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherCollegenameEqualTo(String value) {
            addCriterion("teacher_collegeName =", value, "teacherCollegename");
            return (Criteria) this;
        }

        public Criteria andTeacherCollegenameNotEqualTo(String value) {
            addCriterion("teacher_collegeName <>", value, "teacherCollegename");
            return (Criteria) this;
        }

        public Criteria andTeacherCollegenameGreaterThan(String value) {
            addCriterion("teacher_collegeName >", value, "teacherCollegename");
            return (Criteria) this;
        }

        public Criteria andTeacherCollegenameGreaterThanOrEqualTo(String value) {
            addCriterion("teacher_collegeName >=", value, "teacherCollegename");
            return (Criteria) this;
        }

        public Criteria andTeacherCollegenameLessThan(String value) {
            addCriterion("teacher_collegeName <", value, "teacherCollegename");
            return (Criteria) this;
        }

        public Criteria andTeacherCollegenameLessThanOrEqualTo(String value) {
            addCriterion("teacher_collegeName <=", value, "teacherCollegename");
            return (Criteria) this;
        }

        public Criteria andTeacherCollegenameLike(String value) {
            addCriterion("teacher_collegeName like", value, "teacherCollegename");
            return (Criteria) this;
        }

        public Criteria andTeacherCollegenameNotLike(String value) {
            addCriterion("teacher_collegeName not like", value, "teacherCollegename");
            return (Criteria) this;
        }

        public Criteria andTeacherCollegenameIn(List<String> values) {
            addCriterion("teacher_collegeName in", values, "teacherCollegename");
            return (Criteria) this;
        }

        public Criteria andTeacherCollegenameNotIn(List<String> values) {
            addCriterion("teacher_collegeName not in", values, "teacherCollegename");
            return (Criteria) this;
        }

        public Criteria andTeacherCollegenameBetween(String value1, String value2) {
            addCriterion("teacher_collegeName between", value1, value2, "teacherCollegename");
            return (Criteria) this;
        }

        public Criteria andTeacherCollegenameNotBetween(String value1, String value2) {
            addCriterion("teacher_collegeName not between", value1, value2, "teacherCollegename");
            return (Criteria) this;
        }

        public Criteria andTeacherDepartmentIsNull() {
            addCriterion("teacher_department is null");
            return (Criteria) this;
        }

        public Criteria andTeacherDepartmentIsNotNull() {
            addCriterion("teacher_department is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherDepartmentEqualTo(String value) {
            addCriterion("teacher_department =", value, "teacherDepartment");
            return (Criteria) this;
        }

        public Criteria andTeacherDepartmentNotEqualTo(String value) {
            addCriterion("teacher_department <>", value, "teacherDepartment");
            return (Criteria) this;
        }

        public Criteria andTeacherDepartmentGreaterThan(String value) {
            addCriterion("teacher_department >", value, "teacherDepartment");
            return (Criteria) this;
        }

        public Criteria andTeacherDepartmentGreaterThanOrEqualTo(String value) {
            addCriterion("teacher_department >=", value, "teacherDepartment");
            return (Criteria) this;
        }

        public Criteria andTeacherDepartmentLessThan(String value) {
            addCriterion("teacher_department <", value, "teacherDepartment");
            return (Criteria) this;
        }

        public Criteria andTeacherDepartmentLessThanOrEqualTo(String value) {
            addCriterion("teacher_department <=", value, "teacherDepartment");
            return (Criteria) this;
        }

        public Criteria andTeacherDepartmentLike(String value) {
            addCriterion("teacher_department like", value, "teacherDepartment");
            return (Criteria) this;
        }

        public Criteria andTeacherDepartmentNotLike(String value) {
            addCriterion("teacher_department not like", value, "teacherDepartment");
            return (Criteria) this;
        }

        public Criteria andTeacherDepartmentIn(List<String> values) {
            addCriterion("teacher_department in", values, "teacherDepartment");
            return (Criteria) this;
        }

        public Criteria andTeacherDepartmentNotIn(List<String> values) {
            addCriterion("teacher_department not in", values, "teacherDepartment");
            return (Criteria) this;
        }

        public Criteria andTeacherDepartmentBetween(String value1, String value2) {
            addCriterion("teacher_department between", value1, value2, "teacherDepartment");
            return (Criteria) this;
        }

        public Criteria andTeacherDepartmentNotBetween(String value1, String value2) {
            addCriterion("teacher_department not between", value1, value2, "teacherDepartment");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreIsNull() {
            addCriterion("teacher_score is null");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreIsNotNull() {
            addCriterion("teacher_score is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreEqualTo(Double value) {
            addCriterion("teacher_score =", value, "teacherScore");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreNotEqualTo(Double value) {
            addCriterion("teacher_score <>", value, "teacherScore");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreGreaterThan(Double value) {
            addCriterion("teacher_score >", value, "teacherScore");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreGreaterThanOrEqualTo(Double value) {
            addCriterion("teacher_score >=", value, "teacherScore");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreLessThan(Double value) {
            addCriterion("teacher_score <", value, "teacherScore");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreLessThanOrEqualTo(Double value) {
            addCriterion("teacher_score <=", value, "teacherScore");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreIn(List<Double> values) {
            addCriterion("teacher_score in", values, "teacherScore");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreNotIn(List<Double> values) {
            addCriterion("teacher_score not in", values, "teacherScore");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreBetween(Double value1, Double value2) {
            addCriterion("teacher_score between", value1, value2, "teacherScore");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreNotBetween(Double value1, Double value2) {
            addCriterion("teacher_score not between", value1, value2, "teacherScore");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table teacher
     *
     * @mbggenerated do_not_delete_during_merge Sat Mar 16 14:42:46 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table teacher
     *
     * @mbggenerated Sat Mar 16 14:42:46 CST 2019
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}