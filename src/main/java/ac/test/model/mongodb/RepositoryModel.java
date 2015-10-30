package ac.test.model.mongodb;

import org.mongodb.morphia.Datastore;

import ac.test.model.PojoRepository;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class RepositoryModel extends AbstractModule {

	@Override
	protected void configure() {
		
		// used by mongodb repository implementation
		bind(Datastore.class).toProvider(MongoDbProvider.class)
				.asEagerSingleton();
		
		bind(PojoRepository.class).to(PojoRepositoryMongoDbImpl.class).in(
				Singleton.class);
	}

}
