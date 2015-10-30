package ac.test;

import static spark.Spark.get;
import static spark.Spark.post;

import java.lang.reflect.Type;

import org.bson.types.ObjectId;

import ac.test.model.Pojo;
import ac.test.service.AppService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
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
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(ObjectId.class, new JsonSerializer<ObjectId>() {
			@Override
			public JsonElement serialize(ObjectId arg0, Type arg1,
					JsonSerializationContext arg2) {
				return new JsonPrimitive(arg0.toString());
			}
		});
		Gson gson = gsonBuilder.create();
		
		get("/hello", (req, res) -> {
			return service.hello();
		});

		get("/:id", (req, res) -> {
			Pojo pojo = service.get(req.params("id"));
			return gson.toJson(pojo);
		});

		post("/", (req, res) -> {
			Pojo pojo = gson.fromJson(req.body(), Pojo.class);
			Pojo created = service.create(pojo);
			String json = gson.toJson(created);
			return json;
		});
	}

}
