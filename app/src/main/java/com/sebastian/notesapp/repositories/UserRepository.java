package com.sebastian.notesapp.repositories;

import com.orm.SugarRecord;

import com.sebastian.notesapp.models.User;

import java.util.List;

public class UserRepository {

    public static void create(String nickname,String fullname, String email, String password) {

        User user = new User();
        user.setNickname(nickname);
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPassword(password);

        SugarRecord.save(user);
    }

    public static User login(String nickname, String password){

        List<User> users = SugarRecord.find(User.class,
                "nickname=? and password=?", nickname, password);

        if(!users.isEmpty()){
            return users.get(0);
        }
        return null;
    }

    public static User load(Long id){
        User user = SugarRecord.findById(User.class, id);
        return user;
    }
}
