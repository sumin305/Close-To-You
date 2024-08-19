package ssafy.closetoyou.closet.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ssafy.closetoyou.closet.domain.Closet;
import ssafy.closetoyou.closet.service.port.ClosetRepository;
import ssafy.closetoyou.clothes.infrastructure.ClothesJpaRepository;
import ssafy.closetoyou.global.error.errorcode.ClosetErrorCode;
import ssafy.closetoyou.global.error.exception.CloseToYouException;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClosetRepositoryImpl implements ClosetRepository {

    private final ClosetJpaRepository closetJpaRepository;
    private final ClothesJpaRepository clothesJpaRepository;

    @Override
    public Closet saveCloset(Closet closet) {
        return closetJpaRepository.save(ClosetEntity.fromModel(closet)).toModel();
    }

    @Override
    public boolean existsClosetByClosetNickname(Long userId, String closetNickname) {
        return closetJpaRepository.existsByNicknameIsDeleted(userId, closetNickname, false);
    }

    @Override
    public boolean existsClosetByClosetId(Long closetId) {
        return closetJpaRepository.existsByClosetIdAndIsDeleted(closetId, false);
    }

    @Override
    public List<Closet> getUserClosets(Long userId) {
        return closetJpaRepository.findClosetsByUserIdAndIsDeleted(userId, false)
                .orElseThrow(() -> new CloseToYouException(ClosetErrorCode.NO_CLOSET_EXCEPTION))
                .stream()
                .map(closetEntity -> {
                    Closet closet = closetEntity.toModel();
                    closet.updateClothesCount(clothesJpaRepository.countClothesByClosetIdAndIsDeleted(closet.getClosetId(), false));
                    return closet;
                })
                .toList();
    }

    @Override
    public Closet getClosetByClosetId(Long closetId) {
        return closetJpaRepository.findClosetByClosetIdAndIsDeleted(closetId, false)
                .orElseThrow(() -> new CloseToYouException(ClosetErrorCode.NO_CLOSET_CODE_EXCEPTION))
                .toModel();
    }
}