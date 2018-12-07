package com.example.exam.logic;

import com.example.exam.model.Product;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Mediator {

    ArrayList<Product> afterProductList;
    LinkedList<String> preProductList;
    LineEngineT lineEngineT;

    public Mediator(ArrayList<Product> afterProductList, LinkedList<String> preProductList, LineEngineT lineEngineT) {
        this.afterProductList = afterProductList;
        this.preProductList = preProductList;
        this.lineEngineT = lineEngineT;
    }

    public LinkedList<String> getPreProductList() {
        return preProductList;
    }

    public void setPreProductList(LinkedList<String> preProductList) {
        this.preProductList = preProductList;
    }

    public void setAfterProductList(ArrayList<Product> afterProductList) {
        this.afterProductList = afterProductList;
    }

    public List<Product> getAfterProductList() {
        return afterProductList;
    }

    public LineEngineT getLineEngineT() {
        return lineEngineT;
    }

    public void setLineEngineT(LineEngineT lineEngineT) {
        this.lineEngineT = lineEngineT;
    }

    public List<Product> generateAntal(){

        int i = 0;

        for (Product product : afterProductList) {
            product.setAntal(Integer.valueOf(preProductList.get(i)));
            i++;
        }

        return afterProductList;
    }
}


