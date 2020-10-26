package com.example.vietis.inteface;

import com.example.vietis.entity.User;

public interface IUserRepository {
    void afterLogin(User user, Exception error);
    void afterRegister(User user, Exception error);
}
