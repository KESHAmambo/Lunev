import java.net.*;
import java.io.*;
import java.util.ArrayList;

public final class ServerConnectThread extends Thread {
    protected static int serverscount;
    protected static boolean connected;
    protected static ArrayList<ServerThread> Clients;
    private ServerSocket serversocket;
    private Task task;
    private boolean split;


    public ServerConnectThread(ServerSocket serversocket, Task task, boolean split) {
        this.serversocket = serversocket;
        this.task = task;
        this.split = split;
    }

    public void run() {
        try {
            while ((!ServerConnectThread.connected)) {
                Socket server = null;
                while (server == null) {
                    server = serversocket.accept();
                }
                ServerThread servertthread = new ServerThread(server);
                ServerConnectThread.Clients.add(servertthread);
                ServerConnectThread.serverscount++;
            }

        }
        catch(IOException e) {
            if (e.getMessage().equals("Accept timed out")) {
                System.out.println("Connection timed out");
                ServerConnectThread.connected = true;
                System.out.println("Connected " + ServerConnectThread.serverscount + " servers.");
                if (this.split) {
                    int partscount = ServerConnectThread.serverscount;
                    if (partscount > Task.getMatrixSize()) partscount = Task.getMatrixSize();
                    task.Split(partscount);
                }

                for (ServerThread thread : ServerConnectThread.Clients) {
                    int partnumber = -1;
                    for (int i = 0; i < Task.Parts.size(); i++) {
                        if ((!Task.Parts.get(i).isDone()) && (!Task.Parts.get(i).isWorking())) {
                            partnumber = i;
                            Task.Parts.get(partnumber).setWorking(true);
                            break;
                        }
                    }
                    if (partnumber >= 0) {
                        thread.setTaskNumber(partnumber);
                        thread.setTask(task);
                        thread.start();
                    }
                }
            }
            else
            {
                e.printStackTrace();
            }
        }
    }
}
