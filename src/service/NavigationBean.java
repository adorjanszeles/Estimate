package service;

import common.FacesCommon;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
@Stateless
public class NavigationBean {
    public String goToCreatePerson() {
        return FacesCommon.redirectToJSFPage("/admin/createperson");
    }
}
