package service;

import common.Difficulty;
import common.FacesCommon;
import common.Messages;
import common.TaskState;
import dal.PersonFacade;
import dal.TaskFacade;
import entity.Person;
import entity.Task;

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
public class TaskBean {
    private String name;
    private String details;
    private TaskState taskState;
    private Difficulty difficulty;
    private Person responsible;
    private Date createDate;
    private FacesContext context;
    private List<Task> taskList;
    private Task selectedTask;
    private boolean loggedInUser;

    @EJB
    private TaskFacade taskFacade;

    @EJB
    private PersonFacade personFacade;

    public void setTaskList() {
        if(loggedInUser) {
            this.taskList = taskFacade.findByUser();
        } else {
            this.taskList = taskFacade.findAll();
        }
    }

    public String performSearch() {
        if(loggedInUser) {
            this.taskList = taskFacade.findByUser();
        } else {
            this.taskList = taskFacade.findAll();
        }
        return FacesCommon.redirectToJSFPage("/user/listTask");
    }

    public String setTaskAndRedirect() {
        this.name = selectedTask.getName();
        this.details = selectedTask.getDetails();
        this.taskState = selectedTask.getState();
        this.difficulty = selectedTask.getDifficulty();
        this.responsible = selectedTask.getPerson();
        this.createDate = selectedTask.getCreatedate();
        return FacesCommon.redirectToJSFPage("/user/modifyTask");
    }

    public void setTaskValueToNull() {
        this.createDate = new Date();
        this.name = null;
        this.details = null;
        this.taskState = null;
        this.difficulty = null;
        this.responsible = null;
    }

    public String createTask() {
        context = FacesContext.getCurrentInstance();
        Task task = createTaskEntity();
        try {
            taskFacade.create(task);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(Messages.TASK_CREATE_FAILED.getMessage()));
            return FacesCommon.stayOnPage();
        }
        context.addMessage(null, new FacesMessage(Messages.TASK_CREATE_SUCCESS.getMessage()));
        return FacesCommon.stayOnPage();
    }

    public String modifyTask() {
        context = FacesContext.getCurrentInstance();
        Task task = modifyTaskEntity();
        try {
            taskFacade.edit(task);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(Messages.TASK_MODIFY_FAILED.getMessage()));
            return FacesCommon.stayOnPage();
        }
        context.addMessage(null, new FacesMessage(Messages.TASK_MODIFY_SUCCESS.getMessage()));
        return FacesCommon.stayOnPage();
    }

    public String deleteTask() {
        context = FacesContext.getCurrentInstance();
        try {
            taskFacade.remove(selectedTask);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(Messages.TASK_DELETE_FAILED.getMessage()));
            return FacesCommon.stayOnPage();
        }
        return FacesCommon.redirectToJSFPage("/user/listTask");
    }

    public String viewDetails() {
        // TODO implement view method, here will be the chance
        // TODO to associate estimate and task, and work log and task
        return "";
    }

    private Task createTaskEntity() {
        Task result = new Task();
        result.setName(this.name);
        result.setDetails(this.details);
        result.setCreatedate(this.createDate);
        result.setDifficulty(this.difficulty);
        result.setState(this.taskState);
        result.setPerson(this.responsible);
        return result;
    }

    private Task modifyTaskEntity() {
        selectedTask.setName(this.name);
        selectedTask.setDetails(this.details);
        selectedTask.setState(this.taskState);
        selectedTask.setDifficulty(this.difficulty);
        selectedTask.setPerson(this.responsible);
        return selectedTask;
    }

    public List<Person> getOperators() {
        return personFacade.findAll();
    }

    public TaskState[] getStates() {
        return TaskState.values();
    }

    public Difficulty[] getDifficulties() {
        return Difficulty.values();
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

    public TaskState getTaskState() {
        return taskState;
    }

    public void setTaskState(TaskState taskState) {
        this.taskState = taskState;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Person getResponsible() {
        return responsible;
    }

    public void setResponsible(Person responsible) {
        this.responsible = responsible;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public Task getSelectedTask() {
        return selectedTask;
    }

    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }

    public boolean isLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(boolean loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
