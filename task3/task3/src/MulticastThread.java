/**
 * Created by albert on 13.05.16.
 */

import java.io.IOException;
import java.net.*;


public class MulticastThread extends Thread{
    private String groupIP;
    private int port;
    protected static boolean serverFound;

    public MulticastThread(String groupID, int port)
    {
        this.groupIP = groupID;
        this.port = port;
        this.serverFound = false;
    }

    public void run()
    {
        try {
            String status = "SRVENABLED";
            InetAddress group = InetAddress.getByName(this.groupIP);
            DatagramPacket packet;
            DatagramSocket udpsocket = new DatagramSocket();
            packet = new DatagramPacket(status.getBytes(), status.getBytes().length, group, this.port);
            udpsocket.setBroadcast(true);
            udpsocket.send(packet);

            udpsocket.close();
        }
        catch(SocketException e) {
            e.printStackTrace();
       //     System.err.println(e);
        }
        catch(IOException e)
        {
            e.printStackTrace();
       //     System.err.println(e);
        }
    }

}
