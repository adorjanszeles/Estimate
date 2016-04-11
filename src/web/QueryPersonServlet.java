package web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Person;
import service.PersonBean;

@WebServlet("/QueryPersonServlet")
public class QueryPersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private PersonBean personBean;

	protected void processServerRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Person> listPerson = personBean.queryAllPerson();
    	String result = "The users are: \n";
    	for (Person person : listPerson) {
			result += person.getName() + "\n";
		}
    	
    	request.setAttribute("resultOfPersonQuery", result);
    	request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processServerRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processServerRequest(request, response);
	}

}
