package ssafy.closetoyou.clothes.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import ssafy.closetoyou.clothes.domain.Color;
import ssafy.closetoyou.clothes.domain.Pattern;
import ssafy.closetoyou.clothes.domain.Season;
import ssafy.closetoyou.clothes.domain.Type;
import ssafy.closetoyou.global.common.util.ValidEnum;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ClothesUpdateRequest {

    private String nickname;

    @ValidEnum(enumClass = Color.class)
    private String color;

    @ValidEnum(enumClass = Type.class)
    private String type;

    @ValidEnum(enumClass = Pattern.class)
    private String pattern;

    @ValidEnum(enumClass = Season.class)
    private String season;

    private String memo;

    private String size;

    @Builder
    public ClothesUpdateRequest(String nickname, String color, String type, String pattern, String season, String memo, String size) {
        this.nickname = nickname;
        this.color = color;
        this.type = type;
        this.pattern = pattern;
        this.season = season;
        this.memo = memo;
        this.size = size;
    }
}
