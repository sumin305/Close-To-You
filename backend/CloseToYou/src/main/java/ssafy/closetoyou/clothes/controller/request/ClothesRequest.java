package ssafy.closetoyou.clothes.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.closetoyou.closet.domain.Closet;
import ssafy.closetoyou.clothes.domain.*;
import ssafy.closetoyou.global.error.errorcode.ClothesErrorCode;
import ssafy.closetoyou.global.error.exception.CloseToYouException;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ClothesRequest {

    @NotNull(message = "옷 타입을 입력해주세요.")
    private Type type;

    @NotNull(message = "옷 패턴을 입력해주세요.")
    private Pattern pattern;

    @NotNull(message = "옷 색상을 입력해주세요.")
    private Color color;

    @NotNull(message = "옷 위치를 입력해주세요.")
    private String location;

    @NotNull(message = "옷장 아이디를 입력해주세요.")
    private Long closetId;

    @NotNull(message = "이미지 url을 입력해주세요.")
    private String imageUrl;

    @Builder
    public ClothesRequest(Type type, Pattern pattern, Color color, String location, Long closetId, String imageUrl) {
        this.type = type;
        this.pattern = pattern;
        this.color = color;
        this.location = location;
        this.closetId = closetId;
        this.imageUrl = imageUrl;
    }

    public Clothes toModel() {
        return Clothes.builder()
                .type(type)
                .color(color)
                .pattern(pattern)
                .location(location)
                .season(Season.DEFAULT)
                .closet(Closet.builder().closetId(closetId).build())
                .imageUrl(imageUrl)
                .build();
    }


}