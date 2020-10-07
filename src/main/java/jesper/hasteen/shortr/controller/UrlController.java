package jesper.hasteen.shortr.controller;

import jesper.hasteen.shortr.service.UrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class UrlController {

    private final Logger log = LoggerFactory.getLogger(UrlController.class);
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/api/create")
    public ResponseEntity<Object> getShortUrl(@ModelAttribute LongUrlForm form) {
        validateCreateParams(form);
        String longUrl = form.getLongUrl();
        String shortHash = urlService.createAndStoreShortHash(longUrl);
        return buildCreateResponseEntity(shortHash);
    }

    @GetMapping("/r/{shortUrl}")
    public ResponseEntity<Object> redirect(@PathVariable String shortUrl) {
        return urlService.getLongUrl(shortUrl)
                .map(this::buildRedirectResponseEntity)
                .orElse(buildNotFoundResponseEntity());
    }

    @ExceptionHandler(Exception.class)
    public RedirectView handleException(Exception e) {
        log.error("Exception caught by ExceptionHandler.", e);
        return new RedirectView("/?error=500");
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
