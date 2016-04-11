package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Estimate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int estimateid;

	private int best;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private double estimatedtime;

	private int optimal;

	private double sigma;

	private int worst;

	//bi-directional many-to-many association to Project
	@ManyToMany(mappedBy="estimates")
	private List<Project> projects;

	//bi-directional many-to-many association to Task
	@ManyToMany(mappedBy="estimates")
	private List<Task> tasks;

	public Estimate() {
	}

	public int getEstimateid() {
		return this.estimateid;
	}

	public void setEstimateid(int estimateid) {
		this.estimateid = estimateid;
	}

	public int getBest() {
		return this.best;
	}

	public void setBest(int best) {
		this.best = best;
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public double getEstimatedtime() {
		return this.estimatedtime;
	}

	public void setEstimatedtime(double estimatedtime) {
		this.estimatedtime = estimatedtime;
	}

	public int getOptimal() {
		return this.optimal;
	}

	public void setOptimal(int optimal) {
		this.optimal = optimal;
	}

	public double getSigma() {
		return this.sigma;
	}

	public void setSigma(double sigma) {
		this.sigma = sigma;
	}

	public int getWorst() {
		return this.worst;
	}

	public void setWorst(int worst) {
		this.worst = worst;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

}