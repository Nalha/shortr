package jesper.hasteen.shortr.controller;

import jesper.hasteen.shortr.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/api/create")
    public ResponseEntity<Object> getShortUrl(@ModelAttribute LongUrlForm form) {
        validateCreateParams(form);
        String longUrl = form.getLongUrl();
        String hash = urlService.createShortHash(longUrl);
        return buildCreateResponseEntity(hash);
    }

    @GetMapping("/r/{shortUrl}")
    public ResponseEntity<Object> redirect(@PathVariable String shortUrl) {
        return urlService.getLongUri(shortUrl)
                .map(this::buildRedirectResponseEntity)
                .orElse(buildNotFoundResponseEntity());
    }

    private void validateCreateParams(LongUrlForm form) {
        if (form == null) {
            throw new UrlControllerException("Form is missing.");
        }

        final String longUrl = form.getLongUrl();
        if (longUrl == null || longUrl.isBlank()) {
            throw new UrlControllerException("URL is null, empty or blank.");
        }

        if (longUrl.length() > 2048) {
            throw new UrlControllerException("URL cannot exceed 2048 characters.");
        }
    }

    private ResponseEntity<Object> buildCreateResponseEntity(String hashId) {
        return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .header("Location", "/?shortUrl=" + hashId)
                .build();
    }

    private ResponseEntity<Object> buildNotFoundResponseEntity() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .build();
    }

    private ResponseEntity<Object> buildRedirectResponseEntity(String longUri) {
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .header("Location", longUri)
                .build();
    }
    
    public static class UrlControllerException extends RuntimeException {
        public UrlControllerException(String s) {
            super(s);
        }
    }

}
