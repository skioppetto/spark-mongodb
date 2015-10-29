package ac.test;

import static spark.Spark.get;
import ac.test.service.AppService;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		
		Injector injector = Guice.createInjector(new AppModule());
		AppService service = injector.getInstance(AppService.class);
		get("/hello", (req, res) -> {
			return service.hello();
		});
	}

}
