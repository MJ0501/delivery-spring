package org.delivery.api.interceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Authorization Interceptor url: {}", request.getRequestURI());
        // WEB, chrome 의 경우 GET, POST mothod 지원하는지 사전에 확인, OPTIONS pass
        if(HttpMethod.OPTIONS.matches(request.getMethod())){
            return true;
        }
        // resource (.js, .html, .css, .png ..) 요청시 pass
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }
        // Header 검증

        return true;
    }
}
