package gov.hrm.dao.hibernate;

import java.beans.PropertyDescriptor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Repository;

import gov.hrm.dao.BaseDao;
import gov.hrm.utils.StringUtils;

@Repository
public class BaseDaoImpl implements BaseDao {

	@PersistenceContext
	protected EntityManager entityManager;

	protected Logger log = LogManager.getLogger(this.getClass().getName());

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object find(Class cls, Integer id) {
		return entityManager.find(cls, id);
	}

	@Override
	public Object save(Object obj) {
		/*try {
			getValuedProperties(obj);
		} catch (Exception e) {
			// No need to handle
		}*/
		return entityManager.merge(obj);
	}

	@Override
	public void delete(Object obj) {
		entityManager.remove(obj);
	}

/*	*//**
	 * Used for replacing empty values with {@code null} from DB perspective
	 * 
	 * @param obj
	 *            need to be saved
	 * @throws Exception
	 *//*
	private void getValuedProperties(Object obj) throws Exception {

		BeanWrapper src = new BeanWrapperImpl(obj);
		PropertyDescriptor[] propertyDescriptors = src.getPropertyDescriptors();

		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			if (propertyDescriptor.getPropertyType().isInterface()) {
				continue;
			} else if (propertyDescriptor.getPropertyType().getName().startsWith("gov.hrm")) {
				continue;
			}
			Object value = src.getPropertyValue(propertyDescriptor.getName());
			if (StringUtils.isNull(String.valueOf(value))) {
				src.setPropertyValue(propertyDescriptor.getName(), null);
			}
		}
	}*/
}
