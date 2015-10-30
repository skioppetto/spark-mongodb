package ac.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.mongodb.morphia.Datastore;

import ac.test.model.MongoDbProvider;
import ac.test.model.PojoRepository;
import ac.test.model.PojoRepositoryMongoDbImpl;
import ac.test.service.AppService;
import ac.test.service.AppServiceImpl;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.sun.istack.internal.logging.Logger;

public class AppModule extends AbstractModule {

	@Override
	protected void configure() {

		// load configuration
		Properties properties = new Properties();
		try {
			InputStream stream = AppModule.class.getClassLoader()
					.getResourceAsStream("app.properties");
			if (stream == null) {
				throw new RuntimeException("app.properties cannot be found");
			}
			properties.load(stream);
			Names.bindProperties(binder(), properties);
		} catch (IOException e) {
			Logger.getLogger(AppModule.class).severe(
					"I/O Exception during loading configuration");
		}

		// bind services and repos
		bind(AppService.class).to(AppServiceImpl.class);
		bind(PojoRepository.class).to(PojoRepositoryMongoDbImpl.class).in(
				Singleton.class);

		// used by mongodb repository implementation
		bind(Datastore.class).toProvider(MongoDbProvider.class)
				.asEagerSingleton();
	}

}
