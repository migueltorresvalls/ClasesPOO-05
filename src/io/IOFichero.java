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
    public static ObjetoJuego leerFichero(){
        ArrayList<String> objetos = new ArrayList<>();
        objetos.add("manzana");
        objetos.add("ladrillo");
        objetos.add("bandeja");

        HashMap<String, Color> colores = new HashMap<>();
        colores.put("red", Color.RED);
        colores.put("blue", Color.BLUE);

        ObjetoJuego objetoJuego = null;
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

                // Objeto
                String campoObjeto = campos[0].trim().toLowerCase();
                if (!(objetos.contains(campoObjeto))){
                    br.close();
                    throw new ObjetoNoValido(campoObjeto);
                } else {
                    // Color
                    String campoColor = campos[4].trim().toLowerCase();
                    if (colores.keySet().contains(campoColor)){
                        if (campoObjeto.equals("manzana")){
                            objetoJuego = new Manzana(vida, ancho, alto, colores.get(campoColor), 3);
                        }
                        /*
                         * else if (campoObjeto.equals("manzana")){
                            objetoJuego = new Ladrillo(vida, ancho, alto, colores.get(campoColor), 3);
                         }
                         */
                    } else {
                        br.close();
                        throw new ColorNoValido(campoColor);
                    }
                    /*
                    if (campoColor.equals("blue")){
                        objetoJuego = new ObjetoJuego(vida, ancho, alto, Color.BLUE);
                    } else {
                        br.close();
                        throw new ColorNoValido(campoColor);
                    }
                     */
                }

                linea = br.readLine();
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return objetoJuego;
    }  
}
