package org.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Token의 경우 2000번대의 ERROR CODE 사용
@AllArgsConstructor
@Getter
public enum TokenErrorCode implements ErrorCodeIfs{
    INVALID_TOKEN(400, 2000, "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(400,2001, "만료된 토큰입니다."),
   TOKEN_EXCEPTION(400,2002, "알수없는 토큰 에러입니다");
    private final Integer httpStatusCode;
    private final Integer errorCode;    // 내가 만든 애플리케이션의 custom 코드
    private final String description;
}
