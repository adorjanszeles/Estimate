package converter;

import dal.PersonFacade;
import entity.Person;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean(name = "personConverter")
public class PersonConverter implements Converter {
    @EJB
    private PersonFacade personFacade;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Object result = personFacade.find(Integer.parseInt(s));
        return result;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return Integer.toString(((Person)o).getPersonid());
    }
}
