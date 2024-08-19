package ssafy.closetoyou.global.error.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BookmarkErrorCode implements ErrorCode {

    DUPLICATED_BOOKMARK_NICKNAME(HttpStatus.CONFLICT, "이미 존재하는 북마크 닉네임입니다."),
    DUPLICATED_BOOKMARK_CLOTHES(HttpStatus.CONFLICT, "북마크에 해당 옷이 이미 포함되어 있습니다."),
    NO_BOOKMARK_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 북마크 입니다");
    private final HttpStatus status;
    private final String message;
}
