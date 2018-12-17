package com.example.exam.logic;

import com.example.exam.model.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class LineEngine {

    private ArrayList<Product> productList;
    private final File outputFile = new File("C:\\Users\\Tarkuen\\Desktop\\product.txt");
    private PrintWriter printWriter;
    private boolean found;
    private boolean create;

    public String createOneProductLineFB(Product product) {
        String salesline = "";
        try {
            String beskrivelse = product.getImage()[1].replaceAll(" ","%20");
            product.setProductLine(salesline.concat(
                    product.getServLoc() +
                    product.getFacLoc() +
                    '/' + beskrivelse+" "
                            + String.valueOf(
                    product.getAntal()) +
                    "stk. "
                            + product.getBeskrivelse())
                    + product.getProductType()+" "
                    + '\n'
            );
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
            if (product.getFacLoc().isEmpty() || product.getFacLoc() == null) {
                System.out.println("\n");
            } else if (product.getBeskrivelse().isEmpty() || product.getBeskrivelse() == null) {
                System.out.println("\n");
                System.out.println("Invalid File Name");
                product.setBeskrivelse("*****NA");
            }
        }
        return product.getProductLine();
    }

    public String createOneProductLinePF(Product product){
        String salesline ="";
        try{
        product.setProductLine(salesline.concat("[img]"+product.getThumbLoc()+
                product.getImage()[0] +
                "[/img][url="+
                product.getServLoc()+
                product.getFacLoc()+
                '/'+product.getImage()[1]+
                "] "+String.valueOf(
                        product.getAntal())+
                "stk. "+
                product.getBeskrivelse()
                +"[/url]")
                +product.getProductType()
                +'\n'
        );
        }catch (NullPointerException ex){
            System.out.println(ex.getMessage());
            if(product.getFacLoc().isEmpty() || product.getFacLoc()==null){
                System.out.println("\n");
                System.out.println("Factory Location not found");
                product.setFacLoc("*****NA");
                this.create=false;
            }
            else if(product.getBeskrivelse().isEmpty() || product.getBeskrivelse()==null){
                System.out.println("\n");
                System.out.println("Invalid File Name");
                product.setBeskrivelse("*****NA");
            }
            this.create=false;
        }
        this.create=true;

        return product.getProductLine();
    }

    public void createProduct(String platform){
        System.out.println("J_SYS: ATTEMPTING TO WRITE TO FILE @ "+ LocalDateTime.now().toString());
        if(platform.equalsIgnoreCase("SMF")){
            for (Product product : productList) {
                printWriter.write(createOneProductLinePF(product));
            }
            printWriter.flush();
        }
        if(platform.equalsIgnoreCase("Facebook")){
            for (Product product : productList) {
                printWriter.write(createOneProductLineFB(product));
            }
            printWriter.flush();
        }
        System.out.println("J_SYS: COMPLETED PRODUCTION ON FILE # "+outputFile.getAbsolutePath());
    }

    public LineEngine(ArrayList<Product> productList) {
        this.productList = productList;
        try {
            try {
                outputFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printWriter = new PrintWriter(outputFile);
            System.out.println("FOUND FILE");
            this.found = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            this.found=false;
        }
    }


    public LineEngine() {
    }


    public boolean isFound() {
        return found;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public boolean isCreate() {
        return create;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }
}
