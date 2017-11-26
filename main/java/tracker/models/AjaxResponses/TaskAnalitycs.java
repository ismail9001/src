package tracker.models.AjaxResponses;

import java.util.ArrayList;

public class TaskAnalitycs {
    private ArrayList<Integer> tasks = new ArrayList<>();
    private int newTasks;
    private int inProgressTasks;
    private int doneTasks;

    public int getNewTasks() {
        return newTasks;
    }

    public void setNewTasks(int newTasks) {
        this.newTasks = newTasks;
    }

    public int getInProgressTasks() {
        return inProgressTasks;
    }

    public void setInProgressTasks(int inProgressTasks) {
        this.inProgressTasks = inProgressTasks;
    }

    public int getDoneTasks() {
        return doneTasks;
    }

    public void setDoneTasks(int doneTasks) {
        this.doneTasks = doneTasks;
    }

    public ArrayList<Integer> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Integer> tasks) {
        this.tasks = tasks;
    }
}
