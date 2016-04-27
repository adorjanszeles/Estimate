package service;

import common.FacesCommon;
import common.Messages;
import dal.EstimateFacade;
import entity.Estimate;
import entity.Task;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Date;
import java.util.List;

@ManagedBean
@SessionScoped
public class EstimateBean {
    private Task task;
    private Estimate selectedEstimate;
    private Estimate emptyEstimate;
    private List<Estimate> estimateList;
    private FacesContext facesContext;

    @EJB
    private EstimateFacade estimateFacade;

    public void setEstimateList() {
        this.emptyEstimate = new Estimate();
        this.emptyEstimate.setCreatedate(new Date());
        this.emptyEstimate.setTask(task);
        this.estimateList = estimateFacade.getEstimateByTask(task);
    }

    public String createOrModifyEstimate() {
        facesContext = FacesContext.getCurrentInstance();
        this.calculateEstimate();
        try {
            estimateFacade.edit(selectedEstimate);
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(Messages.WORKLOG_SAVE_FAILED.getMessage()));
            return FacesCommon.stayOnPage();
        }
        facesContext.addMessage(null, new FacesMessage(Messages.WORKLOG_SAVE_SUCCESS.getMessage()));
        return FacesCommon.stayOnPage();
    }

    private void calculateEstimate() {
        this.calculateSigma();
        this.calculateEstimatedTime();
    }

    private void calculateSigma() {
        int worst = this.selectedEstimate.getWorst();
        int best = this.selectedEstimate.getBest();
        double sigma = Math.pow(((worst - best) / 6.0D), 2.0D);
        sigma = roundTwoDigits(sigma);
        this.selectedEstimate.setSigma(sigma);
    }

    private void calculateEstimatedTime() {
        int worst = this.selectedEstimate.getWorst();
        int best = this.selectedEstimate.getBest();
        int optimal = this.selectedEstimate.getOptimal();
        double estimatedTime = (best + (4.0D * optimal) + worst) / 6.0D;
        estimatedTime = roundTwoDigits(estimatedTime);
        this.selectedEstimate.setEstimatedtime(estimatedTime);
    }

    private double roundTwoDigits(double d) {
        return Math.floor(d * 1e2) / 1e2;
    }

    public String deleteEstimate() {
        facesContext = FacesContext.getCurrentInstance();
        try {
            estimateFacade.remove(selectedEstimate);
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(Messages.WORKLOG_DELETE_FAILED.getMessage()));
            return FacesCommon.stayOnPage();
        }
        return FacesCommon.redirectToJSFPage("/user/viewTask");
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Estimate getSelectedEstimate() {
        return selectedEstimate;
    }

    public void setSelectedEstimate(Estimate selectedEstimate) {
        this.selectedEstimate = selectedEstimate;
    }

    public Estimate getEmptyEstimate() {
        return emptyEstimate;
    }

    public void setEmptyEstimate(Estimate emptyEstimate) {
        this.emptyEstimate = emptyEstimate;
    }

    public List<Estimate> getEstimateList() {
        return estimateList;
    }

    public void setEstimateList(List<Estimate> estimateList) {
        this.estimateList = estimateList;
    }
}
