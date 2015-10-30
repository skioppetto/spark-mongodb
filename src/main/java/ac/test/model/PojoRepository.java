package ac.test.model;

import java.util.List;


public interface PojoRepository {

	public Pojo get(String id);
	
	public List<Pojo> retrieve (PojoCriteria criteria);
	
	public Pojo createOrUpdate (Pojo toPersist);
	
	public boolean delete (String ID);
	
}
