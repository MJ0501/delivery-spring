package org.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
// User의 경우 1000번대의 ERROR CODE 사용
@AllArgsConstructor
@Getter
public enum UserErrorCode implements ErrorCodeIfs{
    USER_NOT_FOUND(400, 1401, "사용자를 찾을 수 없음");
    private final Integer httpStatusCode;
    private final Integer errorCode;    // 내가 만든 애플리케이션의 custom 코드
    private final String description;
}
