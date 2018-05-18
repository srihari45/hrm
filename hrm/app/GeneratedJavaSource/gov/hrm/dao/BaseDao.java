package gov.hrm.dao;

public interface BaseDao {

	@SuppressWarnings("rawtypes")
	public Object find(Class cls, Integer id);

	public Object save(Object obj);

	public void delete(Object obj);

}