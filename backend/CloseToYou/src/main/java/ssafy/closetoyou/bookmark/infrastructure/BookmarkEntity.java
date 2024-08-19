package ssafy.closetoyou.bookmark.infrastructure;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ssafy.closetoyou.bookmark.domain.Bookmark;

import java.time.LocalDateTime;

@Entity(name = "bookmarks")
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class BookmarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookmarkId;
    private String nickname;
    private Long userId;
    private Boolean isDeleted = false;

    @CreatedDate
    private LocalDateTime createdDateTime;

    @LastModifiedDate
    private LocalDateTime updateDateTime;

    @Builder
    public BookmarkEntity(Long bookmarkId, String nickname, Long userId, Boolean isDeleted, LocalDateTime createdDateTime, LocalDateTime updateDateTime) {
        this.bookmarkId = bookmarkId;
        this.nickname = nickname;
        this.userId = userId;
        this.isDeleted = isDeleted;
        this.createdDateTime = createdDateTime;
        this.updateDateTime = updateDateTime;
    }

    public static BookmarkEntity fromModel(Bookmark bookmark) {
        return BookmarkEntity.builder()
                .bookmarkId(bookmark.getBookmarkId())
                .nickname(bookmark.getNickname())
                .userId(bookmark.getUserId())
                .isDeleted(bookmark.getIsDeleted() != null && bookmark.getIsDeleted())
                .createdDateTime(bookmark.getCreatedDateTime())
                .updateDateTime(bookmark.getUpdateDateTime())
                .build();
    }

    public Bookmark toModel() {
        return Bookmark.builder()
                .bookmarkId(bookmarkId)
                .nickname(nickname)
                .userId(userId)
                .isDeleted(isDeleted)
                .createdDateTime(createdDateTime)
                .updateDateTime(updateDateTime)
                .build();
    }
}
