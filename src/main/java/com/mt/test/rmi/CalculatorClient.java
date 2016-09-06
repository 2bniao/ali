package com.mt.test.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorClient {
    public void calculate(String expr) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1");
            Calculator calculator = (Calculator) registry.lookup("Calculator");
            String result = calculator.calculate(expr);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CalculatorClient c = new CalculatorClient();
        c.calculate("2123");
    }
}
