package domain.objetosJuego;

import java.awt.Color;
import java.awt.Graphics;

import ui.Juego;
import util.Generador;

public class Ladrillo extends ObjetoJuego {

    private String direccion = "derecha";

    public static int VIDA_LADRILLO;

    public Ladrillo(int vida, int ancho, int alto, Color color, int cuarto){
        super(Generador.generaX(0, Juego.ANCHO-ancho), Generador.generaX(((Juego.ALTO-100)/4)*(cuarto-1), ((Juego.ALTO-100)/4)*cuarto), vida, ancho, alto, color, Generador.generaVelocidad());

        VIDA_LADRILLO = vida;
    }


    public void compruebaDireccion(){
        boolean limiteDerecho = (x+velocidad<(Juego.ANCHO-ancho) && direccion.equals("derecha"));
        boolean limiteIzquierdo = (x-velocidad<=0);
        if (limiteDerecho || limiteIzquierdo){
            direccion = "derecha";
        } else {
            direccion = "izquierda";
        }
    }

    public boolean detectarColision(Manzana manzana){
        return getRectanguloInterseccion().intersects(manzana.getRectanguloInterseccion());
    }

    @Override
    public void mover() {
        compruebaDireccion();
        if (direccion.equals("derecha")){
            setX(x+velocidad);
        } else {
            setX(x-velocidad);
        }
    }

    @Override
    public void pintar(Graphics g) {
        g.setColor(color);

        g.fillRect(x, y, ancho, alto);
    }


    
}
