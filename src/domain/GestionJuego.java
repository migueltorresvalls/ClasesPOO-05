package domain;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import domain.objetosJuego.Manzana;
import domain.objetosJuego.ObjetoJuego;
import io.IOFichero;
import ui.Juego;
import ui.Tablero;

public class GestionJuego {

    private HashMap<String, Boolean> teclasPulsadas = new HashMap<>();
    private ArrayList<ObjetoJuego> objetos = new ArrayList<>();

    private Juego juego; 

    public GestionJuego(Juego juego){
        objetos.add(IOFichero.leerFichero());
        this.juego = juego; 
    }

    public void setDireccion(String tecla){
        Set<String> direcciones = teclasPulsadas.keySet();
        for(String t : direcciones){
            if (t.equals(tecla)){
                teclasPulsadas.put(t, true);
            } else {
                teclasPulsadas.put(t, false);
            }
        }
    }

    public void initTeclas(){
        teclasPulsadas.put("derecha", true);
        teclasPulsadas.put("izquierda", false);
        teclasPulsadas.put("arriba", false);
        teclasPulsadas.put("abajo", false);

        juego.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                switch (e.getKeyCode()){
                    case KeyEvent.VK_RIGHT :
                        setDireccion("derecha");
                        break;
                    case KeyEvent.VK_LEFT :
                        setDireccion("izquierda");
                        break;
                    case KeyEvent.VK_UP :
                        setDireccion("arriba");
                        break;
                    case KeyEvent.VK_DOWN :
                        setDireccion("abajo");
                        break;
                    default: break;
                }
            }
        });
    }

    public void recalcularPosicion(){
        objetos.forEach(obj -> {
            if (obj instanceof Manzana manzana){
                manzana.setDireccion(teclasPulsadas);
                manzana.mover();
            }
        });
    }

    public void initJuego(){
        Tablero tablero = new Tablero(this);
        initTeclas();

        juego.add(tablero);
    }

    public ArrayList<ObjetoJuego> getObjetos(){
        return objetos;
    }
}
