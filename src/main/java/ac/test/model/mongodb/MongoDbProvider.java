package ac.test.model.mongodb;

import java.io.UnsupportedEncodingException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoDbProvider implements Provider<Datastore> {

	private final Morphia morphia = new Morphia();
	private final String url;
	private final String db;

	@Inject
	MongoDbProvider(@Named("connection.url") String url, @Named("connection.db") String db) throws UnsupportedEncodingException {
		this.url = url;
		this.db = db;
	}

	@Override
	public Datastore get() {
		MongoClientURI uri = new MongoClientURI(url);
		return morphia.createDatastore(new MongoClient(uri), db);
	}

}
