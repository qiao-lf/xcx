package henu.xmh.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseCounterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CourseCounterExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andCounterIdIsNull() {
            addCriterion("counter_id is null");
            return (Criteria) this;
        }

        public Criteria andCounterIdIsNotNull() {
            addCriterion("counter_id is not null");
            return (Criteria) this;
        }

        public Criteria andCounterIdEqualTo(String value) {
            addCriterion("counter_id =", value, "counterId");
            return (Criteria) this;
        }

        public Criteria andCounterIdNotEqualTo(String value) {
            addCriterion("counter_id <>", value, "counterId");
            return (Criteria) this;
        }

        public Criteria andCounterIdGreaterThan(String value) {
            addCriterion("counter_id >", value, "counterId");
            return (Criteria) this;
        }

        public Criteria andCounterIdGreaterThanOrEqualTo(String value) {
            addCriterion("counter_id >=", value, "counterId");
            return (Criteria) this;
        }

        public Criteria andCounterIdLessThan(String value) {
            addCriterion("counter_id <", value, "counterId");
            return (Criteria) this;
        }

        public Criteria andCounterIdLessThanOrEqualTo(String value) {
            addCriterion("counter_id <=", value, "counterId");
            return (Criteria) this;
        }

        public Criteria andCounterIdLike(String value) {
            addCriterion("counter_id like", value, "counterId");
            return (Criteria) this;
        }

        public Criteria andCounterIdNotLike(String value) {
            addCriterion("counter_id not like", value, "counterId");
            return (Criteria) this;
        }

        public Criteria andCounterIdIn(List<String> values) {
            addCriterion("counter_id in", values, "counterId");
            return (Criteria) this;
        }

        public Criteria andCounterIdNotIn(List<String> values) {
            addCriterion("counter_id not in", values, "counterId");
            return (Criteria) this;
        }

        public Criteria andCounterIdBetween(String value1, String value2) {
            addCriterion("counter_id between", value1, value2, "counterId");
            return (Criteria) this;
        }

        public Criteria andCounterIdNotBetween(String value1, String value2) {
            addCriterion("counter_id not between", value1, value2, "counterId");
            return (Criteria) this;
        }

        public Criteria andCounterNameIsNull() {
            addCriterion("counter_name is null");
            return (Criteria) this;
        }

        public Criteria andCounterNameIsNotNull() {
            addCriterion("counter_name is not null");
            return (Criteria) this;
        }

        public Criteria andCounterNameEqualTo(String value) {
            addCriterion("counter_name =", value, "counterName");
            return (Criteria) this;
        }

        public Criteria andCounterNameNotEqualTo(String value) {
            addCriterion("counter_name <>", value, "counterName");
            return (Criteria) this;
        }

        public Criteria andCounterNameGreaterThan(String value) {
            addCriterion("counter_name >", value, "counterName");
            return (Criteria) this;
        }

        public Criteria andCounterNameGreaterThanOrEqualTo(String value) {
            addCriterion("counter_name >=", value, "counterName");
            return (Criteria) this;
        }

        public Criteria andCounterNameLessThan(String value) {
            addCriterion("counter_name <", value, "counterName");
            return (Criteria) this;
        }

        public Criteria andCounterNameLessThanOrEqualTo(String value) {
            addCriterion("counter_name <=", value, "counterName");
            return (Criteria) this;
        }

        public Criteria andCounterNameLike(String value) {
            addCriterion("counter_name like", value, "counterName");
            return (Criteria) this;
        }

        public Criteria andCounterNameNotLike(String value) {
            addCriterion("counter_name not like", value, "counterName");
            return (Criteria) this;
        }

        public Criteria andCounterNameIn(List<String> values) {
            addCriterion("counter_name in", values, "counterName");
            return (Criteria) this;
        }

        public Criteria andCounterNameNotIn(List<String> values) {
            addCriterion("counter_name not in", values, "counterName");
            return (Criteria) this;
        }

        public Criteria andCounterNameBetween(String value1, String value2) {
            addCriterion("counter_name between", value1, value2, "counterName");
            return (Criteria) this;
        }

        public Criteria andCounterNameNotBetween(String value1, String value2) {
            addCriterion("counter_name not between", value1, value2, "counterName");
            return (Criteria) this;
        }

        public Criteria andCounterNumIsNull() {
            addCriterion("counter_num is null");
            return (Criteria) this;
        }

        public Criteria andCounterNumIsNotNull() {
            addCriterion("counter_num is not null");
            return (Criteria) this;
        }

        public Criteria andCounterNumEqualTo(Integer value) {
            addCriterion("counter_num =", value, "counterNum");
            return (Criteria) this;
        }

        public Criteria andCounterNumNotEqualTo(Integer value) {
            addCriterion("counter_num <>", value, "counterNum");
            return (Criteria) this;
        }

        public Criteria andCounterNumGreaterThan(Integer value) {
            addCriterion("counter_num >", value, "counterNum");
            return (Criteria) this;
        }

        public Criteria andCounterNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("counter_num >=", value, "counterNum");
            return (Criteria) this;
        }

        public Criteria andCounterNumLessThan(Integer value) {
            addCriterion("counter_num <", value, "counterNum");
            return (Criteria) this;
        }

        public Criteria andCounterNumLessThanOrEqualTo(Integer value) {
            addCriterion("counter_num <=", value, "counterNum");
            return (Criteria) this;
        }

        public Criteria andCounterNumIn(List<Integer> values) {
            addCriterion("counter_num in", values, "counterNum");
            return (Criteria) this;
        }

        public Criteria andCounterNumNotIn(List<Integer> values) {
            addCriterion("counter_num not in", values, "counterNum");
            return (Criteria) this;
        }

        public Criteria andCounterNumBetween(Integer value1, Integer value2) {
            addCriterion("counter_num between", value1, value2, "counterNum");
            return (Criteria) this;
        }

        public Criteria andCounterNumNotBetween(Integer value1, Integer value2) {
            addCriterion("counter_num not between", value1, value2, "counterNum");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCourseIdIsNull() {
            addCriterion("course_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseIdIsNotNull() {
            addCriterion("course_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseIdEqualTo(String value) {
            addCriterion("course_id =", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotEqualTo(String value) {
            addCriterion("course_id <>", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThan(String value) {
            addCriterion("course_id >", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThanOrEqualTo(String value) {
            addCriterion("course_id >=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThan(String value) {
            addCriterion("course_id <", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThanOrEqualTo(String value) {
            addCriterion("course_id <=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLike(String value) {
            addCriterion("course_id like", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotLike(String value) {
            addCriterion("course_id not like", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdIn(List<String> values) {
            addCriterion("course_id in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotIn(List<String> values) {
            addCriterion("course_id not in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdBetween(String value1, String value2) {
            addCriterion("course_id between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotBetween(String value1, String value2) {
            addCriterion("course_id not between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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