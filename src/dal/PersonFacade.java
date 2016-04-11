package dal;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Person;

@Stateless
public class PersonFacade extends AbstractFacade<Person> {
	
	@PersistenceContext
	private EntityManager em;

	public PersonFacade() {
		super(Person.class);
	}

	@Override
	protected EntityManager em() {
		return em;
	}
	
	@SuppressWarnings("unchecked")
	public List<Person> getAllPerson() {
		String sql = "SELECT p FROM Person p";
		Query query = em.createQuery(sql);
		return (List<Person>) query.getResultList();
	}

}
