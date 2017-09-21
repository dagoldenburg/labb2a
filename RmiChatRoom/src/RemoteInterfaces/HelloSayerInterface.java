package RemoteInterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloSayerInterface extends Remote {
    void sayHello() throws RemoteException;
}
