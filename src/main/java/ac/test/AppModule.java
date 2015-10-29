package ac.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
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

public class AppModule extends AbstractModule {

	@Override
	protected void configure() {

		// load configuration
		Properties properties = new Properties();
		try {
//			InputStream prop = this.getClass().getResourceAsStream(
//					"app.properties");
			properties.load(new FileReader("app.properties"));
			Names.bindProperties(binder(), properties);
		} catch (FileNotFoundException e) {
			System.out
					.println("The configuration file Test.properties can not be found");
		} catch (IOException e) {
			System.out.println("I/O Exception during loading configuration");
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
