package dal;

import entity.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
