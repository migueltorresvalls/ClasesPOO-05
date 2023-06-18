package ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import domain.GestionJuego;

public class Tablero extends JPanel {

    private static final int FPS = 60;

    private GestionJuego gestion; 

    public Tablero(GestionJuego gestion){
        super();
        this.gestion = gestion; 

        setBackground(Color.BLACK);

        new Thread(() -> {
            while (true){
                gestion.recalcularPosicion();
                repaint(); // SIEMPRE
                try {
                    Thread.sleep(calcularDelay());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // En milisegundos
    private int calcularDelay(){
        return (int) (1/(FPS*1.)*1000);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        gestion.getObjetos().forEach(obj -> obj.pintar(g));
    }
}
