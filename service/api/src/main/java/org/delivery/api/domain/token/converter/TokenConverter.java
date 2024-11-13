package org.delivery.api.domain.token.converter;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.TokenErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.token.model.TokenDto;

import java.util.Objects;

@RequiredArgsConstructor
@Converter
public class TokenConverter {

    public TokenResponse toResponse(TokenDto accessToken,  TokenDto refreshToken){
        Objects.requireNonNull(refreshToken, ()->{
            throw new  ApiException(ErrorCode.NULL_POINT,"refreshToken이 존재하지 않습니다.");}
        );
        Objects.requireNonNull(accessToken, ()->{
            throw new  ApiException(ErrorCode.NULL_POINT,"accessToken이 존재하지 않습니다.");}
        );


        return TokenResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiredAt(accessToken.getExpiredAt())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                .build();
    }
}
