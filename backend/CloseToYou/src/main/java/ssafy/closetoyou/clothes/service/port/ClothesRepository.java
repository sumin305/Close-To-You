package ssafy.closetoyou.clothes.service.port;

import org.springframework.stereotype.Repository;
import ssafy.closetoyou.clothes.controller.request.ClothesCondition;
import ssafy.closetoyou.clothes.domain.Clothes;

import java.util.List;

public interface ClothesRepository {
    Clothes saveClothes(Clothes clothes);

    boolean existClothesByClothesId(Long clothesId);
    boolean existClothesByNfcId(Long nfcId);
    boolean existClothesByUserIdAndClothesNickname(Long userId, String clothesNickname);

    Clothes findClothesByClothesId(Long clothesId);
    Clothes findClothesByNfcId(Long nfcId);
    List<Clothes> findAllClothes(Long closetId);
    List<Clothes> searchClothesByClothesCondition(ClothesCondition clothesCondition);
    List<Clothes> searchClothesBySearchKeyword(String searchKeyword);
}
