package web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Person;
import service.PersonBean;

@WebServlet("/CreatePersonServlet")
public class CreatePersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private PersonBean personBean;
	
	protected void processServerRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String nickName = request.getParameter("nickName");
		String password = request.getParameter("password");
		
		Person person = new Person();
    	person.setName(name);
    	person.setEmail(email);
    	person.setNickname(nickName);
    	person.setPassword(password);
    	
    	personBean.createAndPersistPerson(person);
    	
    	String result = "Person successfully created with id " + person.getPersonid();
    	request.setAttribute("resultOfPersonCreation", result);
    	request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processServerRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processServerRequest(request, response);
	}
}
