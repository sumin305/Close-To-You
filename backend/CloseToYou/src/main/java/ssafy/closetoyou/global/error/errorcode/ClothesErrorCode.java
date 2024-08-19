package ssafy.closetoyou.global.error.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ClothesErrorCode implements ErrorCode {
    DUPLICATE_CLOTHES_NICKNAME(HttpStatus.CONFLICT, "이미 존재하는 닉네임 입니다."),
    DUPLICATE_CLOTHES(HttpStatus.CONFLICT, "이미 존재하는 옷 입니다"),
    NO_CLOTHES_EXCEPTION(HttpStatus.NOT_FOUND, "해당 아이디에 해당하는 옷이 없습니다."),
    NO_CLOSET_EXCEPTION(HttpStatus.NOT_FOUND, "해당 아이디에 해당하는 옷장이 없습니다."),
    NO_NFC_EXCEPTION(HttpStatus.NOT_FOUND, "해당 NFC 아이디에 해당하는 옷이 없습니다."),
    NO_ENUM_TYPE_EXCEPTION(HttpStatus.BAD_REQUEST, "해당 문자열에 해당하는 Enum 상수가 없습니다.");
    private final HttpStatus status;
    private final String message;
}
