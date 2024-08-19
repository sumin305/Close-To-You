package ssafy.closetoyou.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ssafy.closetoyou.global.error.errorcode.CommonErrorCode;
import ssafy.closetoyou.global.error.errorcode.ErrorCode;

@Getter
@AllArgsConstructor
public class CloseToYouException extends RuntimeException {
    private ErrorCode errorCode;
}
