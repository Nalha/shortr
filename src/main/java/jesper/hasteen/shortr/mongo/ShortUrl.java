package jesper.hasteen.shortr.mongo;

import org.springframework.data.annotation.Id;

import java.time.Instant;

public class ShortUrl {

    @Id
    private String hashId;
    private String longUri;
    private Instant dateCreated;

    public ShortUrl() {
    }

    public ShortUrl(String hashId, String longUri) {
        this.hashId = hashId;
        this.longUri = longUri;
        this.dateCreated = Instant.now();
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public String getLongUri() {
        return longUri;
    }

    public void setLongUri(String longUri) {
        this.longUri = longUri;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "ShortUrl{" +
                "hashId='" + hashId + '\'' +
                ", longUri=" + longUri +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
