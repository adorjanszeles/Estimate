package service;

import common.FacesCommon;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
@Stateless
public class NavigationBean {
    public String goToCreatePerson() { return FacesCommon.redirectToJSFPage("/admin/createPerson"); }
    public String goToListPeople() { return FacesCommon.redirectToJSFPage("/admin/listPeople"); }
    public String goToCreateProject() { return FacesCommon.redirectToJSFPage("/admin/createProject"); }
    public String goToDashboard() { return FacesCommon.redirectToJSFPage("/user/dashboard"); }
}
