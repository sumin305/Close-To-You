package ssafy.closetoyou.clothes.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import ssafy.closetoyou.clothes.domain.Color;
import ssafy.closetoyou.clothes.domain.Pattern;
import ssafy.closetoyou.clothes.domain.Type;
import ssafy.closetoyou.global.common.util.ValidEnum;

@Getter
public class ClothesCondition {

    @NotNull(message = "옷장 아이디를 입력해주세요.")
    private Long closetId;

    @ValidEnum(enumClass = Color.class, message = "옷 색상이 아닙니다.")
    private String color;

    @ValidEnum(enumClass = Type.class, message = "옷 타입이 아닙니다.")
    private String type;

    @ValidEnum(enumClass = Pattern.class, message = "옷 패턴이 아닙니다.")
    private String pattern;

    @Builder
    public ClothesCondition(Long closetId, String color, String type, String pattern) {
        this.closetId = closetId;
        this.color = color;
        this.type = type;
        this.pattern = pattern;
    }
}
