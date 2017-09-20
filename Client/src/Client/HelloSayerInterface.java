package Client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloSayerInterface extends Remote{

    public void sayHello() throws RemoteException;

}
