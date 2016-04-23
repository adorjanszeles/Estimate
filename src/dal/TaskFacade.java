package dal;

import entity.Task;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class TaskFacade extends AbstractFacade<Task> {
    @PersistenceContext
    private EntityManager em;

    @Resource
    private SessionContext context;

    public TaskFacade() {
        super(Task.class);
    }

    @Override
    protected EntityManager em() {
        return em;
    }

    public List<Task> findByUser() {
        String currentUser = context.getCallerPrincipal().getName();
        String sqlQuery = "SELECT t FROM Task t WHERE t.person.name = :userName";
        Query query = em.createQuery(sqlQuery);
        query.setParameter("userName", currentUser);
        return query.getResultList();
    }
}
