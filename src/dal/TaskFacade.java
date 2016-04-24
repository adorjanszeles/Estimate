package dal;

import common.Difficulty;
import common.TaskState;
import entity.SearchParameters;
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
    private boolean isFirstParam;
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

    public List<Task> findByParameters(SearchParameters params) {
        isFirstParam = true;
        boolean needName = false;
        boolean needState = false;
        boolean needDifficulty = false;
        StringBuilder baseQuery = new StringBuilder("SELECT t FROM Task t");
        if(!"".equals(params.getSearchName()) && params.getSearchName() != null) {
            checkIsFirstParam(baseQuery);
            baseQuery.append(" t.name LIKE :taskName");
            needName = true;
        }
        if(!TaskState.EMPTY.equals(params.getSearchState())) {
            checkIsFirstParam(baseQuery);
            baseQuery.append(" t.state = :taskState");
            needState = true;
        }
        if(!Difficulty.EMPTY.equals(params.getSearchDifficulty())) {
            checkIsFirstParam(baseQuery);
            baseQuery.append(" t.difficulty = :taskDifficulty");
            needDifficulty = true;
        }
        Query query = em.createQuery(baseQuery.toString());
        if(needName) {
            query.setParameter("taskName", "%" + params.getSearchName() + "%");
        }
        if(needState) {
            query.setParameter("taskState", params.getSearchState());
        }
        if(needDifficulty) {
            query.setParameter("taskDifficulty", params.getSearchDifficulty());
        }
        return query.getResultList();
    }

    private void checkIsFirstParam(StringBuilder baseQuery) {
        if(isFirstParam) {
            baseQuery.append(" WHERE");
            isFirstParam = false;
        } else {
            baseQuery.append(" AND");
        }
    }

    public String getLoggedInUserName() {
        return context.getCallerPrincipal().getName();
    }
}
