package domain.objetosJuego;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import ui.Juego;

public class Manzana extends ObjetoJuego{

    private HashMap<String, Boolean> direccion = new HashMap<>(); // Derecha, true. Izquireda, false. etc

    public Manzana(int vida, int ancho, int alto, Color color, int velocidad){
        super(50, Juego.ALTO-100, vida, ancho, alto, color, velocidad);
    }

    public void setDireccion(HashMap<String, Boolean> direccion){
        this.direccion = direccion; 
    }

    @Override
    public void mover(){
        // Ejemplo: "derecha", true
        direccion.forEach((dir, valor) -> {
            if (valor){
                if (dir.equals("derecha")){
                    setX(x+velocidad);
                } else if(dir.equals("izquierda")){
                    setX(x-velocidad);
                } else if (dir.equals("arriba")){
                    setY(y-velocidad);
                } else {
                    setY(y+velocidad);
                }
            }
        });
    }

    public void restarVida(int deltaVida){
        vida -= deltaVida;
    }

    public boolean isVivo(){
        return vida>0;
    }

    @Override
    public void pintar(Graphics g){
        g.setColor(color);

        g.fillOval(x, y, ancho, alto);
    }
    
}
