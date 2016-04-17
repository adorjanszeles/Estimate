package dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public abstract class AbstractFacade<T> {
	private Class<T> entityClass;

	public AbstractFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract EntityManager em();

	public void create(T entity) {
		em().persist(entity);
	}

	public void edit(T entity) {
		em().merge(entity);
	}

	public void remove(T entity) {
		em().remove(em().merge(entity));
	}

	public T find(Object id) {
		return em().find(entityClass, id);
	}

	public List<T> findAll() {
		CriteriaQuery<T> cq = em().getCriteriaBuilder().createQuery(entityClass);
		cq.select(cq.from(entityClass));
		return em().createQuery(cq).getResultList();
	}
}