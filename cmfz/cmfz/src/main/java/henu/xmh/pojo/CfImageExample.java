package henu.xmh.pojo;

import java.util.ArrayList;
import java.util.List;

public class CfImageExample {
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

    public CfImageExample() {
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

        public Criteria andImageIdIsNull() {
            addCriterion("image_id is null");
            return (Criteria) this;
        }

        public Criteria andImageIdIsNotNull() {
            addCriterion("image_id is not null");
            return (Criteria) this;
        }

        public Criteria andImageIdEqualTo(String value) {
            addCriterion("image_id =", value, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdNotEqualTo(String value) {
            addCriterion("image_id <>", value, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdGreaterThan(String value) {
            addCriterion("image_id >", value, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdGreaterThanOrEqualTo(String value) {
            addCriterion("image_id >=", value, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdLessThan(String value) {
            addCriterion("image_id <", value, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdLessThanOrEqualTo(String value) {
            addCriterion("image_id <=", value, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdLike(String value) {
            addCriterion("image_id like", value, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdNotLike(String value) {
            addCriterion("image_id not like", value, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdIn(List<String> values) {
            addCriterion("image_id in", values, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdNotIn(List<String> values) {
            addCriterion("image_id not in", values, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdBetween(String value1, String value2) {
            addCriterion("image_id between", value1, value2, "imageId");
            return (Criteria) this;
        }

        public Criteria andImageIdNotBetween(String value1, String value2) {
            addCriterion("image_id not between", value1, value2, "imageId");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNull() {
            addCriterion("org_name is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNotNull() {
            addCriterion("org_name is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEqualTo(String value) {
            addCriterion("org_name =", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotEqualTo(String value) {
            addCriterion("org_name <>", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThan(String value) {
            addCriterion("org_name >", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("org_name >=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThan(String value) {
            addCriterion("org_name <", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThanOrEqualTo(String value) {
            addCriterion("org_name <=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLike(String value) {
            addCriterion("org_name like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotLike(String value) {
            addCriterion("org_name not like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIn(List<String> values) {
            addCriterion("org_name in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotIn(List<String> values) {
            addCriterion("org_name not in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameBetween(String value1, String value2) {
            addCriterion("org_name between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotBetween(String value1, String value2) {
            addCriterion("org_name not between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andNewNameIsNull() {
            addCriterion("new_name is null");
            return (Criteria) this;
        }

        public Criteria andNewNameIsNotNull() {
            addCriterion("new_name is not null");
            return (Criteria) this;
        }

        public Criteria andNewNameEqualTo(String value) {
            addCriterion("new_name =", value, "newName");
            return (Criteria) this;
        }

        public Criteria andNewNameNotEqualTo(String value) {
            addCriterion("new_name <>", value, "newName");
            return (Criteria) this;
        }

        public Criteria andNewNameGreaterThan(String value) {
            addCriterion("new_name >", value, "newName");
            return (Criteria) this;
        }

        public Criteria andNewNameGreaterThanOrEqualTo(String value) {
            addCriterion("new_name >=", value, "newName");
            return (Criteria) this;
        }

        public Criteria andNewNameLessThan(String value) {
            addCriterion("new_name <", value, "newName");
            return (Criteria) this;
        }

        public Criteria andNewNameLessThanOrEqualTo(String value) {
            addCriterion("new_name <=", value, "newName");
            return (Criteria) this;
        }

        public Criteria andNewNameLike(String value) {
            addCriterion("new_name like", value, "newName");
            return (Criteria) this;
        }

        public Criteria andNewNameNotLike(String value) {
            addCriterion("new_name not like", value, "newName");
            return (Criteria) this;
        }

        public Criteria andNewNameIn(List<String> values) {
            addCriterion("new_name in", values, "newName");
            return (Criteria) this;
        }

        public Criteria andNewNameNotIn(List<String> values) {
            addCriterion("new_name not in", values, "newName");
            return (Criteria) this;
        }

        public Criteria andNewNameBetween(String value1, String value2) {
            addCriterion("new_name between", value1, value2, "newName");
            return (Criteria) this;
        }

        public Criteria andNewNameNotBetween(String value1, String value2) {
            addCriterion("new_name not between", value1, value2, "newName");
            return (Criteria) this;
        }

        public Criteria andImageStatusIsNull() {
            addCriterion("image_status is null");
            return (Criteria) this;
        }

        public Criteria andImageStatusIsNotNull() {
            addCriterion("image_status is not null");
            return (Criteria) this;
        }

        public Criteria andImageStatusEqualTo(String value) {
            addCriterion("image_status =", value, "imageStatus");
            return (Criteria) this;
        }

        public Criteria andImageStatusNotEqualTo(String value) {
            addCriterion("image_status <>", value, "imageStatus");
            return (Criteria) this;
        }

        public Criteria andImageStatusGreaterThan(String value) {
            addCriterion("image_status >", value, "imageStatus");
            return (Criteria) this;
        }

        public Criteria andImageStatusGreaterThanOrEqualTo(String value) {
            addCriterion("image_status >=", value, "imageStatus");
            return (Criteria) this;
        }

        public Criteria andImageStatusLessThan(String value) {
            addCriterion("image_status <", value, "imageStatus");
            return (Criteria) this;
        }

        public Criteria andImageStatusLessThanOrEqualTo(String value) {
            addCriterion("image_status <=", value, "imageStatus");
            return (Criteria) this;
        }

        public Criteria andImageStatusLike(String value) {
            addCriterion("image_status like", value, "imageStatus");
            return (Criteria) this;
        }

        public Criteria andImageStatusNotLike(String value) {
            addCriterion("image_status not like", value, "imageStatus");
            return (Criteria) this;
        }

        public Criteria andImageStatusIn(List<String> values) {
            addCriterion("image_status in", values, "imageStatus");
            return (Criteria) this;
        }

        public Criteria andImageStatusNotIn(List<String> values) {
            addCriterion("image_status not in", values, "imageStatus");
            return (Criteria) this;
        }

        public Criteria andImageStatusBetween(String value1, String value2) {
            addCriterion("image_status between", value1, value2, "imageStatus");
            return (Criteria) this;
        }

        public Criteria andImageStatusNotBetween(String value1, String value2) {
            addCriterion("image_status not between", value1, value2, "imageStatus");
            return (Criteria) this;
        }

        public Criteria andImageDirIsNull() {
            addCriterion("image_dir is null");
            return (Criteria) this;
        }

        public Criteria andImageDirIsNotNull() {
            addCriterion("image_dir is not null");
            return (Criteria) this;
        }

        public Criteria andImageDirEqualTo(String value) {
            addCriterion("image_dir =", value, "imageDir");
            return (Criteria) this;
        }

        public Criteria andImageDirNotEqualTo(String value) {
            addCriterion("image_dir <>", value, "imageDir");
            return (Criteria) this;
        }

        public Criteria andImageDirGreaterThan(String value) {
            addCriterion("image_dir >", value, "imageDir");
            return (Criteria) this;
        }

        public Criteria andImageDirGreaterThanOrEqualTo(String value) {
            addCriterion("image_dir >=", value, "imageDir");
            return (Criteria) this;
        }

        public Criteria andImageDirLessThan(String value) {
            addCriterion("image_dir <", value, "imageDir");
            return (Criteria) this;
        }

        public Criteria andImageDirLessThanOrEqualTo(String value) {
            addCriterion("image_dir <=", value, "imageDir");
            return (Criteria) this;
        }

        public Criteria andImageDirLike(String value) {
            addCriterion("image_dir like", value, "imageDir");
            return (Criteria) this;
        }

        public Criteria andImageDirNotLike(String value) {
            addCriterion("image_dir not like", value, "imageDir");
            return (Criteria) this;
        }

        public Criteria andImageDirIn(List<String> values) {
            addCriterion("image_dir in", values, "imageDir");
            return (Criteria) this;
        }

        public Criteria andImageDirNotIn(List<String> values) {
            addCriterion("image_dir not in", values, "imageDir");
            return (Criteria) this;
        }

        public Criteria andImageDirBetween(String value1, String value2) {
            addCriterion("image_dir between", value1, value2, "imageDir");
            return (Criteria) this;
        }

        public Criteria andImageDirNotBetween(String value1, String value2) {
            addCriterion("image_dir not between", value1, value2, "imageDir");
            return (Criteria) this;
        }

        public Criteria andImageSummaryIsNull() {
            addCriterion("image_summary is null");
            return (Criteria) this;
        }

        public Criteria andImageSummaryIsNotNull() {
            addCriterion("image_summary is not null");
            return (Criteria) this;
        }

        public Criteria andImageSummaryEqualTo(String value) {
            addCriterion("image_summary =", value, "imageSummary");
            return (Criteria) this;
        }

        public Criteria andImageSummaryNotEqualTo(String value) {
            addCriterion("image_summary <>", value, "imageSummary");
            return (Criteria) this;
        }

        public Criteria andImageSummaryGreaterThan(String value) {
            addCriterion("image_summary >", value, "imageSummary");
            return (Criteria) this;
        }

        public Criteria andImageSummaryGreaterThanOrEqualTo(String value) {
            addCriterion("image_summary >=", value, "imageSummary");
            return (Criteria) this;
        }

        public Criteria andImageSummaryLessThan(String value) {
            addCriterion("image_summary <", value, "imageSummary");
            return (Criteria) this;
        }

        public Criteria andImageSummaryLessThanOrEqualTo(String value) {
            addCriterion("image_summary <=", value, "imageSummary");
            return (Criteria) this;
        }

        public Criteria andImageSummaryLike(String value) {
            addCriterion("image_summary like", value, "imageSummary");
            return (Criteria) this;
        }

        public Criteria andImageSummaryNotLike(String value) {
            addCriterion("image_summary not like", value, "imageSummary");
            return (Criteria) this;
        }

        public Criteria andImageSummaryIn(List<String> values) {
            addCriterion("image_summary in", values, "imageSummary");
            return (Criteria) this;
        }

        public Criteria andImageSummaryNotIn(List<String> values) {
            addCriterion("image_summary not in", values, "imageSummary");
            return (Criteria) this;
        }

        public Criteria andImageSummaryBetween(String value1, String value2) {
            addCriterion("image_summary between", value1, value2, "imageSummary");
            return (Criteria) this;
        }

        public Criteria andImageSummaryNotBetween(String value1, String value2) {
            addCriterion("image_summary not between", value1, value2, "imageSummary");
            return (Criteria) this;
        }

        public Criteria andImageHrefIsNull() {
            addCriterion("image_href is null");
            return (Criteria) this;
        }

        public Criteria andImageHrefIsNotNull() {
            addCriterion("image_href is not null");
            return (Criteria) this;
        }

        public Criteria andImageHrefEqualTo(String value) {
            addCriterion("image_href =", value, "imageHref");
            return (Criteria) this;
        }

        public Criteria andImageHrefNotEqualTo(String value) {
            addCriterion("image_href <>", value, "imageHref");
            return (Criteria) this;
        }

        public Criteria andImageHrefGreaterThan(String value) {
            addCriterion("image_href >", value, "imageHref");
            return (Criteria) this;
        }

        public Criteria andImageHrefGreaterThanOrEqualTo(String value) {
            addCriterion("image_href >=", value, "imageHref");
            return (Criteria) this;
        }

        public Criteria andImageHrefLessThan(String value) {
            addCriterion("image_href <", value, "imageHref");
            return (Criteria) this;
        }

        public Criteria andImageHrefLessThanOrEqualTo(String value) {
            addCriterion("image_href <=", value, "imageHref");
            return (Criteria) this;
        }

        public Criteria andImageHrefLike(String value) {
            addCriterion("image_href like", value, "imageHref");
            return (Criteria) this;
        }

        public Criteria andImageHrefNotLike(String value) {
            addCriterion("image_href not like", value, "imageHref");
            return (Criteria) this;
        }

        public Criteria andImageHrefIn(List<String> values) {
            addCriterion("image_href in", values, "imageHref");
            return (Criteria) this;
        }

        public Criteria andImageHrefNotIn(List<String> values) {
            addCriterion("image_href not in", values, "imageHref");
            return (Criteria) this;
        }

        public Criteria andImageHrefBetween(String value1, String value2) {
            addCriterion("image_href between", value1, value2, "imageHref");
            return (Criteria) this;
        }

        public Criteria andImageHrefNotBetween(String value1, String value2) {
            addCriterion("image_href not between", value1, value2, "imageHref");
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