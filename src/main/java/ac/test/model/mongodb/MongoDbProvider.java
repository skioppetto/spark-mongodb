package ac.test.model.mongodb;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoDbProvider implements Provider<Datastore> {

	private Morphia morphia = new Morphia();
	private String url;

	@Inject
	public MongoDbProvider(@Named("connection.url") String url) throws UnsupportedEncodingException {
		this.url = url;
	}

	@Override
	public Datastore get() {
		MongoClientURI uri = new MongoClientURI(url);
		return morphia.createDatastore(new MongoClient(uri), "testdb");
	}

}
