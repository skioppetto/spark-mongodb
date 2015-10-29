package ac.test.model;

import org.mongodb.morphia.Datastore;

import com.google.inject.Inject;

public class PojoRepositoryMongoDbImpl implements PojoRepository{

	@Inject
	private Datastore dataStore;
	
	
}
