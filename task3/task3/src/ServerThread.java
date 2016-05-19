/**
 * Created by albert on 19.05.16.
 */

import java.net.*;
import  java.io.*;

public class ServerThread extends Thread{
    private Socket tcpsocket;
    private int number;
    private Task task;

    public ServerThread(Socket socket)
    {
        this.tcpsocket = socket;
    }

    public void setTaskNumber(int num)
    {
        this.number = num;
    }

    public void setTask(Task task)
    {
        this.task = task;
    }

    public Task getTask()
    {
        return this.task;
    }
    public synchronized void run()
    {
        try{
            System.out.println("Server thread for task {" + number + "} started.");

            ObjectOutputStream outputStream = new ObjectOutputStream(tcpsocket.getOutputStream());
            ObjectInputStream inputStream   = new ObjectInputStream(tcpsocket.getInputStream());

            Message msg = new Message(Task.getMatrix(), Task.Parts.get(number).getStartIndex(), Task.Parts.get(number).getEndIndex(), Task.getMatrixSize());
            outputStream.writeObject(msg);
            msg = (Message) inputStream.readObject();
            Task.Parts.get(number).setResult(msg.getResult());
            Task.Parts.get(number).setDone();

            System.out.println("Server calculated task (" + number + "). Result = : "+msg.getResult());
            if ( Task.Parts.get(number).isDone() )
                System.out.println("Task {" + number + "} is done.");
            else
                System.out.println("Task {" + number + "} isn`t done.");
        }
        catch(SocketException e)
        {
            Task.Parts.get(number).setWorking(false);
            System.err.println("Server doing task {" + number + "} disconnected!");
        }
        // Don't forget to analise IOException!
        catch(IOException e)
        {
            e.printStackTrace();
            System.err.println(e);
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println(e);
        }
    }

}
