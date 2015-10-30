package ac.test;

public interface JsonMapper {

	public String toJson(Object obj);

	public <T> T fromJson(String json, Class<? extends T> clazz);

}
