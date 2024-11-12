package org.delivery.api.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.UserErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.user.UserEntity;
import org.delivery.db.user.UserRepository;
import org.delivery.db.user.enums.UserStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

// user domain 로직 처리 하는 서비스
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    // userEntity 가 들어오고 나간다!
    public UserEntity register(UserEntity userEntity){
        return Optional.ofNullable(userEntity).map(it -> {
            userEntity.setStatus(UserStatus.REGISTERED);
            userEntity.setRegistered_at(LocalDateTime.now());
            return userRepository.save(userEntity);
        }).orElseThrow(()->new ApiException(ErrorCode.NULL_POINT,"User Entity NULL"));
    }
    public UserEntity login(String email, String password){
        var entity = getUserWithThrow(email, password);
        return entity;
    }
    public UserEntity getUserWithThrow(
            String email, String password
    ){
        return userRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(
                email,password,UserStatus.REGISTERED
        ).orElseThrow(()->new ApiException(UserErrorCode.USER_NOT_FOUND));
    }
}
