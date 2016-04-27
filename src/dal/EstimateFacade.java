package dal;

import entity.Estimate;
import entity.Task;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class EstimateFacade extends AbstractFacade<Estimate> {
    @PersistenceContext
    private EntityManager em;

    public EstimateFacade() {
        super(Estimate.class);
    }

    @Override
    protected EntityManager em() {
        return em;
    }

    public List<Estimate> getEstimateByTask(Task task) {
        String sqlQuery = "SELECT e FROM Estimate e WHERE e.task.taskid = :taskId";
        Query query = em.createQuery(sqlQuery);
        query.setParameter("taskId", task.getTaskid());
        return query.getResultList();
    }
}
