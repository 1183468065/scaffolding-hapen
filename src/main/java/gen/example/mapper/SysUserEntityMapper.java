package gen.example.mapper;

import gen.example.entity.SysUserEntity;
import gen.example.entity.SysUserEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

public interface SysUserEntityMapper {
    @SelectProvider(type=SysUserEntitySqlProvider.class, method="countByExample")
    long countByExample(SysUserEntityExample example);

    @DeleteProvider(type=SysUserEntitySqlProvider.class, method="deleteByExample")
    int deleteByExample(SysUserEntityExample example);

    @Delete({
        "delete from t_sys_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_sys_user (id, login_name, ",
        "password, job_no, ",
        "name, email, phone, ",
        "mobile, photo, login_flag, ",
        "status, create_by, ",
        "create_date, last_modified_by, ",
        "last_modified_date)",
        "values (#{id,jdbcType=BIGINT}, #{loginName,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{jobNo,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, ",
        "#{mobile,jdbcType=VARCHAR}, #{photo,jdbcType=VARCHAR}, #{loginFlag,jdbcType=CHAR}, ",
        "#{status,jdbcType=INTEGER}, #{createBy,jdbcType=BIGINT}, ",
        "#{createDate,jdbcType=TIMESTAMP}, #{lastModifiedBy,jdbcType=BIGINT}, ",
        "#{lastModifiedDate,jdbcType=TIMESTAMP})"
    })
    int insert(SysUserEntity record);

    @InsertProvider(type=SysUserEntitySqlProvider.class, method="insertSelective")
    int insertSelective(SysUserEntity record);

    @SelectProvider(type=SysUserEntitySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="login_name", property="loginName", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="job_no", property="jobNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="photo", property="photo", jdbcType=JdbcType.VARCHAR),
        @Result(column="login_flag", property="loginFlag", jdbcType=JdbcType.CHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.BIGINT),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="last_modified_by", property="lastModifiedBy", jdbcType=JdbcType.BIGINT),
        @Result(column="last_modified_date", property="lastModifiedDate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysUserEntity> selectByExampleWithRowbounds(SysUserEntityExample example, RowBounds rowBounds);

    @SelectProvider(type=SysUserEntitySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="login_name", property="loginName", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="job_no", property="jobNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="photo", property="photo", jdbcType=JdbcType.VARCHAR),
        @Result(column="login_flag", property="loginFlag", jdbcType=JdbcType.CHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.BIGINT),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="last_modified_by", property="lastModifiedBy", jdbcType=JdbcType.BIGINT),
        @Result(column="last_modified_date", property="lastModifiedDate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysUserEntity> selectByExample(SysUserEntityExample example);

    @Select({
        "select",
        "id, login_name, password, job_no, name, email, phone, mobile, photo, login_flag, ",
        "status, create_by, create_date, last_modified_by, last_modified_date",
        "from t_sys_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="login_name", property="loginName", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="job_no", property="jobNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="photo", property="photo", jdbcType=JdbcType.VARCHAR),
        @Result(column="login_flag", property="loginFlag", jdbcType=JdbcType.CHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.BIGINT),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="last_modified_by", property="lastModifiedBy", jdbcType=JdbcType.BIGINT),
        @Result(column="last_modified_date", property="lastModifiedDate", jdbcType=JdbcType.TIMESTAMP)
    })
    SysUserEntity selectByPrimaryKey(Long id);

    @UpdateProvider(type=SysUserEntitySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SysUserEntity record, @Param("example") SysUserEntityExample example);

    @UpdateProvider(type=SysUserEntitySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SysUserEntity record, @Param("example") SysUserEntityExample example);

    @UpdateProvider(type=SysUserEntitySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysUserEntity record);

    @Update({
        "update t_sys_user",
        "set login_name = #{loginName,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "job_no = #{jobNo,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "mobile = #{mobile,jdbcType=VARCHAR},",
          "photo = #{photo,jdbcType=VARCHAR},",
          "login_flag = #{loginFlag,jdbcType=CHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "create_by = #{createBy,jdbcType=BIGINT},",
          "create_date = #{createDate,jdbcType=TIMESTAMP},",
          "last_modified_by = #{lastModifiedBy,jdbcType=BIGINT},",
          "last_modified_date = #{lastModifiedDate,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysUserEntity record);
}