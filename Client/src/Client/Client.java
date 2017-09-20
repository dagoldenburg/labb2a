package Client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String[] args) {
        try {
            // Getting the registry
            Registry registry = LocateRegistry.getRegistry();
            // Looking up the registry for the remote object
            HelloSayerInterface stub = (HelloSayerInterface) registry.lookup("Hello");
            // Calling the remote method using the obtained object
            stub.sayHello();
            // System.out.println("Remote method invoked");
         } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

