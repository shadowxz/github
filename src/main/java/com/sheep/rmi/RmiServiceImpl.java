package com.sheep.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiServiceImpl extends UnicastRemoteObject implements RmiService {
    
    private static final long serialVersionUID = 1L;

    public RmiServiceImpl() throws RemoteException {
        super();
    }

    public void hello(String str) {
        System.out.println("RmiService : " + str);
    }

}
