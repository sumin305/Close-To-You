package ssafy.closetoyou.clothes.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ssafy.closetoyou.clothes.controller.request.ClothesCondition;
import ssafy.closetoyou.clothes.domain.Clothes;
import ssafy.closetoyou.clothes.service.port.ClothesRepository;
import ssafy.closetoyou.global.error.errorcode.ClothesErrorCode;
import ssafy.closetoyou.global.error.exception.CloseToYouException;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClothesRepositoryImpl implements ClothesRepository {

    private final ClothesJpaRepository clothesJpaRepository;

    @Override
    public Clothes saveClothes(Clothes clothes) {
        return clothesJpaRepository.save(ClothesEntity.fromModel(clothes)).toModel();
    }

    @Override
    public boolean existClothesByClothesId(Long clothesId) {
        return clothesJpaRepository.existsByClothesIdAndIsDeleted(clothesId, false);
    }

    @Override
    public boolean existClothesByNfcId(Long nfcId) {
        return clothesJpaRepository.existsByNfcIdAndIsDeleted(nfcId, false);
    }

    @Override
    public boolean existClothesByUserIdAndClothesNickname(Long userId, String clothesNickname) {
        return clothesJpaRepository.existsByUserIdAndNicknameAndIsDeleted(userId, clothesNickname, false);
    }


    @Override
    public Clothes findClothesByClothesId(Long clothesId) {
        return clothesJpaRepository.findClothesByClothesIdAndIsDeleted(clothesId, false)
                .orElseThrow(() -> new CloseToYouException(ClothesErrorCode.NO_CLOTHES_EXCEPTION))
                .toModel();
    }

    @Override
    public Clothes findClothesByNfcId(Long nfcId) {
        return clothesJpaRepository.findClothesByNfcIdAndIsDeleted(nfcId, false)
                .orElseThrow(() -> new CloseToYouException(ClothesErrorCode.NO_CLOTHES_EXCEPTION))
                .toModel();
    }

    @Override
    public List<Clothes> findAllClothes(Long userId) {
        return clothesJpaRepository
                .findAllByUserIdAndIsDeleted(userId, false)
                .orElse(Collections.emptyList())
                .stream()
                .map(ClothesEntity::toModel)
                .toList();
    }

    @Override
    public List<Clothes> searchClothesByClothesCondition(ClothesCondition clothesCondition) {
        return clothesJpaRepository
                .searchClothesByClosetIdAndClothesConditionAndIsDeleted(clothesCondition, false)
                .orElse(Collections.emptyList())
                .stream()
                .map(ClothesEntity::toModel)
                .toList();
    }

    @Override
    public List<Clothes> searchClothesBySearchKeyword(String searchKeyword) {
        return clothesJpaRepository
                .searchClothesByUserIdAndSearchKeywordAndIsDeleted(searchKeyword, false)
                .orElse(Collections.emptyList())
                .stream()
                .map(ClothesEntity::toModel)
                .toList();
    }
}
