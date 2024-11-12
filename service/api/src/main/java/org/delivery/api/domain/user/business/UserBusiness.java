package org.delivery.api.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.service.UserService;

import javax.validation.Valid;
import java.util.Optional;


@RequiredArgsConstructor
@Business
public class UserBusiness {
    private final UserService userService;
    private final UserConverter userConverter;
    // 사용자 가입처리 로직. request -> entity -> save() -> saved entity -> response -> return response
    public UserResponse register(UserRegisterRequest request) {
        var entity = userConverter.toEntity(request);
        var newEntity = userService.register(entity);
        var response = userConverter.toResponse(newEntity);
        return response;
//        return Optional.ofNullable(request)
//                .map(userConverter::toEntity)
//                .map(userService::register)
//                .map(userConverter::toResponse)
//                .orElseThrow(()->new ApiException(ErrorCode.NULL_POINT,"request NULL"));
    }
    // 로그인.
    // 사용자체크(email,password) -> user Entity 로그인 확인 -> token 생성 -> token response
    public UserResponse login(UserLoginRequest request) {
        var userEntity = userService.login(request.getEmail(),request.getPassword());
        // 사용자 없으면 throw
        // TODO 토큰 생성 (사용자 있다면)
        return userConverter.toResponse(userEntity);
    }
}
