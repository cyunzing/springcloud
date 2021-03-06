package com.zing.cloud.house.user.mapper;

import com.zing.cloud.house.user.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User selectById(Long id);

    List<User> select(User user);

    int update(User user);

    int insert(User account);

    int delete(String email);

    User selectByEmail(String email);
}
