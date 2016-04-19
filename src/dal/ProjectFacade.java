package dal;

import entity.Project;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProjectFacade extends AbstractFacade<Project> {
    @PersistenceContext
    private EntityManager em;

    public ProjectFacade() {
        super(Project.class);
    }

    @Override
    protected EntityManager em() {
        return em;
    }
}
