package com.company;
import Auto.*;
import Exceptions.*;
import Interface.*;
import Threads.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    serverSocket = new ServerSocket(1050);
        try {
            System.out.println("Соединение установлено!");
            clientSocket = serverSocket.accept();
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            Transport[] transports = (Transport[]) objectInputStream.readObject();
            objectOutputStream.writeDouble(Vehicle.getMeanPrice(transports));
            objectOutputStream.flush();
            System.out.println("Средняя цена отправлена!");
            clientSocket.close();
            
            System.out.println("Сервер запущен!");
            clientSocket = serverSocket.accept();
            Runnable r = new Server(clientSocket);
            Thread t = new Thread(r);
            t.start();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}