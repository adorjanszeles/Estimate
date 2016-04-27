package dal;

import entity.Task;
import entity.Worklog;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class WorklogFacade extends AbstractFacade<Worklog> {
    @PersistenceContext
    private EntityManager em;

    public WorklogFacade() {
        super(Worklog.class);
    }

    @Override
    protected EntityManager em() {
        return em;
    }

    public List<Worklog> getWorklogByTask(Task task) {
        String sqlQuery = "SELECT w FROM Worklog w WHERE w.task.taskid = :taskId";
        Query query = em.createQuery(sqlQuery);
        query.setParameter("taskId", task.getTaskid());
        return query.getResultList();
    }
}
