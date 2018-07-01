package cn.ling.angelhack.api.mapper;

import cn.ling.angelhack.api.domain.model.PersonInfo;
import cn.ling.angelhack.api.domain.model.PersonInfoExample.Criteria;
import cn.ling.angelhack.api.domain.model.PersonInfoExample.Criterion;
import cn.ling.angelhack.api.domain.model.PersonInfoExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class PersonInfoSqlProvider {

    public String countByExample(PersonInfoExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("person_info");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(PersonInfoExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("person_info");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(PersonInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("person_info");
        
        if (record.getPicPath() != null) {
            sql.VALUES("pic_path", "#{picPath,jdbcType=VARCHAR}");
        }
        
        if (record.getScores() != null) {
            sql.VALUES("scores", "#{scores,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(PersonInfoExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("pic_path");
        sql.SELECT("scores");
        sql.FROM("person_info");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        PersonInfo record = (PersonInfo) parameter.get("record");
        PersonInfoExample example = (PersonInfoExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("person_info");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getPicPath() != null) {
            sql.SET("pic_path = #{record.picPath,jdbcType=VARCHAR}");
        }
        
        if (record.getScores() != null) {
            sql.SET("scores = #{record.scores,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("person_info");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("pic_path = #{record.picPath,jdbcType=VARCHAR}");
        sql.SET("scores = #{record.scores,jdbcType=VARCHAR}");
        
        PersonInfoExample example = (PersonInfoExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(PersonInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("person_info");
        
        if (record.getPicPath() != null) {
            sql.SET("pic_path = #{picPath,jdbcType=VARCHAR}");
        }
        
        if (record.getScores() != null) {
            sql.SET("scores = #{scores,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, PersonInfoExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}