package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int personid;

	private String email;

	private String name;

	private String nickname;

	private String password;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="person")
	private List<Project> projects;

	//bi-directional many-to-one association to Task
	@OneToMany(mappedBy="person")
	private List<Task> tasks;

	public Person() {
	}

	public int getPersonid() {
		return this.personid;
	}

	public void setPersonid(int personid) {
		this.personid = personid;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setPerson(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setPerson(null);

		return project;
	}

	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Task addTask(Task task) {
		getTasks().add(task);
		task.setPerson(this);

		return task;
	}

	public Task removeTask(Task task) {
		getTasks().remove(task);
		task.setPerson(null);

		return task;
	}

}