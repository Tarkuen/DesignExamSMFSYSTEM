package com.example.exam;

import com.example.exam.logic.LineEngineT;
import com.example.exam.model.Configuration;
import com.example.exam.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamApplicationTests {

    LinkedList<MultipartFile> linkedList = new LinkedList<>();

    LineEngineT lineEngineT = new LineEngineT();

    Configuration configuration = new Configuration(new File("C:\\Users\\Tarkuen\\Desktop\\config.txt"));

    ArrayList<Product> productlist = new ArrayList<>();

    @Test
    public void contextLoads() {
    }

    public LinkedList<MultipartFile> genereateDB() throws IOException {

        File file = new File("HE SPYDMAND.txt");
        file.createNewFile();
        String testScriptPath = file.getAbsolutePath();

        linkedList.add(new MockMultipartFile(
                "HE SPYDMAND",
                new FileInputStream(testScriptPath)
                )
        ); linkedList.add(new MockMultipartFile(
                "ÆØÅ",
                new FileInputStream(testScriptPath)
                )
        ); linkedList.add(new MockMultipartFile(
                "123",
                new FileInputStream(testScriptPath)
                )
        );
//        linkedList.add(new MockMultipartFile(
//                " ",
//                new FileInputStream(testScriptPath)
//                ));

        linkedList.add(new MockMultipartFile(
                "@£$€{[]€[{€$£",
                new FileInputStream(testScriptPath)
                )
        );

        return linkedList;
    }

    @Test
    public void testFileSelector() throws IOException {
    linkedList = genereateDB();

        for (MultipartFile multipartFile : linkedList) {
            String[] image = new String[2];

            image[0] = multipartFile.getName();
            image[1] = multipartFile.getName();

            productlist.add(new Product(image));
        }

        for (Product product : productlist) {
            System.out.println(product.getBeskrivelse());
        }

        lineEngineT = new LineEngineT(productlist);
        lineEngineT.createProduct();
    }


    @Test
    public void testFileConfiguration() throws IOException{
        configuration.gen();
        linkedList = genereateDB();

        for (MultipartFile multipartFile : linkedList) {
            String[] image = new String[2];

            image[0] = multipartFile.getName();
            image[1] = multipartFile.getName();

            productlist.add(new Product(image));
        }

        for (Product product : productlist) {
            System.out.println(product.toString());
            product.setFacLoc(configuration.getFaction(product.getBeskrivelse()));
        }



    }

}
