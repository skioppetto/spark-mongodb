package ac.test;

import java.lang.reflect.Type;

import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.inject.Provider;

class JsonMapperGSONImpl implements Provider<JsonMapper>, JsonMapper {

	private Gson gson;

	JsonMapperGSONImpl() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(ObjectId.class,
				new JsonSerializer<ObjectId>() {
					@Override
					public JsonElement serialize(ObjectId arg0, Type arg1,
							JsonSerializationContext arg2) {
						return new JsonPrimitive(arg0.toString());
					}
				});
		gson = gsonBuilder.create();
	}

	@Override
	public String toJson(Object obj) {
		return gson.toJson(obj);
	}

	@Override
	public <T> T fromJson(String json, Class<? extends T> clazz) {
		return gson.fromJson(json, clazz);
	}

	@Override
	public JsonMapper get() {
		return this;
	}

}
