package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

	//bi-directional many-to-one association to Task
	@ManyToOne
	@JoinColumn(name="TASKID")
	private Task task;

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

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}