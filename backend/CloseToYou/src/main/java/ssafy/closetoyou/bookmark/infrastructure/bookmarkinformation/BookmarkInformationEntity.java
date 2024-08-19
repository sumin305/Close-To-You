package ssafy.closetoyou.bookmark.infrastructure.bookmarkinformation;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity(name = "bookmark_informations")
@Getter
@EntityListeners(AuditingEntityListener.class)
@RequiredArgsConstructor
public class BookmarkInformationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookmarkInformationId;

    private Long clothesId;
    private Long bookmarkId;

    @CreatedDate
    private LocalDateTime createdDateTime;

    @LastModifiedDate
    private LocalDateTime updatedDateTime;

    @Builder
    public BookmarkInformationEntity(Long bookmarkInformationId, Long clothesId, Long bookmarkId, LocalDateTime createdDateTime, LocalDateTime updatedDateTime) {
        this.bookmarkInformationId = bookmarkInformationId;
        this.clothesId = clothesId;
        this.bookmarkId = bookmarkId;
        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updatedDateTime;
    }
}
