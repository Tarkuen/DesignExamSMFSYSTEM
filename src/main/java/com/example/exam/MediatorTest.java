package com.example.exam;

import com.example.exam.model.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class MediatorTest {

    ArrayList<Product> afterProductList;
    LinkedList<String> preProductList;

    @Before
    public void setUp() throws Exception {
        afterProductList = new ArrayList<>();
        String[] images = {"www.blirup.dk/asdasdsaasd", "www.blirup.dk/asdasdsaasd"};
        afterProductList.add(new Product("@£$€{[]€[{€$£ HE Eltharion The Grim (Metal).Png", 3, images, "(Plastic)"));
        afterProductList.add(new Product("ÆØÅ HE Eltharion The Grim (Metal).Png", 3, images, "(Plastic)"));
        afterProductList.add(new Product("HE Eltharion The Grim (Metal).Png", 3, images, "(Plastic)"));

        preProductList = new LinkedList<>();
        preProductList.add("1");
        preProductList.add("3");
        preProductList.add("5");

    }

    @Test
    public void generateAntal() {

        int i = 0;

        for (Product product : afterProductList) {
            product.setAntal(Integer.valueOf(preProductList.get(i)));
            i++;
        }
        for (Product product : afterProductList) {
            if (product.getBeskrivelse().equalsIgnoreCase("@£$€{[]€[{€$£ HE Eltharion The Grim (Metal).Png")) {
                assertEquals(1, product.getAntal());
            }
            if (product.getBeskrivelse().equalsIgnoreCase("ÆØÅ HE Eltharion The Grim (Metal).Png")) {
                assertEquals(3, product.getAntal());
            }
            if (product.getBeskrivelse().equalsIgnoreCase("HE Eltharion The Grim (Metal).Png")) {
                assertEquals(5, product.getAntal());
            }
        }

    }
}