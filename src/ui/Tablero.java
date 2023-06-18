package ui;

import java.awt.Color;
import java.awt.Font;
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
            while (gestion.getManzana().isVivo()){
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

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        g.drawString("Vida: " + gestion.getManzana().getVida(), 50, 50);

        gestion.getObjetos().forEach(obj -> obj.pintar(g));
    }
}
