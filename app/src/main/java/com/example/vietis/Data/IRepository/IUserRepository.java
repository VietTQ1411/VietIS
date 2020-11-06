package com.example.vietis.Data.IRepository;

import com.example.vietis.Data.entity.Image;
import com.example.vietis.Data.entity.User;

public interface IUserRepository {
    void afterLogin(User user, Exception error);
    void afterRegister(User user, Exception error);
    void getSettingData(User user,Exception error);
}
