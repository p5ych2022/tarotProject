package com.tarot.tarot.service;

import com.tarot.tarot.model.User;
import com.tarot.tarot.mapper.UserMapper;
import com.tarot.tarot.Security.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    // private Map<String, Integer> emailSendCount = new HashMap<>();
    private Map<String, Long> emailCodeTimeMap = new HashMap<>();
    private static final long CODE_EXPIRATION_TIME = 60 * 1000; // 60 seconds
    private Map<String, String> verificationCodes = new HashMap<>(); // Store codes temporarily

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JavaMailSender mailSender;


    public static boolean isEmail(String input){
        String reg = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9_-]+\\.com$";
        return input.matches(reg);
    }

    @Override
    public boolean verifyCode(String email, String inputCode)  throws Exception{
        long sentTime = emailCodeTimeMap.get(email);
        if (System.currentTimeMillis() - sentTime > CODE_EXPIRATION_TIME) {
            throw new Exception("验证码已过期");
        }
        if (!verificationCodes.get(email).equals(inputCode)) {
            throw new Exception("验证码错误");
        }
        return true;
    }

    @Override
    public String loginUser(String nameOrEmail, String password) throws RuntimeException{
        User user;
        // 判断输入的是 email 还是 username
        if (isEmail(nameOrEmail)) {
            // email
            user = userMapper.getUserByEmail(nameOrEmail);
            if (user == null) {
                throw new RuntimeException("邮箱未注册");
            }
        } else {
            // username
            user = userMapper.getUserByName(nameOrEmail);
            if (user == null) {
                throw new RuntimeException("用户名不存在");
            }
        }

//        if (user == null) {
//            throw new RuntimeException("用户不存在");
//        }

        // 验证密码
        String encodedPassword = Md5Util.md5(password);
        if (encodedPassword != null && !encodedPassword.equals(user.getPassword())) {
            throw new RuntimeException("密码错误");
        }
        // 返回登录成功的标识，可以是 JWT token 也可以是其他标识
        return "登录成功";
    }

    @Override
    public User registerUser(String email, String username, String password) throws Exception {
        if (userMapper.existsByEmail(email)) {
            throw new Exception("邮箱已被注册");
        }
        if (userMapper.existsByUsername(username)) {
            throw new Exception("用户名已被注册");
        }

        // 密码加密
        String encodedPassword = Md5Util.md5(password);

        // 创建用户对象
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setUsername(username);
        newUser.setPassword(encodedPassword);


        // 添加到数据库
        userMapper.addUser(newUser);

        // 生成验证令牌（例如UUID）
        String token = UUID.randomUUID().toString();
        // 保存token到数据库（创建token表与用户关联）

        // 发送验证邮件
        String verifyCode = sendEmail(email);

        // 返回创建成功的用户
        return newUser;
    }

    @Override
    public void forgetPassword(String email, String newPassword) throws Exception {
        if (!userMapper.existsByEmail(email)) {
            throw new Exception("邮箱未注册");
        }

        // 加密新密码
        // String encodedPassword = passwordEncoder.encode(newPassword);
        String encodedPassword = Md5Util.md5(newPassword);

        // 更新密码
        int updateCount = userMapper.changePassword(email, encodedPassword);

        if (updateCount == 0) {
            throw new Exception("更新密码失败");
        }
    }

    @Override
    public String  sendEmail(String email) throws Exception {

//        if (emailSendCount.getOrDefault(email, 0) >= 3) {
//            throw new Exception("同一邮箱每天只能发送3次验证码");
//        }

        String verificationCode = String.valueOf(new Random().nextInt(99999));

        // Send the email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Psych's tarot master");
        message.setText("Your verification code is: " + verificationCode);

        mailSender.send(message);

        // Store the verification code in a map or cache for later verification
        verificationCodes.put(email, verificationCode);
        emailCodeTimeMap.put(email, System.currentTimeMillis());

        return verificationCode;
    }



}
