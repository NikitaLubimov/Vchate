package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Socket socket;
    private final static int PORT = 7275;
    private static String ADDRESS = "localhost";
    private static DataInputStream in;
    private static DataOutputStream out;
    private static Scanner sc;


    public static void main(String[] args) {
        try {
            socket = new Socket(ADDRESS,PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            sc = new Scanner(System.in);

            new Thread(new Runnable() {
                public void run() {
                    while (true){
                        String str = sc.nextLine();
                        try {
                            out.writeUTF(str);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            while (true){
                String str = in.readUTF();
                System.out.println("Server: " + str);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}