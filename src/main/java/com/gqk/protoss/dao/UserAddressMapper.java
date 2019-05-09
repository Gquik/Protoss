package com.gqk.protoss.dao;

import com.gqk.protoss.entity.UserAddress;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    UserAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);

    UserAddress selectByUserId(Integer userId);
}