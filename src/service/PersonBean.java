package service;

import dal.PersonFacade;
import entity.Person;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;

@Stateless
@ManagedBean
public class PersonBean {
    private String userName;
    private String password;
    private String message;

    @EJB
    PersonFacade personFacade;

    public PersonBean() {
        this.userName = "";
        this.password = "";
        this.message = "";
    }
    
    public void login() {
        Person person = personFacade.getPersonByNickName(userName);
        if(password.equals(person.getPassword())) {
            message = "Bejelentkezés sikeres! :)";

        } else {
            message = "Bejelentkezés sikertelen! :(";
        }
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage(message);
        context.addMessage(null, facesMessage);
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
