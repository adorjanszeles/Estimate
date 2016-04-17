package service;

import common.FacesCommon;
import common.Role;
import dal.PersonFacade;
import dal.PersonRoleFacade;
import entity.Person;
import entity.PersonRole;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
@Stateless
public class PersonBean {
    private String name;
    private String userName;
    private String password;
    private String email;
    private Role role;

    @EJB
    private PersonFacade personFacade;

    @EJB
    private PersonRoleFacade personRoleFacade;

    public String createPerson() {
        FacesContext context = FacesContext.getCurrentInstance();
        Person person = createPersonEntity();
        PersonRole personRole = createPersonRoleEntity();
        try {
            personFacade.create(person);
            personRoleFacade.create(personRole);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage("A felhasználó regisztrálása sikertelen volt."));
        }
        context.addMessage(null, new FacesMessage("A felhasználó regisztrálása sikeres volt."));
        return FacesCommon.stayOnPage();
    }

    private Person createPersonEntity() {
        Person person = new Person();
        person.setEmail(this.email);
        person.setName(this.name);
        person.setNickname(this.userName);
        person.setPassword(this.password);
        return person;
    }

    private PersonRole createPersonRoleEntity() {
        PersonRole personRole = new PersonRole();
        personRole.setPersonNickName(this.userName);
        personRole.setRole(this.role.getName());
        return personRole;
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
}
