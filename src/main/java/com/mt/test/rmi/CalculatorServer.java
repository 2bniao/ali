package com.mt.test.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorServer implements Calculator {

    public String calculate(String expr) throws RemoteException {
        return "calculate";
    }

    public void start() throws RemoteException, AlreadyBoundException {
        Calculator stub = (Calculator) UnicastRemoteObject.exportObject(this, 2365);
        Registry registry = LocateRegistry.getRegistry();
        registry.rebind("Calculator", stub);
    }

    public static void main(String[] args) {
        try {
            new CalculatorServer().start();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (AlreadyBoundException e) {
            throw new RuntimeException(e);
        }
    }

}
