package jesper.hasteen.shortr.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;

public interface ShortUrlRepository extends MongoRepository<ShortUrl, String> {

    void deleteByDateCreatedBefore(Instant date);

}
