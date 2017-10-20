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
        try {
            if (msg.charAt(0) == '/') {
                if (msg.startsWith("/quit")) {
                    broadcast(client," has disconnected");
                    ServerMain.getConnectionList().remove(client);
                } else if (msg.startsWith("/who")) {
                    unicast(client, ServerMain.getClientNames());
                } else if (msg.startsWith("/nick")) {
                    if (ServerMain.isNameAvailable(msg.substring(6))) {
                        try {
                            broadcast(client, client.getName() + " has changed name to " + msg.substring(6));
                            client.setName(msg.substring(6));
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    } else {
                        unicast(client, "Name is already taken");
                    }
                } else if (msg.startsWith("/help")) {
                    unicast(client, availableCommands);
                } else {
                    unicast(client, "Unknown command.");
                }
            } else {
                broadcast(client, msg);
            }
        }catch(StringIndexOutOfBoundsException e){

        }
    }

    @Override
    public synchronized void broadcast(ClientIF client, String msg) {
        for(int i = 0;i<ServerMain.getConnectionList().size();i++){
            try {
                ServerMain.getConnectionList().get(i).displayMsg(client.getName()+": "+msg);
            } catch (RemoteException e) {
                ServerMain.getConnectionList().remove(ServerMain.getConnectionList().get(i));
                //System.out.println(ServerMain.getNameList().get(i));
                //ServerMain.getNameList().remove(ServerMain.getNameList().get(i));
                i--;
            }
        }
    }

    @Override
    public synchronized void checkIfAlive(ClientIF client){

    }


    @Override
    public synchronized void clientConnect(ClientIF client) throws RemoteException {
        broadcast(client," has connected");
        ServerMain.getConnectionList().add(client);
        //ServerMain.getNameList().add(client.getName());
    }
}
