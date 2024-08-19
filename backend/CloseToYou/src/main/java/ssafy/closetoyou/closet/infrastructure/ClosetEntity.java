package ssafy.closetoyou.closet.infrastructure;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ssafy.closetoyou.closet.domain.Closet;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Entity
@Table(name = "closets")
public class ClosetEntity {

    @Id
    @Column(name = "closet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long closetId;
    private Long userId;
    private String closetCode;
    private String nickname;
    private Boolean isDeleted = false;

    @CreatedDate
    LocalDateTime createdDateTime;

    @LastModifiedDate
    LocalDateTime updatedDateTime;

    @Builder
    public ClosetEntity(Long closetId, Long userId, String closetCode, String nickname, Boolean isDeleted, LocalDateTime createdDateTime, LocalDateTime updatedDateTime) {
        this.closetId = closetId;
        this.userId = userId;
        this.closetCode = closetCode;
        this.nickname = nickname;
        this.isDeleted = isDeleted;
        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updatedDateTime;
    }

    public static ClosetEntity fromModel(Closet closet) {
        return ClosetEntity.builder()
                .closetId(closet.getClosetId())
                .userId(closet.getUserId())
                .closetCode(closet.getClosetCode())
                .nickname(closet.getNickname())
                .isDeleted(closet.getIsDeleted() != null && closet.getIsDeleted())
                .build();
    }

    public Closet toModel() {
        return Closet.builder()
                .closetId(closetId)
                .userId(userId)
                .closetCode(closetCode)
                .nickname(nickname)
                .isDeleted(isDeleted)
                .createdDateTime(createdDateTime)
                .updateDateTime(updatedDateTime)
                .build();
    }
}