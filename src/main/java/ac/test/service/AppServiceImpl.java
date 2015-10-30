package ac.test.service;

import ac.test.model.Pojo;
import ac.test.model.PojoRepository;

import com.google.inject.Inject;

public class AppServiceImpl implements AppService{

	@Inject
	private PojoRepository repository;
	
	
	@Override
	public String hello(){return "Hello World";}

	@Override
	public Pojo get(String id) {
		return repository.get(id);
	}
	
	@Override
	public Pojo create(Pojo pojo) {
		return repository.createOrUpdate(pojo);
	}
	
	@Override
	public Pojo update(Pojo pojo) {
		return repository.createOrUpdate(pojo);
	}
	
	@Override
	public String delete(String id) {
		return (repository.delete(id))?"{result:\"success\"}":"{result:\"failure\"}";
	}
	
	
	
}
