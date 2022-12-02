// Server can handle multiple clients
// Server can get/send messages from/to clients
// Can also get data from client when client is waiting

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class server {
    private static int sum = 0;

    public static void main(String[] argc) throws IOException {
        // Create a socket to listen at port 5000
        DatagramSocket DSocket = new DatagramSocket(5000);
        byte[] receive = new byte[1024];

        DatagramPacket DpReceive = null;
        while (true) {
            // Create a DatagramPacket to receive the data.
            DpReceive = new DatagramPacket(receive, receive.length);

            // Receive the data in byte buffer.
            DSocket.receive(DpReceive);            

            System.out.println("Client: " + data(receive));
            String temp = data(receive).toString();
            sum += Integer.parseInt(temp);
            
            // Exit the server if the client sends "bye"
            if (data(receive).toString().equals("0")) {
                System.out.println("Client sent 0.....EXITING");
                break;
            }

            // Clear the buffer after every message.
            receive = new byte[1024];
        }

        // send sum to client
        byte[] buf = String.valueOf(sum).getBytes();
        InetAddress ip = InetAddress.getLocalHost();
        DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, 5000);
        DSocket.send(DpSend);

        // test
        System.out.println("Server: " + sum);

        // Close the socket
        DSocket.close();
    }

    // A utility method to convert the byte array
    // data into a string representation.
    public static StringBuilder data(byte[] a) {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0) {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
}
