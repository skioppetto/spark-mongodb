package ac.test.service;

import ac.test.model.Pojo;

public interface AppService {

	public Pojo get(String id);

	public String delete(String id);

	public Pojo update(Pojo pojo);

	public Pojo create(Pojo pojo);
	
}
