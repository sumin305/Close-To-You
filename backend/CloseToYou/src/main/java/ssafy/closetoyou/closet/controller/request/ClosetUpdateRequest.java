package ssafy.closetoyou.closet.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ClosetUpdateRequest {
    @NotNull(message = "변경할 닉네임을 입력해주세요.")
    private String nickname;
}
