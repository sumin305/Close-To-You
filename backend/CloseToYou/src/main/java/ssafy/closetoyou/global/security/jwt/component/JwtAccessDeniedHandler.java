package ssafy.closetoyou.global.security.jwt.component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import ssafy.closetoyou.global.error.errorcode.CommonErrorCode;
import ssafy.closetoyou.global.error.errorcode.UserErrorCode;

import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        String responseJson = "{" +
                "\"status\": \"" + UserErrorCode.INVALID_ACCESS_TOKEN.getStatus().value() + "\", " +
                "\"error\": \"" + UserErrorCode.INVALID_ACCESS_TOKEN.name() + "\", " +
                "\"message\": \"" + UserErrorCode.INVALID_ACCESS_TOKEN.getMessage() + "\"" +
                "}";

        response.getWriter().println(responseJson);
    }
}



