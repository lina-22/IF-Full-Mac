package com.serverSide.javaSpringBoot.services;

import com.serverSide.javaSpringBoot.dto.BaseResponseDto;
import com.serverSide.javaSpringBoot.model.UserModel;
import java.util.List;

public interface UsersService {

     UserModel create(UserModel userModel);
     UserModel findByEmail(String email);
     List<UserModel> findAll();

     BaseResponseDto update(UserModel userModel);

     void delete(long users_id);

}
