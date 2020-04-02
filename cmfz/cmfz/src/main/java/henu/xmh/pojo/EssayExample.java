package henu.xmh.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EssayExample {
    private Integer beginValue;

    private Integer pageSize;

    public Integer getBeginValue() {
        return beginValue;
    }

    public void setBeginValue(Integer beginValue) {
        this.beginValue = beginValue;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EssayExample() {
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

        public Criteria andEssayIdIsNull() {
            addCriterion("essay_id is null");
            return (Criteria) this;
        }

        public Criteria andEssayIdIsNotNull() {
            addCriterion("essay_id is not null");
            return (Criteria) this;
        }

        public Criteria andEssayIdEqualTo(String value) {
            addCriterion("essay_id =", value, "essayId");
            return (Criteria) this;
        }

        public Criteria andEssayIdNotEqualTo(String value) {
            addCriterion("essay_id <>", value, "essayId");
            return (Criteria) this;
        }

        public Criteria andEssayIdGreaterThan(String value) {
            addCriterion("essay_id >", value, "essayId");
            return (Criteria) this;
        }

        public Criteria andEssayIdGreaterThanOrEqualTo(String value) {
            addCriterion("essay_id >=", value, "essayId");
            return (Criteria) this;
        }

        public Criteria andEssayIdLessThan(String value) {
            addCriterion("essay_id <", value, "essayId");
            return (Criteria) this;
        }

        public Criteria andEssayIdLessThanOrEqualTo(String value) {
            addCriterion("essay_id <=", value, "essayId");
            return (Criteria) this;
        }

        public Criteria andEssayIdLike(String value) {
            addCriterion("essay_id like", value, "essayId");
            return (Criteria) this;
        }

        public Criteria andEssayIdNotLike(String value) {
            addCriterion("essay_id not like", value, "essayId");
            return (Criteria) this;
        }

        public Criteria andEssayIdIn(List<String> values) {
            addCriterion("essay_id in", values, "essayId");
            return (Criteria) this;
        }

        public Criteria andEssayIdNotIn(List<String> values) {
            addCriterion("essay_id not in", values, "essayId");
            return (Criteria) this;
        }

        public Criteria andEssayIdBetween(String value1, String value2) {
            addCriterion("essay_id between", value1, value2, "essayId");
            return (Criteria) this;
        }

        public Criteria andEssayIdNotBetween(String value1, String value2) {
            addCriterion("essay_id not between", value1, value2, "essayId");
            return (Criteria) this;
        }

        public Criteria andGuruIdIsNull() {
            addCriterion("guru_id is null");
            return (Criteria) this;
        }

        public Criteria andGuruIdIsNotNull() {
            addCriterion("guru_id is not null");
            return (Criteria) this;
        }

        public Criteria andGuruIdEqualTo(String value) {
            addCriterion("e.guru_id =", value, "guruId");
            return (Criteria) this;
        }

        public Criteria andGuruIdNotEqualTo(String value) {
            addCriterion("guru_id <>", value, "guruId");
            return (Criteria) this;
        }

        public Criteria andGuruIdGreaterThan(String value) {
            addCriterion("guru_id >", value, "guruId");
            return (Criteria) this;
        }

        public Criteria andGuruIdGreaterThanOrEqualTo(String value) {
            addCriterion("guru_id >=", value, "guruId");
            return (Criteria) this;
        }

        public Criteria andGuruIdLessThan(String value) {
            addCriterion("guru_id <", value, "guruId");
            return (Criteria) this;
        }

        public Criteria andGuruIdLessThanOrEqualTo(String value) {
            addCriterion("guru_id <=", value, "guruId");
            return (Criteria) this;
        }

        public Criteria andGuruIdLike(String value) {
            addCriterion("guru_id like", value, "guruId");
            return (Criteria) this;
        }

        public Criteria andGuruIdNotLike(String value) {
            addCriterion("guru_id not like", value, "guruId");
            return (Criteria) this;
        }

        public Criteria andGuruIdIn(List<String> values) {
            addCriterion("guru_id in", values, "guruId");
            return (Criteria) this;
        }

        public Criteria andGuruIdNotIn(List<String> values) {
            addCriterion("guru_id not in", values, "guruId");
            return (Criteria) this;
        }

        public Criteria andGuruIdBetween(String value1, String value2) {
            addCriterion("guru_id between", value1, value2, "guruId");
            return (Criteria) this;
        }

        public Criteria andGuruIdNotBetween(String value1, String value2) {
            addCriterion("guru_id not between", value1, value2, "guruId");
            return (Criteria) this;
        }

        public Criteria andEssayTimeIsNull() {
            addCriterion("essay_time is null");
            return (Criteria) this;
        }

        public Criteria andEssayTimeIsNotNull() {
            addCriterion("essay_time is not null");
            return (Criteria) this;
        }

        public Criteria andEssayTimeEqualTo(Date value) {
            addCriterion("essay_time =", value, "essayTime");
            return (Criteria) this;
        }

        public Criteria andEssayTimeNotEqualTo(Date value) {
            addCriterion("essay_time <>", value, "essayTime");
            return (Criteria) this;
        }

        public Criteria andEssayTimeGreaterThan(Date value) {
            addCriterion("essay_time >", value, "essayTime");
            return (Criteria) this;
        }

        public Criteria andEssayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("essay_time >=", value, "essayTime");
            return (Criteria) this;
        }

        public Criteria andEssayTimeLessThan(Date value) {
            addCriterion("essay_time <", value, "essayTime");
            return (Criteria) this;
        }

        public Criteria andEssayTimeLessThanOrEqualTo(Date value) {
            addCriterion("essay_time <=", value, "essayTime");
            return (Criteria) this;
        }

        public Criteria andEssayTimeIn(List<Date> values) {
            addCriterion("essay_time in", values, "essayTime");
            return (Criteria) this;
        }

        public Criteria andEssayTimeNotIn(List<Date> values) {
            addCriterion("essay_time not in", values, "essayTime");
            return (Criteria) this;
        }

        public Criteria andEssayTimeBetween(Date value1, Date value2) {
            addCriterion("essay_time between", value1, value2, "essayTime");
            return (Criteria) this;
        }

        public Criteria andEssayTimeNotBetween(Date value1, Date value2) {
            addCriterion("essay_time not between", value1, value2, "essayTime");
            return (Criteria) this;
        }

        public Criteria andEssayCoverIsNull() {
            addCriterion("essay_cover is null");
            return (Criteria) this;
        }

        public Criteria andEssayCoverIsNotNull() {
            addCriterion("essay_cover is not null");
            return (Criteria) this;
        }

        public Criteria andEssayCoverEqualTo(String value) {
            addCriterion("essay_cover =", value, "essayCover");
            return (Criteria) this;
        }

        public Criteria andEssayCoverNotEqualTo(String value) {
            addCriterion("essay_cover <>", value, "essayCover");
            return (Criteria) this;
        }

        public Criteria andEssayCoverGreaterThan(String value) {
            addCriterion("essay_cover >", value, "essayCover");
            return (Criteria) this;
        }

        public Criteria andEssayCoverGreaterThanOrEqualTo(String value) {
            addCriterion("essay_cover >=", value, "essayCover");
            return (Criteria) this;
        }

        public Criteria andEssayCoverLessThan(String value) {
            addCriterion("essay_cover <", value, "essayCover");
            return (Criteria) this;
        }

        public Criteria andEssayCoverLessThanOrEqualTo(String value) {
            addCriterion("essay_cover <=", value, "essayCover");
            return (Criteria) this;
        }

        public Criteria andEssayCoverLike(String value) {
            addCriterion("essay_cover like", value, "essayCover");
            return (Criteria) this;
        }

        public Criteria andEssayCoverNotLike(String value) {
            addCriterion("essay_cover not like", value, "essayCover");
            return (Criteria) this;
        }

        public Criteria andEssayCoverIn(List<String> values) {
            addCriterion("essay_cover in", values, "essayCover");
            return (Criteria) this;
        }

        public Criteria andEssayCoverNotIn(List<String> values) {
            addCriterion("essay_cover not in", values, "essayCover");
            return (Criteria) this;
        }

        public Criteria andEssayCoverBetween(String value1, String value2) {
            addCriterion("essay_cover between", value1, value2, "essayCover");
            return (Criteria) this;
        }

        public Criteria andEssayCoverNotBetween(String value1, String value2) {
            addCriterion("essay_cover not between", value1, value2, "essayCover");
            return (Criteria) this;
        }

        public Criteria andEssayTittleIsNull() {
            addCriterion("essay_tittle is null");
            return (Criteria) this;
        }

        public Criteria andEssayTittleIsNotNull() {
            addCriterion("essay_tittle is not null");
            return (Criteria) this;
        }

        public Criteria andEssayTittleEqualTo(String value) {
            addCriterion("essay_tittle =", value, "essayTittle");
            return (Criteria) this;
        }

        public Criteria andEssayTittleNotEqualTo(String value) {
            addCriterion("essay_tittle <>", value, "essayTittle");
            return (Criteria) this;
        }

        public Criteria andEssayTittleGreaterThan(String value) {
            addCriterion("essay_tittle >", value, "essayTittle");
            return (Criteria) this;
        }

        public Criteria andEssayTittleGreaterThanOrEqualTo(String value) {
            addCriterion("essay_tittle >=", value, "essayTittle");
            return (Criteria) this;
        }

        public Criteria andEssayTittleLessThan(String value) {
            addCriterion("essay_tittle <", value, "essayTittle");
            return (Criteria) this;
        }

        public Criteria andEssayTittleLessThanOrEqualTo(String value) {
            addCriterion("essay_tittle <=", value, "essayTittle");
            return (Criteria) this;
        }

        public Criteria andEssayTittleLike(String value) {
            addCriterion("essay_tittle like", value, "essayTittle");
            return (Criteria) this;
        }

        public Criteria andEssayTittleNotLike(String value) {
            addCriterion("essay_tittle not like", value, "essayTittle");
            return (Criteria) this;
        }

        public Criteria andEssayTittleIn(List<String> values) {
            addCriterion("essay_tittle in", values, "essayTittle");
            return (Criteria) this;
        }

        public Criteria andEssayTittleNotIn(List<String> values) {
            addCriterion("essay_tittle not in", values, "essayTittle");
            return (Criteria) this;
        }

        public Criteria andEssayTittleBetween(String value1, String value2) {
            addCriterion("essay_tittle between", value1, value2, "essayTittle");
            return (Criteria) this;
        }

        public Criteria andEssayTittleNotBetween(String value1, String value2) {
            addCriterion("essay_tittle not between", value1, value2, "essayTittle");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
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