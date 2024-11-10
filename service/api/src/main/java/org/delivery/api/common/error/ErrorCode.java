package org.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode implements ErrorCodeIfs{
    OK(200, 200, "성공"),
    Bad_REQUEST(HttpStatus.BAD_REQUEST.value(),400, "잘못된 요청" ),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 500, "서버에러"),
    NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR.value(), 512, "Null Point");
    private final Integer httpStatusCode;
    private final Integer errorCode;    // 내가 만든 애플리케이션의 custom 코드
    private final String description;
}
