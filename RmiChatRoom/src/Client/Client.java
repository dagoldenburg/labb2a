package Client;

import RemoteInterfaces.ClientSide.ClientIF;
import RemoteInterfaces.ServerSide.ServerIF;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Client extends UnicastRemoteObject implements ClientIF {

    private String name;

    protected Client(ServerIF server) throws RemoteException {
        InetAddress address = null;
        try {
            NetworkInterface nwi = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
            byte mac[] = nwi.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            name = sb.toString();
            server.clientConnect(this);
        } catch (UnknownHostException|SocketException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        while(true){
            String input = scanner.nextLine();
            server.recieveMessage(this,input);
        }
    }

    @Override
    public void displayMsg(String msg) throws RemoteException {
        System.out.println(msg);
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public void setName(String newName) throws RemoteException {
        name = newName;
    }
}
