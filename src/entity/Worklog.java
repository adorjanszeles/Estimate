package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQuery(name="Worklog.findAll", query="SELECT w FROM Worklog w")
public class Worklog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int worklogid;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	@Temporal(TemporalType.DATE)
	private Date enddate;

	@Temporal(TemporalType.DATE)
	private Date spendtime;

	@Temporal(TemporalType.DATE)
	private Date startdate;

	//bi-directional many-to-one association to Task
	@ManyToOne
	@JoinColumn(name="TASKID")
	private Task task;

	public Worklog() {
	}

	public int getWorklogid() {
		return this.worklogid;
	}

	public void setWorklogid(int worklogid) {
		this.worklogid = worklogid;
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Date getSpendtime() {
		return this.spendtime;
	}

	public void setSpendtime(Date spendtime) {
		this.spendtime = spendtime;
	}

	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}