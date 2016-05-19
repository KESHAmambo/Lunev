/**
 * Created by albert on 13.05.16.
 */
import java.io.Serializable;

import Jama.*;



public class Message implements Serializable{

 //   private static final long serialVersionUID = 0x1c1ec6832L;
    private Matrix matrix;
    private int startminorindex;
    private int endminorindex;
    private int size;
    private double result;
    private String text;
    private String type;

    public Message() {
        this.matrix = null;
        this.startminorindex = 0;
        this.endminorindex = 0;
        this.size = 0;
        this.result = 0;
        this.text = "-";
        this.type = "null";
    }

    public Message(Matrix matrix, int startminorindex, int endminorindex, int size) {
        this();
        this.matrix = matrix;
        this.startminorindex = startminorindex;
        this.endminorindex = endminorindex;
        this.size = size;
        this.type = "Matrix";
    }

    public Message(double result) {
        this();
        this.result = result;
        this.type = "Double";
    }

    public Message(String text) {
        this();
        this.text = text;
        this.type = "String";
    }

    public String getText() {
        return this.text;
    }

    public double getResult() {
        return this.result;
    }

    public Matrix getMatrix() {
        return this.matrix;
    }

    public String getType() {
        return this.type;
    }

    public int getStartMinorIndex() {
        return this.startminorindex;
    }

    public int getEndMinorIndex() {
        return this.endminorindex;
    }

    public int getMatrixSize() {
        return this.size;
    }

}