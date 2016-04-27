package service;

import common.Difficulty;
import common.FacesCommon;
import common.Messages;
import common.TaskState;
import dal.PersonFacade;
import dal.TaskFacade;
import entity.Person;
import entity.SearchParameters;
import entity.Task;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Date;
import java.util.List;

@ManagedBean
@SessionScoped
public class TaskBean {
    private String name;
    private String details;
    private TaskState taskState;
    private Difficulty difficulty;
    private Person responsible;
    private Date createDate;
    private FacesContext facesContext;
    private List<Task> taskList;
    private Task selectedTask;
    private String userName;
    private String searchName;
    private TaskState searchState;
    private Difficulty searchDifficulty;

    @EJB
    private TaskFacade taskFacade;

    @EJB
    private PersonFacade personFacade;

    public void setTaskList() {
        this.taskList = taskFacade.findAll();
        this.setSearchValueToNull();
    }

    public void findUserTaskAndSetName() {
        this.taskList = taskFacade.findByUser();
        this.userName = taskFacade.getLoggedInUserName();
        this.setSearchValueToNull();
    }

    public String performSearch() {
        SearchParameters params = new SearchParameters();
        params.setSearchName(this.searchName);
        params.setSearchDifficulty(this.searchDifficulty);
        params.setSearchState(this.searchState);
        this.taskList = taskFacade.findByParameters(params);
        return FacesCommon.redirectToJSFPage("/user/resultTaskList");
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

    private void setSearchValueToNull() {
        this.searchName = null;
        this.searchDifficulty = null;
        this.searchState = null;
    }

    public String createTask() {
        facesContext = FacesContext.getCurrentInstance();
        Task task = createTaskEntity();
        try {
            taskFacade.create(task);
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(Messages.TASK_CREATE_FAILED.getMessage()));
            return FacesCommon.stayOnPage();
        }
        facesContext.addMessage(null, new FacesMessage(Messages.TASK_CREATE_SUCCESS.getMessage()));
        return FacesCommon.stayOnPage();
    }

    public String modifyTask() {
        facesContext = FacesContext.getCurrentInstance();
        Task task = modifyTaskEntity();
        try {
            taskFacade.edit(task);
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(Messages.TASK_MODIFY_FAILED.getMessage()));
            return FacesCommon.stayOnPage();
        }
        facesContext.addMessage(null, new FacesMessage(Messages.TASK_MODIFY_SUCCESS.getMessage()));
        return FacesCommon.stayOnPage();
    }

    public String deleteTask() {
        facesContext = FacesContext.getCurrentInstance();
        try {
            taskFacade.remove(selectedTask);
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(Messages.TASK_DELETE_FAILED.getMessage()));
            return FacesCommon.stayOnPage();
        }
        return FacesCommon.redirectToJSFPage("/user/listTask");
    }

    public String viewDetails() {
        return FacesCommon.redirectToJSFPage("/user/viewTask");
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public TaskState getSearchState() {
        return searchState;
    }

    public void setSearchState(TaskState searchState) {
        this.searchState = searchState;
    }

    public Difficulty getSearchDifficulty() {
        return searchDifficulty;
    }

    public void setSearchDifficulty(Difficulty searchDifficulty) {
        this.searchDifficulty = searchDifficulty;
    }
}
