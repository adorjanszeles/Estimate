package entity;

import common.State;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int projectid;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private String details;

	@Temporal(TemporalType.DATE)
	private Date enddate;

	private String name;

	@Temporal(TemporalType.DATE)
	private Date startdate;

	@Enumerated(EnumType.STRING)
	private State state;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="PERSONID")
	private Person person;

	//bi-directional many-to-many association to Estimate
	@ManyToMany
	@JoinTable(
		name="PROJECT_ESTIMATE"
		, joinColumns={
			@JoinColumn(name="PROJECTID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ESTIMATEID")
			}
		)
	private List<Estimate> estimates;

	public Project() {
	}

	public int getProjectid() {
		return this.projectid;
	}

	public void setProjectid(int projectid) {
		this.projectid = projectid;
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

	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Estimate> getEstimates() {
		return this.estimates;
	}

	public void setEstimates(List<Estimate> estimates) {
		this.estimates = estimates;
	}

}