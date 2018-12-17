package com.example.exam.controller;

import com.example.exam.logic.LineEngine;
import com.example.exam.logic.Mediator;
import com.example.exam.model.Configuration;
import com.example.exam.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.SchemaOutputResolver;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

@Controller
public class MainController {

    LineEngine lineEngine;
    Mediator mediator;
    ArrayList<Product> preList;
    Configuration configuration = new Configuration(new File("C:\\Users\\Tarkuen\\Desktop\\config.txt"));

    @PostMapping("/selectFile")
    public String selectFile(@RequestParam(name = "files")LinkedList<MultipartFile> fileList,
                             Model model){
        ArrayList<Product> productlist = new ArrayList<>();

        for (MultipartFile multipartFile : fileList) {
            String[] image = new String[2];

            image[0] = multipartFile.getOriginalFilename();
            image[1] = multipartFile.getOriginalFilename();

            productlist.add(new Product(image));
        }
            this.preList = productlist;

        configuration.gen();

        for (Product product : preList) {
            product.setFacLoc(configuration.getFaction(product.getBeskrivelse()));
        }

        model.addAttribute("list", productlist);
        return "selectAntal";
    }

    @GetMapping("/selectFile")
    public String goToFile(){
        return "selectFile";
    }

    @PostMapping("/selectAntal")
    public String setNumber(@RequestParam(name = "number")LinkedList<String> fileList){

         mediator = new Mediator(preList,fileList,lineEngine);

        preList = mediator.generateAntal();



        lineEngine = new LineEngine(preList);

        lineEngine.createProduct();

        return "homepage";
    }



    @GetMapping("/")
    public String index(){
        return "homepage";
    }

}
