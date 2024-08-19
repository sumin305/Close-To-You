package ssafy.closetoyou.bookmark.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BookmarkRequest {

    @NotNull(message = "닉네임을 입력해주세요.")
    private String nickname;

    @Builder
    public BookmarkRequest(String nickname) {
        this.nickname = nickname;
    }
}
