package ssafy.closetoyou.clothes.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ssafy.closetoyou.clothes.controller.request.ClothesCondition;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClothesJpaRepository extends JpaRepository<ClothesEntity,Integer> {
    boolean existsByClothesIdAndIsDeleted(Long clothesId, boolean deleted);
    boolean existsByNfcIdAndIsDeleted(Long nfcId, boolean deleted);

    @Query(" SELECT COUNT(c) > 0 " +
            "FROM ClothesEntity c " +
            "JOIN ClosetEntity cl ON c.closet.closetId = cl.closetId " +
            "WHERE cl.userId = :userId " +
            "AND c.nickname = :nickname " +
            "AND c.isDeleted = :isDeleted")
    boolean existsByUserIdAndNicknameAndIsDeleted(@Param("userId") Long userId,
                                                  @Param("nickname") String nickname,
                                                  @Param("isDeleted") boolean isDeleted);

    Optional<ClothesEntity> findClothesByClothesIdAndIsDeleted(Long clothesId, boolean deleted);

    Optional<ClothesEntity> findClothesByNfcIdAndIsDeleted(Long nfcId, boolean deleted);

    @Query("SELECT c " +
            "FROM ClothesEntity c " +
            "JOIN ClosetEntity cl ON c.closet.closetId = cl.closetId " +
            "WHERE cl.userId = :userId " +
            "AND c.isDeleted = :isDeleted " +
            "ORDER BY c.wearingCount DESC")
    Optional<List<ClothesEntity>> findAllByUserIdAndIsDeleted(@Param("userId") Long userId,
                                                    @Param("isDeleted") boolean isDeleted);

    @Query("SELECT c " +
            "FROM ClothesEntity c " +
            "WHERE (:#{#clothesCondition.pattern} IS NULL OR c.pattern = :#{#clothesCondition.pattern}) " +
            "AND (:#{#clothesCondition.color} IS NULL OR c.color = :#{#clothesCondition.color}) " +
            "AND (:#{#clothesCondition.type} IS NULL OR c.type = :#{#clothesCondition.type}) " +
            "AND c.isDeleted = :isDeleted " +
            "AND (:#{#clothesCondition.closetId} = 0 OR c.closet.closetId = :#{#clothesCondition.closetId}) " +
            "ORDER BY c.wearingCount DESC")
    Optional<List<ClothesEntity>> searchClothesByClosetIdAndClothesConditionAndIsDeleted(@Param("clothesCondition") ClothesCondition clothesCondition,
                                                                               @Param("isDeleted") boolean isDeleted);

    @Query("SELECT c " +
            "FROM ClothesEntity c " +
            "JOIN ClosetEntity cl ON c.closet.closetId = cl.closetId " +
            "WHERE ( c.nickname LIKE concat('%', :searchKeyword,'%') " +
            "OR c.color LIKE concat('%', :searchKeyword,'%') " +
            "OR c.type LIKE concat('%', :searchKeyword,'%') " +
            "OR c.pattern LIKE concat('%', :searchKeyword,'%') )" +
            "AND c.isDeleted = :isDeleted " +
            "ORDER BY c.wearingCount DESC ")
    Optional<List<ClothesEntity>> searchClothesByUserIdAndSearchKeywordAndIsDeleted(@Param("searchKeyword") String searchKeyword,
                                                                                    @Param("isDeleted") boolean isDeleted);

    @Query("SELECT COUNT(*) " +
            "FROM ClothesEntity c " +
            "JOIN ClosetEntity cl ON c.closet.closetId = cl.closetId " +
            "WHERE cl.closetId = :closetId " +
            "AND c.isDeleted = :isDeleted")
    int countClothesByClosetIdAndIsDeleted(@Param("closetId") Long closetId,
                                           @Param("isDeleted") boolean isDeleted);
}
