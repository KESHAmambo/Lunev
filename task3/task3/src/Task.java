/**
 * Created by albert on 14.05.16.
 */

import Jama.*;
import java.util.ArrayList;


public class Task {
    private static int size;
    private static Matrix matrix;
    public static ArrayList<TaskPart> Parts;
    private static double result;

    public Task(Matrix matrix, int size) {
        Task.matrix = matrix;
        Task.size = size;
    }

    public void Split(int partscount) {
        int partsize = (int) Math.ceil((double)(size) / (double)(partscount));
        Task.Parts = new ArrayList<TaskPart>();
        int start = 0;
        int end = size - 1;
        for (int i = 0; i < partscount; i++) {
            start = partsize*i;
            end = partsize * (i + 1) - 1;
            if ((end >= size)||(i == partscount - 1))
                end = size - 1;
            System.out.println("Task {" + i + "} = minors [" + start + " - " + end + "]");
            Parts.add(new TaskPart(start, end));
        }
    }

    public static void initResult() {
        Task.result = 0;
    }

    public static double getResult() {
        return Task.result;
    }

    public static Matrix getMatrix() {
        return Task.matrix;
    }

    public static int getMatrixSize() {
        return Task.size;
    }

    public void calcResult() {
        for (int i = 0; i < Parts.size(); i++)
            Task.result = Task.result + Task.Parts.get(i).getResult();
    }

}
