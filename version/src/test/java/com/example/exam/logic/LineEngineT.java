package com.example.exam.logic;

import com.example.exam.model.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class LineEngineT {

    private ArrayList<Product> productList;
    private final File outputFile = new File("C:\\Users\\Tarkuen\\Desktop\\product.txt");
    private PrintWriter printWriter;

    public String createOneProductLine(Product product){
        String salesline ="";
        product.setProductLine(salesline.concat("[img]"+product.getThumbLoc()+product.getImage()[0]+"[/img][url="+product.getServLoc()+product.getImage()[1]+"]"+String.valueOf(product.getAntal())+"stk. "+product.getBeskrivelse()+"[/url]"));
        return product.getProductLine();
    }

    public void createProduct(){
        System.out.println("J_SYS: ATTEMPTING TO WRITE TO FILE @ "+ LocalDateTime.now().toString());
        for (Product product : this.productList) {
            printWriter.write(createOneProductLine(product));
        }
        printWriter.flush();
        System.out.println("J_SYS: COMPLETED PRODUCTION ON FILE # "+outputFile.getAbsolutePath());
    }

    public LineEngineT(ArrayList<Product> productList) {
        this.productList = productList;
        try {
            try {
                outputFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printWriter = new PrintWriter(outputFile);
            System.out.println("FOUND FILE");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public LineEngineT() {
    }
}
