package entity;

import common.Difficulty;
import common.TaskState;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int taskid;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private String details;

	@Enumerated(EnumType.STRING)
	private Difficulty difficulty;

	private String name;

	@Enumerated(EnumType.STRING)
	private TaskState state;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="PERSONID")
	private Person person;

	//bi-directional many-to-one association to Worklog
	@OneToMany(mappedBy="task")
	private List<Worklog> worklogs;

	//bi-directional many-to-one association to Estimate
	@OneToMany(mappedBy ="task")
	private List<Estimate> estimates;

	public Task() {
	}

	public int getTaskid() {
		return this.taskid;
	}

	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Difficulty getDifficulty() {
		return this.difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TaskState getState() {
		return this.state;
	}

	public void setState(TaskState state) {
		this.state = state;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Worklog> getWorklogs() {
		return this.worklogs;
	}

	public void setWorklogs(List<Worklog> worklogs) {
		this.worklogs = worklogs;
	}

	public Worklog addWorklog(Worklog worklog) {
		getWorklogs().add(worklog);
		worklog.setTask(this);

		return worklog;
	}

	public Worklog removeWorklog(Worklog worklog) {
		getWorklogs().remove(worklog);
		worklog.setTask(null);

		return worklog;
	}

	public List<Estimate> getEstimates() {
		return this.estimates;
	}

	public void setEstimates(List<Estimate> estimates) {
		this.estimates = estimates;
	}

}