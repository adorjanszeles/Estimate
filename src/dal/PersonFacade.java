package dal;

import entity.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

	public Person getPersonByNickName(String paramNickName) {
		Person result = null;
		String sqlQuery = "SELECT p FROM Person p WHERE p.name = :nickName";
		Query query = em.createQuery(sqlQuery);
		query.setParameter("nickName", paramNickName);
		try {
			result = (Person) query.getSingleResult();
		} catch(ClassCastException exception) {
			// TODO ki kell találni a logolást...
		}
		return result;
	}

}
