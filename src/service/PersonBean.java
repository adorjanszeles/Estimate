package service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import dal.PersonFacade;
import entity.Person;

@Stateless
@LocalBean
public class PersonBean {

    @EJB
    PersonFacade personFacade;
    
    public void createAndPersistPerson(Person person) {
    	personFacade.add(person);
    }
    
    public List<Person> queryAllPerson() {
    	return personFacade.getAllPerson();
    }
}
