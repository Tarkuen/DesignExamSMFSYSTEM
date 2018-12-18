package com.example.exam.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Configuration {

    private File inputFile;

    private Scanner scanner;

    private String[] locations;

    public Configuration(File inputFile) {
        this.inputFile = inputFile;
        locations = new String[100];
    }

    public String[] gen() {

        try {
            this.scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } int i = 0;
            int j = 1;
        do{ String line = scanner.nextLine();

            String[] split = line.split(";");
            split[0]=split[0].replaceAll("'","");
            split[1]=split[1].replaceAll("'","");

            for (int i1 = 0; i1 < split.length; i1++) {
                locations[i]=split[i1];
                i++;
            }


        }while(scanner.hasNext());




        setLocations(locations);
        return locations;
    }


    public String getFaction(String input){

        String[] split = input.split(" ");

        for (int i = 0; i < locations.length; i++) {
            if(split[0].equalsIgnoreCase(locations[i])){
                return locations[i+1];
            }
        }
//        Standard mappe Struktur
        return locations[0];
    }




    public File getInputFile() {
        return inputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public String[] getLocations() {
        return locations;
    }

    public void setLocations(String[] locations) {
        this.locations = locations;
    }
}
