package service;

import common.FacesCommon;
import common.Messages;
import common.State;
import dal.PersonFacade;
import dal.ProjectFacade;
import entity.Person;
import entity.Project;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Date;
import java.util.List;

@ManagedBean
@SessionScoped
@Stateless
public class ProjectBean {
    private String name;
    private String details;
    private State state;
    private Date start;
    private Date end;
    private Date createDate;
    private Person responsible;
    private FacesContext context;

    @EJB
    private PersonFacade personFacade;

    @EJB
    private ProjectFacade projectFacade;

    public void setCreateDateToToday() {
        this.createDate = new Date();
    }

    public String createProject() {
        context = FacesContext.getCurrentInstance();
        Project project = createProjectEntity();
        try {
            projectFacade.create(project);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(Messages.PROJECT_CREATE_FAILED.getMessage()));
        }
        context.addMessage(null, new FacesMessage(Messages.PROJECT_CREATE_SUCCESS.getMessage()));
        return FacesCommon.stayOnPage();
    }

    private Project createProjectEntity() {
        Project result = new Project();
        result.setName(this.name);
        result.setDetails(this.details);
        result.setState(this.state.getName());
        result.setCreatedate(this.createDate);
        result.setStartdate(this.start);
        result.setEnddate(this.end);
        result.setPerson(this.responsible);
        return result;
    }

    public List<Person> getOperators() {
        return personFacade.findAll();
    }

    public State[] getStates() {
        return State.values();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Person getResponsible() {
        return responsible;
    }

    public void setResponsible(Person responsible) {
        this.responsible = responsible;
    }
}
