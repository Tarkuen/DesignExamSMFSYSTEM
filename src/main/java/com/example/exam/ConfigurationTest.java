package com.example.exam;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ConfigurationTest {

    private String[] locations;
    private File inputFile;

    @Before
    public void setUp() throws Exception {
        inputFile = new File("testFile");
        locations = new String[100];
    }

    @Test
    public void gen() {

        String[] testInput= {"'HE';'FAN-HE'"
        , "'DE';'FAN-DE'"};

        for (int i = 0; i < testInput.length; i++) {
            String[] split = testInput[i].split(";");
            split[0]=split[0].replaceAll("'","");
            split[1]=split[1].replaceAll("'","");

        for (int i1 = 0; i1 < split.length; i1++) {
            locations[i]=split[i1];
            i++;
        }
        }
        assertEquals("FAN-HE",locations[1]);
    }


    @Test
    public void getFaction() {
        String input = "HE SOLDIER";
        String[] split = input.split(" ");

        gen();

        for (int i = 0; i < locations.length; i++) {
            if(split[0].equalsIgnoreCase(locations[i])){
                assertEquals("FAN-HE", locations[i + 1]);
            }
        }
    }
}