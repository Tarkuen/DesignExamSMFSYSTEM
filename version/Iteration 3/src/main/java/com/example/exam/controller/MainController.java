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
            String result ="";
            String result1 ="";
            String[] image = new String[2];


            if(!multipartFile.getOriginalFilename().isEmpty() || multipartFile.getOriginalFilename()==null){
                String[] split = multipartFile.getOriginalFilename().split("(\\.)");
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
    public String setNumber(@RequestParam(name = "number")LinkedList<String> fileList,
                            @RequestParam(name= "platform")String platform
                            ){

         mediator = new Mediator(preList,fileList,lineEngine);

        preList = mediator.generateAntal();

        lineEngine = new LineEngine(preList);

        lineEngine.createProduct(platform);

        return "homepage";
    }



    @GetMapping("/")
    public String index(){
        return "homepage";
    }

}
