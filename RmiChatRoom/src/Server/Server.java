package Server;

import RemoteInterfaces.ClientSide.ClientIF;
import RemoteInterfaces.ServerSide.ServerIF;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements ServerIF {

    protected Server() throws RemoteException {

    }
    private String availableCommands = "Available commands:\n"
            + "/quit - disconnect from the server\n"
            + "/who - see who is online\n"
            + "/nick - change your nickname\n"
            + "/help - to see list of commands\n";

    @Override
    public void unicast(ClientIF client, String msg){
        try {
            client.displayMsg(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void recieveMessage(ClientIF client, String msg){
        if (msg.charAt(0) == '/') {
            if (msg.startsWith("/quit")) {
                ServerMain.getConnectionList().remove(client);
            } else if (msg.startsWith("/who")) {
                unicast(client, ServerMain.getClientNames());
            } else if (msg.startsWith("/nick")) {
                if (ServerMain.isNameAvailable(msg.substring(6))) {
                    try {
                        broadcast(client,client.getName()+" has changed name to "+msg.substring(6));
                        client.setName(msg.substring(6));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    unicast(client,"Name is already taken");
                }
            } else if (msg.startsWith("/help")) {
                unicast(client,availableCommands);
            } else {
                unicast(client,"Unknown command.");
            }
        } else {
            broadcast(client,msg);
        }
    }

    @Override
    public void broadcast(ClientIF client, String msg) {
        //TODO: se till att andra klienter inte krashar när en klient försvinner
        try {
            for(ClientIF lif : ServerMain.getConnectionList()){
                lif.displayMsg(client.getName()+": "+msg);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    @Override
    public synchronized void clientConnect(ClientIF client, String name) throws RemoteException {
        ServerMain.getConnectionList().add(client);
    }
}
