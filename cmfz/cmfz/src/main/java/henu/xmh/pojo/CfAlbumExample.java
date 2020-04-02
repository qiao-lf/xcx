package henu.xmh.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CfAlbumExample {
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

    public CfAlbumExample() {
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

        public Criteria andAlbumIdIsNull() {
            addCriterion("album_id is null");
            return (Criteria) this;
        }

        public Criteria andAlbumIdIsNotNull() {
            addCriterion("album_id is not null");
            return (Criteria) this;
        }

        public Criteria andAlbumIdEqualTo(String value) {
            addCriterion("album_id =", value, "albumId");
            return (Criteria) this;
        }

        public Criteria andAlbumIdNotEqualTo(String value) {
            addCriterion("album_id <>", value, "albumId");
            return (Criteria) this;
        }

        public Criteria andAlbumIdGreaterThan(String value) {
            addCriterion("album_id >", value, "albumId");
            return (Criteria) this;
        }

        public Criteria andAlbumIdGreaterThanOrEqualTo(String value) {
            addCriterion("album_id >=", value, "albumId");
            return (Criteria) this;
        }

        public Criteria andAlbumIdLessThan(String value) {
            addCriterion("album_id <", value, "albumId");
            return (Criteria) this;
        }

        public Criteria andAlbumIdLessThanOrEqualTo(String value) {
            addCriterion("album_id <=", value, "albumId");
            return (Criteria) this;
        }

        public Criteria andAlbumIdLike(String value) {
            addCriterion("album_id like", value, "albumId");
            return (Criteria) this;
        }

        public Criteria andAlbumIdNotLike(String value) {
            addCriterion("album_id not like", value, "albumId");
            return (Criteria) this;
        }

        public Criteria andAlbumIdIn(List<String> values) {
            addCriterion("album_id in", values, "albumId");
            return (Criteria) this;
        }

        public Criteria andAlbumIdNotIn(List<String> values) {
            addCriterion("album_id not in", values, "albumId");
            return (Criteria) this;
        }

        public Criteria andAlbumIdBetween(String value1, String value2) {
            addCriterion("album_id between", value1, value2, "albumId");
            return (Criteria) this;
        }

        public Criteria andAlbumIdNotBetween(String value1, String value2) {
            addCriterion("album_id not between", value1, value2, "albumId");
            return (Criteria) this;
        }

        public Criteria andAlbumNameIsNull() {
            addCriterion("album_name is null");
            return (Criteria) this;
        }

        public Criteria andAlbumNameIsNotNull() {
            addCriterion("album_name is not null");
            return (Criteria) this;
        }

        public Criteria andAlbumNameEqualTo(String value) {
            addCriterion("album_name =", value, "albumName");
            return (Criteria) this;
        }

        public Criteria andAlbumNameNotEqualTo(String value) {
            addCriterion("album_name <>", value, "albumName");
            return (Criteria) this;
        }

        public Criteria andAlbumNameGreaterThan(String value) {
            addCriterion("album_name >", value, "albumName");
            return (Criteria) this;
        }

        public Criteria andAlbumNameGreaterThanOrEqualTo(String value) {
            addCriterion("album_name >=", value, "albumName");
            return (Criteria) this;
        }

        public Criteria andAlbumNameLessThan(String value) {
            addCriterion("album_name <", value, "albumName");
            return (Criteria) this;
        }

        public Criteria andAlbumNameLessThanOrEqualTo(String value) {
            addCriterion("album_name <=", value, "albumName");
            return (Criteria) this;
        }

        public Criteria andAlbumNameLike(String value) {
            addCriterion("album_name like", value, "albumName");
            return (Criteria) this;
        }

        public Criteria andAlbumNameNotLike(String value) {
            addCriterion("album_name not like", value, "albumName");
            return (Criteria) this;
        }

        public Criteria andAlbumNameIn(List<String> values) {
            addCriterion("album_name in", values, "albumName");
            return (Criteria) this;
        }

        public Criteria andAlbumNameNotIn(List<String> values) {
            addCriterion("album_name not in", values, "albumName");
            return (Criteria) this;
        }

        public Criteria andAlbumNameBetween(String value1, String value2) {
            addCriterion("album_name between", value1, value2, "albumName");
            return (Criteria) this;
        }

        public Criteria andAlbumNameNotBetween(String value1, String value2) {
            addCriterion("album_name not between", value1, value2, "albumName");
            return (Criteria) this;
        }

        public Criteria andAlbumCoverIsNull() {
            addCriterion("album_cover is null");
            return (Criteria) this;
        }

        public Criteria andAlbumCoverIsNotNull() {
            addCriterion("album_cover is not null");
            return (Criteria) this;
        }

        public Criteria andAlbumCoverEqualTo(String value) {
            addCriterion("album_cover =", value, "albumCover");
            return (Criteria) this;
        }

        public Criteria andAlbumCoverNotEqualTo(String value) {
            addCriterion("album_cover <>", value, "albumCover");
            return (Criteria) this;
        }

        public Criteria andAlbumCoverGreaterThan(String value) {
            addCriterion("album_cover >", value, "albumCover");
            return (Criteria) this;
        }

        public Criteria andAlbumCoverGreaterThanOrEqualTo(String value) {
            addCriterion("album_cover >=", value, "albumCover");
            return (Criteria) this;
        }

        public Criteria andAlbumCoverLessThan(String value) {
            addCriterion("album_cover <", value, "albumCover");
            return (Criteria) this;
        }

        public Criteria andAlbumCoverLessThanOrEqualTo(String value) {
            addCriterion("album_cover <=", value, "albumCover");
            return (Criteria) this;
        }

        public Criteria andAlbumCoverLike(String value) {
            addCriterion("album_cover like", value, "albumCover");
            return (Criteria) this;
        }

        public Criteria andAlbumCoverNotLike(String value) {
            addCriterion("album_cover not like", value, "albumCover");
            return (Criteria) this;
        }

        public Criteria andAlbumCoverIn(List<String> values) {
            addCriterion("album_cover in", values, "albumCover");
            return (Criteria) this;
        }

        public Criteria andAlbumCoverNotIn(List<String> values) {
            addCriterion("album_cover not in", values, "albumCover");
            return (Criteria) this;
        }

        public Criteria andAlbumCoverBetween(String value1, String value2) {
            addCriterion("album_cover between", value1, value2, "albumCover");
            return (Criteria) this;
        }

        public Criteria andAlbumCoverNotBetween(String value1, String value2) {
            addCriterion("album_cover not between", value1, value2, "albumCover");
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

        public Criteria andAnnouncerIsNull() {
            addCriterion("announcer is null");
            return (Criteria) this;
        }

        public Criteria andAnnouncerIsNotNull() {
            addCriterion("announcer is not null");
            return (Criteria) this;
        }

        public Criteria andAnnouncerEqualTo(String value) {
            addCriterion("announcer =", value, "announcer");
            return (Criteria) this;
        }

        public Criteria andAnnouncerNotEqualTo(String value) {
            addCriterion("announcer <>", value, "announcer");
            return (Criteria) this;
        }

        public Criteria andAnnouncerGreaterThan(String value) {
            addCriterion("announcer >", value, "announcer");
            return (Criteria) this;
        }

        public Criteria andAnnouncerGreaterThanOrEqualTo(String value) {
            addCriterion("announcer >=", value, "announcer");
            return (Criteria) this;
        }

        public Criteria andAnnouncerLessThan(String value) {
            addCriterion("announcer <", value, "announcer");
            return (Criteria) this;
        }

        public Criteria andAnnouncerLessThanOrEqualTo(String value) {
            addCriterion("announcer <=", value, "announcer");
            return (Criteria) this;
        }

        public Criteria andAnnouncerLike(String value) {
            addCriterion("announcer like", value, "announcer");
            return (Criteria) this;
        }

        public Criteria andAnnouncerNotLike(String value) {
            addCriterion("announcer not like", value, "announcer");
            return (Criteria) this;
        }

        public Criteria andAnnouncerIn(List<String> values) {
            addCriterion("announcer in", values, "announcer");
            return (Criteria) this;
        }

        public Criteria andAnnouncerNotIn(List<String> values) {
            addCriterion("announcer not in", values, "announcer");
            return (Criteria) this;
        }

        public Criteria andAnnouncerBetween(String value1, String value2) {
            addCriterion("announcer between", value1, value2, "announcer");
            return (Criteria) this;
        }

        public Criteria andAnnouncerNotBetween(String value1, String value2) {
            addCriterion("announcer not between", value1, value2, "announcer");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Double value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Double value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Double value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Double value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Double value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Double value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Double> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Double> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Double value1, Double value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Double value1, Double value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andAlibumDateIsNull() {
            addCriterion("alibum_date is null");
            return (Criteria) this;
        }

        public Criteria andAlibumDateIsNotNull() {
            addCriterion("alibum_date is not null");
            return (Criteria) this;
        }

        public Criteria andAlibumDateEqualTo(Date value) {
            addCriterion("alibum_date =", value, "alibumDate");
            return (Criteria) this;
        }

        public Criteria andAlibumDateNotEqualTo(Date value) {
            addCriterion("alibum_date <>", value, "alibumDate");
            return (Criteria) this;
        }

        public Criteria andAlibumDateGreaterThan(Date value) {
            addCriterion("alibum_date >", value, "alibumDate");
            return (Criteria) this;
        }

        public Criteria andAlibumDateGreaterThanOrEqualTo(Date value) {
            addCriterion("alibum_date >=", value, "alibumDate");
            return (Criteria) this;
        }

        public Criteria andAlibumDateLessThan(Date value) {
            addCriterion("alibum_date <", value, "alibumDate");
            return (Criteria) this;
        }

        public Criteria andAlibumDateLessThanOrEqualTo(Date value) {
            addCriterion("alibum_date <=", value, "alibumDate");
            return (Criteria) this;
        }

        public Criteria andAlibumDateIn(List<Date> values) {
            addCriterion("alibum_date in", values, "alibumDate");
            return (Criteria) this;
        }

        public Criteria andAlibumDateNotIn(List<Date> values) {
            addCriterion("alibum_date not in", values, "alibumDate");
            return (Criteria) this;
        }

        public Criteria andAlibumDateBetween(Date value1, Date value2) {
            addCriterion("alibum_date between", value1, value2, "alibumDate");
            return (Criteria) this;
        }

        public Criteria andAlibumDateNotBetween(Date value1, Date value2) {
            addCriterion("alibum_date not between", value1, value2, "alibumDate");
            return (Criteria) this;
        }

        public Criteria andAlbumNumIsNull() {
            addCriterion("album_num is null");
            return (Criteria) this;
        }

        public Criteria andAlbumNumIsNotNull() {
            addCriterion("album_num is not null");
            return (Criteria) this;
        }

        public Criteria andAlbumNumEqualTo(Integer value) {
            addCriterion("album_num =", value, "albumNum");
            return (Criteria) this;
        }

        public Criteria andAlbumNumNotEqualTo(Integer value) {
            addCriterion("album_num <>", value, "albumNum");
            return (Criteria) this;
        }

        public Criteria andAlbumNumGreaterThan(Integer value) {
            addCriterion("album_num >", value, "albumNum");
            return (Criteria) this;
        }

        public Criteria andAlbumNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("album_num >=", value, "albumNum");
            return (Criteria) this;
        }

        public Criteria andAlbumNumLessThan(Integer value) {
            addCriterion("album_num <", value, "albumNum");
            return (Criteria) this;
        }

        public Criteria andAlbumNumLessThanOrEqualTo(Integer value) {
            addCriterion("album_num <=", value, "albumNum");
            return (Criteria) this;
        }

        public Criteria andAlbumNumIn(List<Integer> values) {
            addCriterion("album_num in", values, "albumNum");
            return (Criteria) this;
        }

        public Criteria andAlbumNumNotIn(List<Integer> values) {
            addCriterion("album_num not in", values, "albumNum");
            return (Criteria) this;
        }

        public Criteria andAlbumNumBetween(Integer value1, Integer value2) {
            addCriterion("album_num between", value1, value2, "albumNum");
            return (Criteria) this;
        }

        public Criteria andAlbumNumNotBetween(Integer value1, Integer value2) {
            addCriterion("album_num not between", value1, value2, "albumNum");
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