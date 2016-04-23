package service;

import common.FacesCommon;
import common.Messages;
import common.Role;
import dal.PersonFacade;
import entity.Person;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
@SessionScoped
@Stateless
public class PersonBean {
    private String name;
    private String userName;
    private String password;
    private String email;
    private Role role;
    private FacesContext context;
    private List<Person> peopleList;
    private Person selectedPerson;

    @EJB
    private PersonFacade personFacade;

    public String deletePerson() {
        context = FacesContext.getCurrentInstance();
        try {
            personFacade.remove(selectedPerson);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(Messages.USER_DELETE_FAILED.getMessage()));
            return FacesCommon.stayOnPage();
        }
        // Reload the site to refresh
        return FacesCommon.redirectToJSFPage("/admin/listPeople");
    }

    public void setPersonValueToNull() {
        this.name = null;
        this.userName = null;
        this.email = null;
        this.role = null;
    }

    public String setPersonAndRedirect() {
        this.name = selectedPerson.getName();
        this.email = selectedPerson.getEmail();
        this.userName = selectedPerson.getNickname();
        this.role = selectedPerson.getRole();
        return FacesCommon.redirectToJSFPage("/admin/modifyPerson");
    }

    public void loadPeopleList() {
        peopleList = personFacade.findAll();
    }

    public String createPerson() {
        context = FacesContext.getCurrentInstance();
        Person person = createPersonEntity();
        try {
            personFacade.create(person);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(Messages.USER_CREATE_FAILED.getMessage()));
            return FacesCommon.stayOnPage();
        }
        context.addMessage(null, new FacesMessage(Messages.USER_CREATE_SUCCESS.getMessage()));
        return FacesCommon.stayOnPage();
    }

    public String modifyPerson() {
        context = FacesContext.getCurrentInstance();
        Person person = modifyPersonEntity();
        try {
            personFacade.edit(person);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(Messages.USER_MODIFY_FAILED.getMessage()));
            return FacesCommon.stayOnPage();
        }
        context.addMessage(null, new FacesMessage(Messages.USER_MODIFY_SUCCESS.getMessage()));
        return FacesCommon.stayOnPage();
    }

    private Person createPersonEntity() {
        Person person = new Person();
        person.setEmail(this.email);
        person.setName(this.name);
        person.setNickname(this.userName);
        person.setPassword(this.password);
        person.setRole(this.role);
        return person;
    }

    private Person modifyPersonEntity() {
        selectedPerson.setEmail(this.email);
        selectedPerson.setName(this.name);
        selectedPerson.setNickname(this.userName);
        selectedPerson.setPassword(this.password);
        selectedPerson.setRole(this.role);
        return selectedPerson;
    }

    public Role[] getRoles() {
        return Role.values();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Person> getPeopleList() {
        return peopleList;
    }

    public void setPeopleList(List<Person> peopleList) {
        this.peopleList = peopleList;
    }

    public Person getSelectedPerson() {
        return selectedPerson;
    }

    public void setSelectedPerson(Person selectedPerson) {
        this.selectedPerson = selectedPerson;
    }
}
