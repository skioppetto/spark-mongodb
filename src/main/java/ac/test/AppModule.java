package ac.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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

		// bind JSON mapper
		bind(JsonMapper.class).to(JsonMapperGSONImpl.class).in(Singleton.class);

		// bind services and repos
		bind(AppService.class).to(AppServiceImpl.class);

	}

}
