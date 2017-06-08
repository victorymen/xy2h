package com.xy2h.mapper;

import com.xy2h.pojo.TbAdminUser;
import com.xy2h.pojo.TbAdminUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.0.0
 * @Date 2016/12/27
 * @Description
 */
public interface TbAdminUserMapper {

    int countByExample(TbAdminUserExample example);

    int deleteByExample(TbAdminUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbAdminUser record);

    int insertSelective(TbAdminUser record);

    List<TbAdminUser> selectByExample(TbAdminUserExample example);

    TbAdminUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbAdminUser record, @Param("example") TbAdminUserExample example);

    int updateByExample(@Param("record") TbAdminUser record, @Param("example") TbAdminUserExample example);

    int updateByPrimaryKeySelective(TbAdminUser record);

    int updateByPrimaryKey(TbAdminUser record);
}
