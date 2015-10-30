package ac.test;

import static spark.Spark.get;
import static spark.Spark.post;
import ac.test.model.Pojo;
import ac.test.model.mongodb.RepositoryModel;
import ac.test.service.AppService;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(
				new RepositoryModel(),
				new AppModule());
		AppService service = injector.getInstance(AppService.class);
		JsonMapper mapper = injector.getInstance(JsonMapper.class);

		get("/:id", (req, res) -> {
			Pojo pojo = service.get(req.params("id"));
			return mapper.toJson(pojo);
		});

		post("/", (req, res) -> {
			Pojo pojo = mapper.fromJson(req.body(), Pojo.class);
			Pojo created = service.create(pojo);
			String json = mapper.toJson(created);
			return json;
		});
	}

}
