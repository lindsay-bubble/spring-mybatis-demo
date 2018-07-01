package cn.ling.angelhack.api.mapper;

import cn.ling.angelhack.api.domain.model.PersonInfo;
import cn.ling.angelhack.api.domain.model.TableGame;
import cn.ling.angelhack.api.domain.model.TableGameExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

public interface TableGameMapper {
    @SelectProvider(type=TableGameSqlProvider.class, method="countByExample")
    long countByExample(TableGameExample example);

    @DeleteProvider(type=TableGameSqlProvider.class, method="deleteByExample")
    int deleteByExample(TableGameExample example);

    @Delete({
        "delete from table_game",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into table_game (`name`, `desc`, ",
        "pic_url, min_num, ",
        "max_num, scores)",
        "values (#{name,jdbcType=VARCHAR}, #{desc,jdbcType=VARCHAR}, ",
        "#{picUrl,jdbcType=VARCHAR}, #{minNum,jdbcType=INTEGER}, ",
        "#{maxNum,jdbcType=INTEGER}, #{scores,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(TableGame record);

    @InsertProvider(type=TableGameSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(TableGame record);

    @SelectProvider(type=TableGameSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="pic_url", property="picUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="min_num", property="minNum", jdbcType=JdbcType.INTEGER),
        @Result(column="max_num", property="maxNum", jdbcType=JdbcType.INTEGER),
        @Result(column="scores", property="scores", jdbcType=JdbcType.VARCHAR)
    })
    List<TableGame> selectByExampleWithRowbounds(TableGameExample example, RowBounds rowBounds);

    @SelectProvider(type=TableGameSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="pic_url", property="picUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="min_num", property="minNum", jdbcType=JdbcType.INTEGER),
        @Result(column="max_num", property="maxNum", jdbcType=JdbcType.INTEGER),
        @Result(column="scores", property="scores", jdbcType=JdbcType.VARCHAR)
    })
    List<TableGame> selectByExample(TableGameExample example);

    @Select({
        "select",
        "id, `name`, `desc`, pic_url, min_num, max_num, scores",
        "from table_game",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="pic_url", property="picUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="min_num", property="minNum", jdbcType=JdbcType.INTEGER),
        @Result(column="max_num", property="maxNum", jdbcType=JdbcType.INTEGER),
        @Result(column="scores", property="scores", jdbcType=JdbcType.VARCHAR)
    })
    TableGame selectByPrimaryKey(Integer id);


    @Select({
            "select",
            "id, `name`, `desc`, pic_url, min_num, max_num, scores",
            "from table_game"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
            @Result(column="pic_url", property="picUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="min_num", property="minNum", jdbcType=JdbcType.INTEGER),
            @Result(column="max_num", property="maxNum", jdbcType=JdbcType.INTEGER),
            @Result(column="scores", property="scores", jdbcType=JdbcType.VARCHAR)
    })
    List<TableGame> selectAll();

    @UpdateProvider(type=TableGameSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TableGame record, @Param("example") TableGameExample example);

    @UpdateProvider(type=TableGameSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TableGame record, @Param("example") TableGameExample example);

    @UpdateProvider(type=TableGameSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TableGame record);

    @Update({
        "update table_game",
        "set `name` = #{name,jdbcType=VARCHAR},",
          "`desc` = #{desc,jdbcType=VARCHAR},",
          "pic_url = #{picUrl,jdbcType=VARCHAR},",
          "min_num = #{minNum,jdbcType=INTEGER},",
          "max_num = #{maxNum,jdbcType=INTEGER},",
          "scores = #{scores,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TableGame record);
}