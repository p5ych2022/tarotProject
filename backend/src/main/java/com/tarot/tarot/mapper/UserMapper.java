package com.tarot.tarot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tarot.tarot.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    // login
    public User getUserByName(String userName);
    public User getUserByEmail(String email);
    // register
    public int addUser(User user);

    // forget password
    boolean changePassword(String email, String encodedPassword);

    // check if email or username exists
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}