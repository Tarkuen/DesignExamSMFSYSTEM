package com.example.exam;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestAuto {


    public static void run(){

        Result result = JUnitCore.runClasses(TestSuiteAll.class);

        System.out.println("Compiling Test Failures (if any)");
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.getTestHeader()+ "Failed");
            System.out.println(failure.getMessage());
        }
        if(result.wasSuccessful()){
            System.out.println("Tests were a success");
            System.out.println(result.wasSuccessful());
        }
    }
}
