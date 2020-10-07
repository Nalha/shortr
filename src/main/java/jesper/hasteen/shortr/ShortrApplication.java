package jesper.hasteen.shortr;

import jesper.hasteen.shortr.service.UrlService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
@EnableScheduling
public class ShortrApplication {

	final UrlService urlService;

	public ShortrApplication(UrlService urlService) {
		this.urlService = urlService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ShortrApplication.class, args);
	}

	// TODO: Replace with cluster central scheduling if running multiple instances.
	@Scheduled(fixedDelay = 60 * 60 * 1000)
	public void deleteSchedule() {
		urlService.deleteEntriesOlderThan(Instant.now().minus(30, ChronoUnit.DAYS));
	}

}
