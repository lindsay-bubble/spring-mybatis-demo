package cn.ling.angelhack.api.mapper;

import cn.ling.angelhack.api.domain.model.PersonInfo;
import cn.ling.angelhack.api.domain.model.PersonInfoExample;
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

public interface PersonInfoMapper {

    @SelectProvider(type = PersonInfoSqlProvider.class, method = "countByExample")
    long countByExample(PersonInfoExample example);

    @DeleteProvider(type = PersonInfoSqlProvider.class, method = "deleteByExample")
    int deleteByExample(PersonInfoExample example);

    @Delete({
            "delete from person_info",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into person_info (pic_path, scores)",
            "values (#{picPath,jdbcType=VARCHAR}, #{scores,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(PersonInfo record);

    @InsertProvider(type = PersonInfoSqlProvider.class, method = "insertSelective")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertSelective(PersonInfo record);

    @SelectProvider(type = PersonInfoSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "pic_path", property = "picPath", jdbcType = JdbcType.VARCHAR),
            @Result(column = "scores", property = "scores", jdbcType = JdbcType.VARCHAR)
    })
    List<PersonInfo> selectByExampleWithRowbounds(PersonInfoExample example, RowBounds rowBounds);

    @SelectProvider(type = PersonInfoSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "pic_path", property = "picPath", jdbcType = JdbcType.VARCHAR),
            @Result(column = "scores", property = "scores", jdbcType = JdbcType.VARCHAR)
    })
    List<PersonInfo> selectByExample(PersonInfoExample example);

    @Select({
            "select",
            "id, pic_path, scores",
            "from person_info"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "pic_path", property = "picPath", jdbcType = JdbcType.VARCHAR),
            @Result(column = "scores", property = "scores", jdbcType = JdbcType.VARCHAR)
    })
    List<PersonInfo> selectAll();

    @Select({
            "select",
            "id, pic_path, scores",
            "from person_info",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "pic_path", property = "picPath", jdbcType = JdbcType.VARCHAR),
            @Result(column = "scores", property = "scores", jdbcType = JdbcType.VARCHAR)
    })
    PersonInfo selectByPrimaryKey(Integer id);

    @UpdateProvider(type = PersonInfoSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") PersonInfo record, @Param("example") PersonInfoExample example);

    @UpdateProvider(type = PersonInfoSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") PersonInfo record, @Param("example") PersonInfoExample example);

    @UpdateProvider(type = PersonInfoSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PersonInfo record);

    @Update({
            "update person_info",
            "set pic_path = #{picPath,jdbcType=VARCHAR},",
            "scores = #{scores,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(PersonInfo record);
}