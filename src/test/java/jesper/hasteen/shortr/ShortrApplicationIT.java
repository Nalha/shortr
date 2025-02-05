package jesper.hasteen.shortr;

import jesper.hasteen.shortr.controller.LongUrlForm;
import jesper.hasteen.shortr.controller.UrlController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ShortrApplicationIT {

    @Autowired
    private UrlController urlController;

    @Test
    void contextLoads() {
    }

    @Test
    void happy_flow() {
        final ResponseEntity<Object> getShortResponse = urlController.getShortUrl(new LongUrlForm("https://www.google.com"));
        final String shortUrl = getShortResponse
                .getHeaders()
                .get("Location")
                .get(0)
                .split("=")[1];

        final ResponseEntity<Object> redirectResponse = urlController.redirect(shortUrl);
        final String redirectUrl = redirectResponse
                .getHeaders()
                .get("Location")
                .get(0);
        assertEquals("https://www.google.com", redirectUrl);
    }

    @Test
    void non_existing_entry_returns_404() {
        final ResponseEntity<Object> redirectResponse = urlController.redirect("sajhfoiusahfioaf");
        assertEquals(HttpStatus.NOT_FOUND, redirectResponse.getStatusCode());
    }

    @Test
    void create_empty_string_throws_exception() {
        assertThrows(UrlController.UrlControllerException.class, () -> urlController.getShortUrl(new LongUrlForm("")));
    }

    @Test
    void create_null_throws_exception() {
        assertThrows(UrlController.UrlControllerException.class, () -> urlController.getShortUrl(new LongUrlForm(null)));
    }

    @Test
    void create_too_long_string_throws_exception() {
        assertThrows(UrlController.UrlControllerException.class, () -> urlController.getShortUrl(new LongUrlForm(
                "isanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiof" +
                        "h8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8a" +
                        "feisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afe" +
                        "isanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisa" +
                        "nf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0" +
                        "ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijf" +
                        "iofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiof" +
                        "h8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8af" +
                        "eisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisa" +
                        "nf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ij" +
                        "fiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfio" +
                        "fh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8af" +
                        "eisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf" +
                        "0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfio" +
                        "fh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afe" +
                        "isanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0i" +
                        "jfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8a" +
                        "feisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0" +
                        "ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8" +
                        "afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisa" +
                        "nf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfi" +
                        "ofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afe" +
                        "isanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ij" +
                        "fiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8" +
                        "afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afeisanf0ijfiofh8afe")));
    }
}
