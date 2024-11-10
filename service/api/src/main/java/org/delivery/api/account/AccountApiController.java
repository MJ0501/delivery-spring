package org.delivery.api.account;

import lombok.RequiredArgsConstructor;
import org.delivery.api.account.model.AccountMeResponse;
import org.delivery.api.common.api.Api;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.UserErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.account.AccountEntity;
import org.delivery.db.account.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountApiController {

    private final AccountRepository accountRepository;

    @GetMapping("/me")
    public Api<AccountMeResponse> me(){
   // public Api<Object> me(){
        var response = AccountMeResponse.builder()
                .name("길똥")
                .email("A@gmail.com")
                .registeredAt(LocalDateTime.now())
                .build();
        var str = "안녕하세요";
        var age = 0;
        try{
            Integer.parseInt(str);
        }catch(Exception e){
            throw new ApiException(ErrorCode.SERVER_ERROR,e,"사용자 me호출시 error 발생");
        }

        return Api.OK(response);
        //return Api.ERROR(UserErrorCode.USER_NOT_FOUND, "길똥 없음");
    }
}
