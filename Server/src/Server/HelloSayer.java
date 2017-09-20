package Server;

import java.rmi.RemoteException;

public class HelloSayer implements HelloSayerInterface{

    @Override
    public void sayHello() throws RemoteException {
        System.out.println("Hello");
    }
}
