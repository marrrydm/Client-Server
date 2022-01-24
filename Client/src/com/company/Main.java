package com.company;
import Auto.*;
import Exceptions.*;
import Interface.*;
import Threads.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.io.DataInputStream;

public class Main {
    public static void main(String[] args) throws DuplicateModelNameException{
            String address = "localhost";
            Socket socket = null;
            ObjectOutputStream out;
            ObjectInputStream in;
            Transport car1 = new Car("BMW",3);
            Transport car2 = new Car("AUDI",3);
            Transport car3 = new Car("LEXUS",3);
            Transport[] array = new Transport[]{car1,car2,car3};
            try {
                socket = new Socket(address,1050);
                System.out.println("Соединение установлено!");
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
                out.writeObject(array);
                out.flush();
                System.out.println("Средняя цена:" + in.readDouble());
                out.close();
                in.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
        }
    }
}