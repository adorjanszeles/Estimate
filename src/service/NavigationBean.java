package service;

import common.FacesCommon;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class NavigationBean {
    public String goToCreatePerson() { return FacesCommon.redirectToJSFPage("/admin/createPerson"); }
    public String goToListPeople() { return FacesCommon.redirectToJSFPage("/admin/listPeople"); }
    public String goToCreateProject() { return FacesCommon.redirectToJSFPage("/admin/createProject"); }
    public String goToDashboard() { return FacesCommon.redirectToJSFPage("/user/dashboard"); }
    public String goToListProjects() { return FacesCommon.redirectToJSFPage("/admin/listProject"); }
    public String goToCreateTask() { return FacesCommon.redirectToJSFPage("/user/createTask"); }
    public String goToListTask() { return FacesCommon.redirectToJSFPage("/user/listTask"); }
}
