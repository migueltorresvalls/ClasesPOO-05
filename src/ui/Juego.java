package ui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.GestionJuego;

public class Juego extends JFrame {

    public static final int ANCHO = 600;
    public static final int ALTO = 600;

    public Juego(){
        super("JuegoPrueba");

        GestionJuego gestion = new GestionJuego(this);
        gestion.initJuego();

        setResizable(false);
        setVisible(true);
        setSize(ANCHO, ALTO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        new Juego();
    }
}
