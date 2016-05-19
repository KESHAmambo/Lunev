/**
 * Created by albert on 13.05.16.
 */

public class TaskPart {
    private boolean done;
    private boolean working;
    private double result;
    private int startindex;
    private int endindex;

    public TaskPart(int startindex, int endindex) {
        this.startindex = startindex;
        this.endindex = endindex;
        this.done = false;
        this.working = false;
        this.result = 0;
    }

    public boolean isDone() {
        return this.done;
    }

    public boolean isWorking() {
        return this.working;
    }

    public void setWorking(boolean state) {
        this.working = state;
    }

    public void setDone() {
        this.done = true;
    }

    public int getStartIndex() {
        return this.startindex;
    }

    public int getEndIndex() {
        return this.endindex;
    }

    public double getResult() {
        return this.result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
