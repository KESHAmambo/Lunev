
import Jama.*;


public class Calculator extends Thread{
    private Matrix matrix;
    private int size;
    private int startminor;
    private int endminor;
    private int number;
    private double result;

    public Calculator(Matrix matrix, int size,int startminor, int endminor, int number) {
        this.matrix = matrix;
        this.startminor = startminor;
        this.endminor = endminor;
        this.size = size;
        this.number = number;
    }

    public void run() {
        for (int i = startminor; i <= endminor; i++)
        {
            int arr[] = new int[size - 1];
            for (int k = 1; k < size; k++)
                arr[k-1] = k;
            if (i > 0) arr[i - 1] = 0;

            Matrix minor = this.matrix.getMatrix(0, size - 2, arr);
            System.out.println("Thread (" + number + ") caclulating [" + i + "] minor.");

            if (i % 2 == 0)
                this.result += minor.det();
            else
                this.result -= minor.det();
        }
    }

    public double getResult() {
        return result;
    }
}
