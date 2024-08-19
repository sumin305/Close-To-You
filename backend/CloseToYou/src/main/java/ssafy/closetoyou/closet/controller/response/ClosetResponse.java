package ssafy.closetoyou.closet.controller.response;

import lombok.Builder;
import lombok.Getter;
import ssafy.closetoyou.closet.domain.Closet;

import java.time.LocalDateTime;

@Getter
public class ClosetResponse {
    private Long closetId;
    private String nickname;
    private Long userId;
    private String closetCode;
    private LocalDateTime createdDateTime;
    private LocalDateTime updateDateTime;
    private int clothesCount;

    @Builder
    public ClosetResponse(Long closetId, String nickname, Long userId, String closetCode, LocalDateTime createdDateTime, LocalDateTime updateDateTime, int clothesCount) {
        this.closetId = closetId;
        this.nickname = nickname;
        this.userId = userId;
        this.closetCode = closetCode;
        this.createdDateTime = createdDateTime;
        this.updateDateTime = updateDateTime;
        this.clothesCount = clothesCount;
    }


    public static ClosetResponse fromModel(Closet closet) {
        return ClosetResponse.builder()
                .closetId(closet.getClosetId())
                .nickname(closet.getNickname())
                .userId(closet.getUserId())
                .closetCode(closet.getClosetCode())
                .createdDateTime(closet.getCreatedDateTime())
                .updateDateTime(closet.getUpdateDateTime())
                .clothesCount(closet.getClothesCount())
                .build();
    }
}
