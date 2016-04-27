package service;

import common.FacesCommon;
import common.Messages;
import dal.WorklogFacade;
import entity.Task;
import entity.Worklog;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Date;
import java.util.List;

@ManagedBean
@SessionScoped
public class WorklogBean {
    private Task task;
    private FacesContext facesContext;
    private Worklog selectedWorklog;
    private List<Worklog> worklogList;
    private Worklog emptyWorklog;

    @EJB
    private WorklogFacade worklogFacade;

    public void setWorklogList() {
        this.worklogList = worklogFacade.getWorklogByTask(task);
        this.emptyWorklog = new Worklog();
        this.emptyWorklog.setCreatedate(new Date());
        this.emptyWorklog.setTask(task);
    }

    public String createOrModifyWorklog() {
        facesContext = FacesContext.getCurrentInstance();
        try {
            worklogFacade.edit(this.selectedWorklog);
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(Messages.WORKLOG_SAVE_FAILED.getMessage()));
            return FacesCommon.stayOnPage();
        }
        facesContext.addMessage(null, new FacesMessage(Messages.WORKLOG_SAVE_SUCCESS.getMessage()));
        return FacesCommon.stayOnPage();
    }

    public String deleteWorklog() {
        facesContext = FacesContext.getCurrentInstance();
        try {
            worklogFacade.remove(this.selectedWorklog);
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(Messages.WORKLOG_DELETE_FAILED.getMessage()));
            return FacesCommon.stayOnPage();
        }
        return FacesCommon.redirectToJSFPage("/user/viewTask");
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Worklog getSelectedWorklog() {
        return selectedWorklog;
    }

    public void setSelectedWorklog(Worklog selectedWorklog) {
        this.selectedWorklog = selectedWorklog;
    }

    public List<Worklog> getWorklogList() {
        return worklogList;
    }

    public void setWorklogList(List<Worklog> worklogList) {
        this.worklogList = worklogList;
    }

    public Worklog getEmptyWorklog() {
        return emptyWorklog;
    }

    public void setEmptyWorklog(Worklog emptyWorklog) {
        this.emptyWorklog = emptyWorklog;
    }
}
