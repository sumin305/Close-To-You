package ssafy.closetoyou.closet.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ClosetRequest {

    @NotNull(message = "닉네임을 입력해주세요.")
    private String nickname;

    @NotNull(message = "옷장 코드를 입력해주세요.")
    private String closetCode;
}
