package webservice;

import entity.Person;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface IPersonWs {
    @WebMethod
    List<Person> getAllPersonWebService();
}
