package cn.ling.angelhack.api.mapper;

import cn.ling.angelhack.api.domain.model.TableGame;
import cn.ling.angelhack.api.domain.model.TableGameExample.Criteria;
import cn.ling.angelhack.api.domain.model.TableGameExample.Criterion;
import cn.ling.angelhack.api.domain.model.TableGameExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class TableGameSqlProvider {

    public String countByExample(TableGameExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("table_game");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(TableGameExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("table_game");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(TableGame record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("table_game");
        
        if (record.getName() != null) {
            sql.VALUES("`name`", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getDesc() != null) {
            sql.VALUES("`desc`", "#{desc,jdbcType=VARCHAR}");
        }
        
        if (record.getPicUrl() != null) {
            sql.VALUES("pic_url", "#{picUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getMinNum() != null) {
            sql.VALUES("min_num", "#{minNum,jdbcType=INTEGER}");
        }
        
        if (record.getMaxNum() != null) {
            sql.VALUES("max_num", "#{maxNum,jdbcType=INTEGER}");
        }
        
        if (record.getScores() != null) {
            sql.VALUES("scores", "#{scores,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(TableGameExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("`name`");
        sql.SELECT("`desc`");
        sql.SELECT("pic_url");
        sql.SELECT("min_num");
        sql.SELECT("max_num");
        sql.SELECT("scores");
        sql.FROM("table_game");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        TableGame record = (TableGame) parameter.get("record");
        TableGameExample example = (TableGameExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("table_game");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.SET("`name` = #{record.name,jdbcType=VARCHAR}");
        }
        
        if (record.getDesc() != null) {
            sql.SET("`desc` = #{record.desc,jdbcType=VARCHAR}");
        }
        
        if (record.getPicUrl() != null) {
            sql.SET("pic_url = #{record.picUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getMinNum() != null) {
            sql.SET("min_num = #{record.minNum,jdbcType=INTEGER}");
        }
        
        if (record.getMaxNum() != null) {
            sql.SET("max_num = #{record.maxNum,jdbcType=INTEGER}");
        }
        
        if (record.getScores() != null) {
            sql.SET("scores = #{record.scores,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("table_game");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("`name` = #{record.name,jdbcType=VARCHAR}");
        sql.SET("`desc` = #{record.desc,jdbcType=VARCHAR}");
        sql.SET("pic_url = #{record.picUrl,jdbcType=VARCHAR}");
        sql.SET("min_num = #{record.minNum,jdbcType=INTEGER}");
        sql.SET("max_num = #{record.maxNum,jdbcType=INTEGER}");
        sql.SET("scores = #{record.scores,jdbcType=VARCHAR}");
        
        TableGameExample example = (TableGameExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TableGame record) {
        SQL sql = new SQL();
        sql.UPDATE("table_game");
        
        if (record.getName() != null) {
            sql.SET("`name` = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getDesc() != null) {
            sql.SET("`desc` = #{desc,jdbcType=VARCHAR}");
        }
        
        if (record.getPicUrl() != null) {
            sql.SET("pic_url = #{picUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getMinNum() != null) {
            sql.SET("min_num = #{minNum,jdbcType=INTEGER}");
        }
        
        if (record.getMaxNum() != null) {
            sql.SET("max_num = #{maxNum,jdbcType=INTEGER}");
        }
        
        if (record.getScores() != null) {
            sql.SET("scores = #{scores,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, TableGameExample example, boolean includeExamplePhrase) {
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