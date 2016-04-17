package dal;

import entity.PersonRole;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PersonRoleFacade extends AbstractFacade<PersonRole> {

    @PersistenceContext
    private EntityManager em;

    public PersonRoleFacade() {
        super(PersonRole.class);
    }

    @Override
    protected EntityManager em() {
        return em;
    }
}
