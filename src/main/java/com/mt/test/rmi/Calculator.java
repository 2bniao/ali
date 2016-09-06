package com.mt.test.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
    String calculate(String expr) throws RemoteException;
}
