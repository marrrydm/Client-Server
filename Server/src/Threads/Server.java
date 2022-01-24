package Threads;

import Auto.Vehicle;
import Interface.Transport;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Server implements Runnable{
    private Socket clientSocket;
    public Server(Socket s){
        this.clientSocket = s;
    }
    @Override
    public void run() {
        System.out.println("Соединение установлено!");
        try {
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            Transport[] transports = (Transport[]) in.readObject();
            out.writeDouble(Vehicle.getMeanPrice(transports));
            out.flush();
            System.out.println("Средняя цена отправлена!");
            clientSocket.close();
        } catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
