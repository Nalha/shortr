package jesper.hasteen.shortr.service;

import jesper.hasteen.shortr.mongo.ShortUrl;
import jesper.hasteen.shortr.mongo.ShortUrlRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UrlServiceIT {

    @Autowired
    private UrlService urlService;

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @Test
    void createShortUrl_saved_long_url_to_db() {
        final String longUrl = "isALongUrl";
        final String hashId = urlService.createAndStoreShortHash(longUrl);
        assertFalse(hashId.isBlank());

        ShortUrl dbResult = shortUrlRepository
                .findById(hashId)
                .orElseThrow(RuntimeException::new);

        assertEquals(longUrl, dbResult.getLongUri());
        assertEquals(hashId, dbResult.getHashId());
    }

    @Test
    void getLongUrl_returns_longUrl_from_db() {
        final String expectedLongUrl = "isTotallyALongUrl";
        final String hashId = "02f9n83f892f";
        shortUrlRepository.save(new ShortUrl(hashId, expectedLongUrl));
        final String longUrl = urlService
                .getLongUrl(hashId)
                .orElseThrow(RuntimeException::new);
        assertEquals(expectedLongUrl, longUrl);
    }

    @Test
    void getLongUrl_returns_empty_optional_when_missing() {
        final Optional<String> optional = urlService.getLongUrl("NONEXISTING");
        assertTrue(optional.isEmpty());
    }

    @Test
    void deleteEntriesOlderThan_deletes_entries_older_than_instant() {
        final ShortUrl oldEntity = new ShortUrl("123", "abc");
        oldEntity.setDateCreated(Instant.now().minus(40, ChronoUnit.DAYS));
        shortUrlRepository.save(oldEntity);

        final ShortUrl newEntity = new ShortUrl("456", "def");
        newEntity.setDateCreated(Instant.now());
        shortUrlRepository.save(newEntity);

        assertTrue(shortUrlRepository.findById("123").isPresent());
        assertTrue(shortUrlRepository.findById("456").isPresent());

        urlService.deleteEntriesOlderThan(Instant.now().minus(30, ChronoUnit.DAYS));

        assertTrue(shortUrlRepository.findById("123").isEmpty());
        assertTrue(shortUrlRepository.findById("456").isPresent());
    }
}