package Server;

import RemoteInterfaces.HelloSayerInterface;

import java.rmi.RemoteException;

public class HelloSayer implements HelloSayerInterface {

    public void sayHello() throws RemoteException {
        System.out.println("Hello");
    }
}
