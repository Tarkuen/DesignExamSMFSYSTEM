package com.example.exam;

import com.example.exam.logic.LineEngine;
import com.example.exam.logic.Mediator;
import com.example.exam.model.Configuration;
import com.example.exam.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ExamApplicationTests {

    LinkedList<MultipartFile> linkedList;
    LinkedList<String> antalList;

    LineEngine lineEngineT = new LineEngine();

    Configuration configuration = new Configuration(new File("C:\\Users\\Tarkuen\\Desktop\\config.txt"));

    ArrayList<Product> productlist = new ArrayList<>();

    LinkedList<String> productType = new LinkedList<>();

    @Test
    public void contextLoads() {
    }

    @Before
    @Test
    public void genereateDB() throws IOException {
        linkedList = new LinkedList<>();
        antalList = new LinkedList<>();
        productType = new LinkedList<>();
        File file = new File("HE SPYDMAND.txt");
        file.createNewFile();
        String testScriptPath = file.getAbsolutePath();

        linkedList.add(new MockMultipartFile(
                "HE Eltharion The Grim (Metal).Jpg",
                new FileInputStream(testScriptPath)
                )
        ); linkedList.add(new MockMultipartFile(
                "HE ÆØÅ The Grim (Metal).Gif",
                new FileInputStream(testScriptPath)
                )
        ); linkedList.add(new MockMultipartFile(
                "HE 123213 Eltharion ERASD Grim (Resin).Jpg",
                new FileInputStream(testScriptPath)
                )
        );
        linkedList.add(new MockMultipartFile(
                "HE Eltharion The Grim (Plastic).Png",
                new FileInputStream(testScriptPath)
                ));

        linkedList.add(new MockMultipartFile(
                "@£$€{[]€[{€$£ HE Eltharion The Grim (Metal).Png",
                new FileInputStream(testScriptPath)
                )
        );

        String[] images = {"www.blirup.dk/asdasdsaasd", "www.blirup.dk/asdasdsaasd"};
        productlist.add(new Product("@£$€{[]€[{€$£ HE Eltharion The Grim (Metal).Png", 3,images,"(Plastic)"));
        productlist.add(new Product("@£$€{[]€[{€$£ HE Eltharion The Grim (Metal).Png", 3,images,"(Plastic)"));
        productlist.add(new Product("@£$€{[]€[{€$£ HE Eltharion The Grim (Metal).Png", 3,images,"(Plastic)"));

    }

    @Test
    public void testFileSelector() throws IOException {

        for (MultipartFile multipartFile : linkedList) {
            String[] image = new String[2];

            String[] split = multipartFile.getOriginalFilename().split(" ");

            for (int i = 0; i < split.length-1; i++) {
                image[0] = image[0].concat(split[i]);
                image[1] = image[1].concat(split[i]);
            }

            productlist.add(new Product(image,split[split.length-1]));
        }

        lineEngineT = new LineEngine(productlist);
        Assert.assertTrue(lineEngineT.isFound());
        lineEngineT.createProduct("SMF");
        Assert.assertTrue(lineEngineT.isCreate());

    }

    @Test
    public void testFileConfiguration() throws IOException{
        configuration.gen();

        for (Product product : productlist) {
            product.setFacLoc(configuration.getFaction(product.getBeskrivelse()));
            if(product.getBeskrivelse().equalsIgnoreCase("HE SPYDMAND")){
                Assert.assertEquals("FAN-HE", product.getFacLoc());
            }
        }
    }

    @Test
    public void testProductType(){

        for (MultipartFile multipartFile : linkedList) {
            String result ="";
            String result1 ="";
            String[] image = new String[2];

            String[] split = multipartFile.getName().split("(\\.)");
            result1='.'+split[1];

            split = split[0].split(" ");

            for (int i = 0; i < split.length-1; i++) {

                result = result.concat(split[i]);
                if(i<split.length-2){
                    result = result.concat(" ");
                }
            }
            result = result.concat(result1);
            image[0] = result;
            image[1] = result;

            productlist.add(new Product(image, split[split.length-1]));
            result = null;
            result1 = null;
            image = null;
        }

        for (Product product : productlist) {

            if (product.getBeskrivelse().equalsIgnoreCase("HE Eltharion The Grim")){
                Assert.assertEquals("(Plastic)", product.getProductType());
            }
            if(product.getBeskrivelse().equalsIgnoreCase("HE 123213 Eltharion ERASD Grim")){
                Assert.assertEquals("(Resin)", product.getProductType());
            }if(product.getBeskrivelse().equalsIgnoreCase("@£$€{[]€[{€$£ HE Eltharion The Grim")){
                Assert.assertEquals("(Metal)", product.getProductType());
            }
        }
    }

    @Test
    public void platformChoice() throws FileNotFoundException {

        LineEngine lineEngine = new LineEngine(productlist);
        Scanner scn = new Scanner(lineEngine.getOutputFile().getAbsoluteFile());
        String platform = "Facebook";
        lineEngine.createProduct(platform);
//        Assert.assertEquals("[url=www.bilirip.dk/tp/pt/null/www.blirup.dk/asdasdsaasd] 3stk. @£$€{[]€[{€$£ HE Eltharion The Grim (Metal).Png[/url](Plastic)", scn.nextLine());
    }


}
