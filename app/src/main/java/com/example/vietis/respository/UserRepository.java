package com.example.vietis.respository;

import com.example.vietis.entity.User;
import com.example.vietis.inteface.IUserRepository;

import java.util.List;

public class UserRepository {
    private static UserRepository instance = null;
    private IUserRepository iUserRepository;
    private List<User> users;
    public static final String URL_LOGIN =
            "http://"+Config.HOST_NAME+":"+Config.PORT+"/users/login";
    private UserRepository(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }
    public static UserRepository getInstance(IUserRepository iUserRepository) {
        if(instance == null) {
            instance = new UserRepository(iUserRepository);
        }
        return instance;
    }
    public void login(String email, String password){
    }
}
