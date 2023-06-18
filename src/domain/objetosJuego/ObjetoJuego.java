package domain.objetosJuego;

import java.awt.Color;
import java.awt.Graphics;

import ui.Juego;

public abstract class ObjetoJuego {

    protected int x = 50; 
    protected int y = Juego.ALTO-100;
    protected int vida; 
    protected int ancho; 
    protected int alto; 
    protected int velocidad; 

    protected Color color; 

    public ObjetoJuego(){

    }

    public ObjetoJuego(int vida, int ancho, int alto, Color color, int velocidad){
        this.vida = vida; 
        this.ancho = ancho; 
        this.alto = alto; 
        this.color = color; 
        this.velocidad = velocidad; 
    }

    public void setX(int x){
        if (x>0 && (x+ancho)<Juego.ANCHO){
            this.x = x; 
        }
    }

    public void setY(int y){
        if (y>0 && (y-(alto-100))<Juego.ALTO){
            this.y = y; 
        }
    }
    
    public abstract void mover();
    public abstract void pintar(Graphics g);

    @Override
    public String toString(){
        return "Vida: " + vida + ", ancho: " + ancho + ", alto: " + alto + ", color: " + color; 
    }
}
