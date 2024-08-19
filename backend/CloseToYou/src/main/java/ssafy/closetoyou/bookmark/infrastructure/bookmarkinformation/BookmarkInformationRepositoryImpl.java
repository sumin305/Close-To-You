package ssafy.closetoyou.bookmark.infrastructure.bookmarkinformation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ssafy.closetoyou.bookmark.service.port.BookmarkInformationRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookmarkInformationRepositoryImpl implements BookmarkInformationRepository {

    private final BookmarkInformationJpaRepository bookmarkInformationJpaRepository;


    @Override
    public BookmarkInformationEntity saveBookmarkInformation(BookmarkInformationEntity bookmarkInformationEntity) {
        return bookmarkInformationJpaRepository.save(bookmarkInformationEntity);
    }

    @Override
    public void deleteBookmarkInformationByClothesId(Long clothesId) {
        bookmarkInformationJpaRepository.deleteBookmarkInformationByClothesId(clothesId);
    }

    @Override
    public void deleteBookmarkInformationByBookmarkId(Long bookmarkId) {
        bookmarkInformationJpaRepository.deleteBookmarkInformationByBookmarkId(bookmarkId);
    }

    @Override
    public boolean existsBookmarkByBookmarkIdAndClothesId(Long bookmarkId, Long clothesId) {
        return bookmarkInformationJpaRepository.existsBookmarkByBookmarkIdAndClothesId(bookmarkId, clothesId);
    }

    @Override
    public List<Long> findClothesIdsByBookmarkId(Long bookmarkId) {
        return bookmarkInformationJpaRepository.findBookmarkInformationsByBookmarkId(bookmarkId)
                .stream()
                .map(BookmarkInformationEntity::getClothesId)
                .toList();
    }
}
