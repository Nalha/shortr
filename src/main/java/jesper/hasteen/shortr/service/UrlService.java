package jesper.hasteen.shortr.service;

import jesper.hasteen.shortr.mongo.ShortUrl;
import jesper.hasteen.shortr.mongo.ShortUrlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class UrlService {

    Logger log = LoggerFactory.getLogger(UrlService.class);

    private final HashGenerator hashGenerator;
    private final ShortUrlRepository shortUrlRepository;

    public UrlService(HashGenerator hashGenerator, ShortUrlRepository shortUrlRepository) {
        this.hashGenerator = hashGenerator;
        this.shortUrlRepository = shortUrlRepository;
    }

    /**
     * Stores the given string in database and creates a hash it can be fetched with later.
     *
     * @param longUrl Any string.
     * @return A hash id.
     */
    public String createAndStoreShortHash(String longUrl) {
        final String shortHash = hashGenerator.generate(longUrl);
        final ShortUrl shortUrl = new ShortUrl(shortHash, longUrl);
        shortUrlRepository.save(shortUrl);
        return shortUrl.getHashId();
    }

    /**
     * Gets the string previously saved by {@link #createAndStoreShortHash(String)}
     *
     * @param hash A hashed id.
     * @return An optional of the saved string.
     */
    public Optional<String> getLongUrl(String hash) {
        return shortUrlRepository
                .findById(hash)
                .map(ShortUrl::getLongUri);
    }

    public void deleteEntriesOlderThan(Instant instant) {
        log.info("Deleting old entries. dateThreshold=\"{}\"", instant);
        shortUrlRepository.deleteByDateCreatedBefore(instant);
    }
}
