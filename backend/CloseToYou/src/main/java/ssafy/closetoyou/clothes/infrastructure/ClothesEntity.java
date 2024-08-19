package ssafy.closetoyou.clothes.infrastructure;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ssafy.closetoyou.closet.infrastructure.ClosetEntity;
import ssafy.closetoyou.clothes.domain.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "clothes")
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ClothesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clothesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "closet_id")
    private ClosetEntity closet;

    private Long nfcId;
    private String location;
    private String nickname;
    private String type;
    private String pattern;
    private String color;
    private String size;
    private String season;
    private String memo;

    private int wearingCount;
    private Boolean isDeleted;

    @CreatedDate
    private LocalDateTime createdDateTime;

    @LastModifiedDate
    private LocalDateTime updatedDateTime;
    private LocalDate lastWornDate;

    private String imageUrl;

    @Builder
    public ClothesEntity(Long clothesId, ClosetEntity closet, Long nfcId, String location, String nickname, String type, String pattern, String color, String size, String season, String memo, int wearingCount, Boolean isDeleted, LocalDateTime createdDateTime, LocalDateTime updatedDateTime, LocalDate lastWornDate, String imageUrl) {
        this.clothesId = clothesId;
        this.closet = closet;
        this.nfcId = nfcId;
        this.location = location;
        this.nickname = nickname;
        this.type = type;
        this.pattern = pattern;
        this.color = color;
        this.size = size;
        this.season = season;
        this.memo = memo;
        this.wearingCount = wearingCount;
        this.isDeleted = isDeleted;
        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updatedDateTime;
        this.lastWornDate = lastWornDate;
        this.imageUrl = imageUrl;
    }


    public static ClothesEntity fromModel(Clothes clothes) {
        return ClothesEntity.builder()
                .clothesId(clothes.getClothesId())
                .closet(ClosetEntity.fromModel(clothes.getCloset()))
                .nfcId(clothes.getNfcId())
                .location(clothes.getLocation())
                .nickname(clothes.getNickname())
                .type(String.valueOf(clothes.getType()))
                .pattern(String.valueOf(clothes.getPattern()))
                .color(String.valueOf(clothes.getColor()))
                .size(clothes.getSize() == null ? "" : clothes.getSize())
                .season(clothes.getSeason() == null ? "" : String.valueOf(clothes.getSeason()))
                .memo(clothes.getMemo() == null ? "" : clothes.getMemo())
                .wearingCount(clothes.getWearingCount())
                .isDeleted(clothes.getIsDeleted() != null && clothes.getIsDeleted())
                .createdDateTime(clothes.getCreatedDateTime())
                .updatedDateTime(clothes.getUpdatedDateTime())
                .lastWornDate(clothes.getLastWornDate())
                .imageUrl(clothes.getImageUrl())
                .build();
    }

    public Clothes toModel() {
        return Clothes.builder()
                .closet(closet.toModel())
                .nfcId(nfcId)
                .clothesId(clothesId)
                .nickname(nickname)
                .type(Type.valueOf(type))
                .pattern(Pattern.valueOf(pattern))
                .color(Color.valueOf(color))
                .season(season == null ? Season.DEFAULT : Season.valueOf(season))
                .size(size)
                .memo(memo)
                .wearingCount(wearingCount)
                .location(location)
                .isDeleted(isDeleted)
                .createdDateTime(createdDateTime)
                .updatedDateTime(updatedDateTime)
                .lastWornDate(lastWornDate)
                .imageUrl(imageUrl)
                .build();
    }
}
