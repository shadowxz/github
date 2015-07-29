package com.sheep.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiService extends Remote{
	
	public void hello(String str) throws RemoteException;
	
}
