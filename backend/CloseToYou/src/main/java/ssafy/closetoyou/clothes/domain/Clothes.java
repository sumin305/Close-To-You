package ssafy.closetoyou.clothes.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import ssafy.closetoyou.closet.domain.Closet;
import ssafy.closetoyou.clothes.controller.request.ClothesUpdateRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
public class Clothes {
    private Long clothesId;
    private Closet closet;
    private Long nfcId;
    private String location;
    private String nickname;
    private Type type;
    private Pattern pattern;
    private Color color;
    private Season season;
    private String size;
    private String memo;
    private int wearingCount;

    private Boolean isDeleted;
    private String imageUrl;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private LocalDate lastWornDate;

    public void delete() {
        this.isDeleted = Boolean.TRUE;
    }
    @Builder
    public Clothes(Long clothesId, Closet closet, Long nfcId, String location, String nickname, Type type, Pattern pattern, Color color, Season season, String size, String memo, int wearingCount, Boolean isDeleted, String imageUrl, LocalDateTime createdDateTime, LocalDateTime updatedDateTime, LocalDate lastWornDate) {
        this.clothesId = clothesId;
        this.closet = closet;
        this.nfcId = nfcId;
        this.location = location;
        this.nickname = nickname;
        this.type = type;
        this.pattern = pattern;
        this.color = color;
        this.season = season;
        this.size = size;
        this.memo = memo;
        this.wearingCount = wearingCount;
        this.isDeleted = isDeleted;
        this.imageUrl = imageUrl;
        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updatedDateTime;
        this.lastWornDate = lastWornDate;
    }
    public Clothes(Long clothesId, Closet closet, String location, String nickname, Type type, Pattern pattern, Season season, Color color) {
        this.clothesId = clothesId;
        this.closet = closet;
        this.location = location;
        this.nickname = nickname;
        this.type = type;
        this.pattern = pattern;
        this.color = color;
        this.season = season;
    }

    public Clothes(Long clothesId, Long nfcId, Closet closet, String location, String nickname, Type type, Pattern pattern, Season season, Color color, Boolean isDeleted) {
        this.clothesId = clothesId;
        this.nfcId = nfcId;
        this.closet = closet;
        this.location = location;
        this.nickname = nickname;
        this.type = type;
        this.pattern = pattern;
        this.color = color;
        this.season = season;
        this.isDeleted = isDeleted;
    }

    public void changeClothesInfo(ClothesUpdateRequest clothesUpdateRequest) {
        if (clothesUpdateRequest.getNickname() != null) this.nickname = clothesUpdateRequest.getNickname();
        if (clothesUpdateRequest.getType() != null) this.type = Type.valueOf(clothesUpdateRequest.getType());
        if (clothesUpdateRequest.getPattern() != null) this.pattern = Pattern.valueOf(clothesUpdateRequest.getPattern());
        if (clothesUpdateRequest.getColor() != null) this.color = Color.valueOf(clothesUpdateRequest.getColor());
        if (clothesUpdateRequest.getMemo() != null) this.memo = clothesUpdateRequest.getMemo();
        if (clothesUpdateRequest.getSeason() != null) this.season = Season.valueOf(clothesUpdateRequest.getSeason());
        if (clothesUpdateRequest.getSize() != null) this.size = clothesUpdateRequest.getSize();
    }
}
