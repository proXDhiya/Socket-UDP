import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


public class client {
    public static void main(String[] argc) throws IOException {
        Scanner sc = new Scanner(System.in);

        // Create a socket to listen at port 5000
        DatagramSocket DSocket = new DatagramSocket();

        InetAddress ip = InetAddress.getLocalHost();
        byte buf[] = null;

        // loop while user not enters "0"
        System.out.println("Client Start!");
        while (true) {
            System.out.print("msg: ");
            String inp = sc.nextLine();

            // convert the String input into the byte array.
            buf = inp.getBytes();

            // Step 2 : Create the datagramPacket for sending the data.
            DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, 5000);

            // Step 3 : invoke the send call to actually send
            // the data.
            DSocket.send(DpSend);

            // if input equal to 0
            if (inp.equals("0")) {
                System.out.println("Done");
                break;
            }
        }

        // receive sum from server
        byte[] receive = new byte[65535];
        DatagramPacket DpReceive = new DatagramPacket(receive, receive.length);
        DSocket.receive(DpReceive);
        System.out.println("Server: " + data(receive));
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
