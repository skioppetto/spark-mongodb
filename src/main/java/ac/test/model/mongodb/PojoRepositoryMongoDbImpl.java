package ac.test.model.mongodb;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;

import ac.test.model.Pojo;
import ac.test.model.PojoCriteria;
import ac.test.model.PojoRepository;

import com.google.inject.Inject;
import com.mongodb.WriteResult;

class PojoRepositoryMongoDbImpl implements PojoRepository {

	@Inject
	private Datastore dataStore;

	@Override
	public Pojo get(String id) {
		return dataStore.get(Pojo.class, new ObjectId(id));
	}

	@Override
	public List<Pojo> retrieve(PojoCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pojo createOrUpdate(Pojo toPersist) {
		Key<Pojo> key = dataStore.save(toPersist);
		return dataStore.get(Pojo.class, key.getId());
	}

	@Override
	public boolean delete(String ID) {
		WriteResult result = dataStore.delete(Pojo.class, ID);
		if (result.getN() > 0)
			return true;
		return false;
	}

}
