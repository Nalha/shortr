package jesper.hasteen.shortr.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class HashGenerator {

    private final static String HASH_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final static byte HASH_LENGTH = 7;

    /**
     * Creates a hashed string with an input string as seed.
     *
     * @param seed any string used as seed for the hash.
     * @return an alphanumerical hash that is {@link #HASH_LENGTH} long.
     */
    public String generate(String seed) {
        return generateRandomString(seed.hashCode());
    }

    private String generateRandomString(int seed) {
        final StringBuilder sb = new StringBuilder();
        final Random rand = new Random(seed);
        for (int i = 0; i < HASH_LENGTH; i++) {
            final int nextChar = rand.nextInt(HASH_CHARS.length());
            sb.append(HASH_CHARS.charAt(nextChar));
        }
        return sb.toString();
    }

}
