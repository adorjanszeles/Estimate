package service;

import common.FacesCommon;
import dal.PersonFacade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@SessionScoped
@Stateless
public class LoginBean {
    private String userName;
    private String password;

    @EJB
    PersonFacade personFacade;

    public String login () throws ServletException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(this.userName, this.password);
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Bejelentkezés sikertelen."));
            return FacesCommon.stayOnPage();
        }
        return FacesCommon.redirectToJSFPage("/user/dashboard");
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed."));
            return FacesCommon.stayOnPage();
        }
        return FacesCommon.redirectToJSFPage("/index");
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
}
