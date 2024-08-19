package ssafy.closetoyou.global.security.jwt.component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import ssafy.closetoyou.global.error.errorcode.CommonErrorCode;
import ssafy.closetoyou.global.error.errorcode.ErrorCode;
import ssafy.closetoyou.global.error.errorcode.UserErrorCode;

import java.io.IOException;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
            String exception = (String)request.getAttribute("exception");
            ErrorCode errorCode;

            log.debug(" log: exception: {}", exception);
        /*
        토큰 없는 경우
        */
            if (exception == null) {
                errorCode = UserErrorCode.NO_TOKEN_EXCEPTION;
                setResponse(response, errorCode);
                return;
            }
        /*
        해당하는 토큰 없는 경우
        */
            if (authException == null) {
                errorCode = UserErrorCode.NO_MATCH_TOKEN_EXCEPTION;
                setResponse(response, errorCode);
                return;
            }

        /*
        토큰이 이상한 경우
        */
            if (exception.equals(UserErrorCode.INVALID_TOKEN.name())) {
                errorCode = UserErrorCode.INVALID_TOKEN;
                setResponse(response, errorCode);
                return;
            }

        /*
        토큰이 만료된 경우
        */
            if (exception.equals(UserErrorCode.EXPIRED_TOKEN_EXCEPTION.name())) {
                errorCode = UserErrorCode.EXPIRED_TOKEN_EXCEPTION;
                setResponse(response, errorCode);
            }
        }

        private void setResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            String responseJson = "{" +
                    "\"code\": \"" + errorCode.getStatus().value() + "\", " +
                    "\"error\": \"" + errorCode.name() + "\", " +
                    "\"message\": \"" + errorCode.getMessage() + "\"" +
                    "}";

            response.getWriter().println(responseJson);
    }
}