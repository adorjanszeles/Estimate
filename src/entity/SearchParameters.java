package entity;

import common.Difficulty;
import common.TaskState;

public class SearchParameters {
    private String searchName;
    private TaskState searchState;
    private Difficulty searchDifficulty;

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public TaskState getSearchState() {
        return searchState;
    }

    public void setSearchState(TaskState searchState) {
        this.searchState = searchState;
    }

    public Difficulty getSearchDifficulty() {
        return searchDifficulty;
    }

    public void setSearchDifficulty(Difficulty searchDifficulty) {
        this.searchDifficulty = searchDifficulty;
    }
}
