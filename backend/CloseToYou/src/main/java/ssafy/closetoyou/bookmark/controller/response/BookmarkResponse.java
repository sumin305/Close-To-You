package ssafy.closetoyou.bookmark.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.closetoyou.clothes.controller.response.ClothesDetail;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class BookmarkResponse {
    private Long bookmarkId;
    private String nickname;
    private Long userId;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    private List<ClothesDetail> clothes;

    @Builder
    public BookmarkResponse(Long bookmarkId, String nickname, Long userId, LocalDateTime createDateTime, LocalDateTime updateDateTime, List<ClothesDetail> clothes) {
        this.bookmarkId = bookmarkId;
        this.nickname = nickname;
        this.userId = userId;
        this.createDateTime = createDateTime;
        this.updateDateTime = updateDateTime;
        this.clothes = clothes;
    }
}
