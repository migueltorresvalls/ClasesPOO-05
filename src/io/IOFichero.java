package io;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import domain.exceptions.ColorNoValido;
import domain.exceptions.ObjetoNoValido;
import domain.objetosJuego.Ladrillo;
import domain.objetosJuego.Manzana;
import domain.objetosJuego.ObjetoJuego;

public class IOFichero {
    /*
     * GestionJuego:
     * try {
     *  IOFichero.leerFichero()
     * } catch(){
     *  something to do
     * }
     */
    public static ArrayList<ObjetoJuego> leerFichero(){
        ArrayList<ObjetoJuego> objetosJuego = new ArrayList<>();

        ArrayList<String> objetosValidos = new ArrayList<>();
        objetosValidos.add("manzana");
        objetosValidos.add("ladrillo");

        HashMap<String, Color> coloresValidos = new HashMap<>();
        coloresValidos.put("red", Color.RED);
        coloresValidos.put("gray", Color.GRAY);
        coloresValidos.put("orange", Color.ORANGE);
        coloresValidos.put("green", Color.GREEN);
        coloresValidos.put("blue", Color.BLUE);

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("resources/figuras.csv")));

            String linea = br.readLine(); 
            while (linea != null){
                String[] campos = linea.split(":");
                // Objeto: vida: ancho: alto
                // String campoVida = campos[1].trim();
                // int vida = Integer.parseInt(campoVida);

                int vida = Integer.parseInt(campos[1].trim());
                int ancho = Integer.parseInt(campos[2].trim());
                int alto = Integer.parseInt(campos[3].trim());
                String campoObjeto = campos[0].trim().toLowerCase();
                String campoColor = campos[4].trim().toLowerCase();

                // Objeto
                if (!(objetosValidos.contains(campoObjeto))){
                    br.close();
                    throw new ObjetoNoValido(campoObjeto);
                } else {
                    // Color
                    if (coloresValidos.keySet().contains(campoColor)){
                        if (campoObjeto.equals("manzana")){
                            ObjetoJuego manzana = new Manzana(vida, ancho, alto, coloresValidos.get(campoColor), 3);
                            objetosJuego.add(manzana);
                        } else if (campoObjeto.equals("ladrillo")){
                            int tercio = Integer.parseInt(campos[5].trim());
                            ObjetoJuego ladrillo = new Ladrillo(vida, ancho, alto, coloresValidos.get(campoColor), tercio);
                            objetosJuego.add(ladrillo);
                        }
                    } else {
                        br.close();
                        throw new ColorNoValido(campoColor);
                    }
                }

                linea = br.readLine();
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return objetosJuego;
    }  
}
