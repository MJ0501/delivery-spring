package org.delivery.api.domain.token.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.UserErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.ifs.TokenHelperIfs;
import org.delivery.api.domain.token.model.TokenDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

// Token domain 로직 담당
@RequiredArgsConstructor
@Service
public class TokenService {
    // TokenHelperIfs - jwt 방식이든 다른 방식이든 변경가능
    private final TokenHelperIfs tokenHelperIfs;
    // Long userId 는 바뀔 수 있음.
    public TokenDto issueAccessToken(Long userId){
        var data = new HashMap<String, Object>();
        data.put("userId", userId);
        return tokenHelperIfs.issueAccessToken(data);
    }

    public TokenDto issueRefreshToken(Long userId){
        var data = new HashMap<String, Object>();
        data.put("userId", userId);
        return tokenHelperIfs.issueRefreshToken(data);
    }
    public Long validationToken(String token){
        var map = tokenHelperIfs.validationTokenWithThrow(token);
        var userId = map.get("userId");
        Objects.requireNonNull(userId, ()->{
            throw new ApiException(ErrorCode.NULL_POINT, "userId가 존재하지 않습니다.");
        });
        return Long.parseLong(userId.toString());
    }
}
