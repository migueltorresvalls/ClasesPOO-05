package domain;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import domain.objetosJuego.Ladrillo;
import domain.objetosJuego.Manzana;
import domain.objetosJuego.ObjetoJuego;
import io.IOFichero;
import ui.Juego;
import ui.Tablero;

public class GestionJuego {

    private Manzana manzana;

    private HashMap<String, Boolean> teclasPulsadas = new HashMap<>();
    private ArrayList<ObjetoJuego> objetos = new ArrayList<>();

    private Juego juego; 

    public GestionJuego(Juego juego){
        objetos = IOFichero.leerFichero();
        // Sacamos objeto manzana para colision
        objetos.forEach(o -> {
            if (o instanceof Manzana mz){
                manzana = mz;
            }
        });
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
            // Muevo todos los objetos
            obj.mover();
            if (obj instanceof Manzana manzana){
                // Compruebo teclas pulsadas 
                manzana.setDireccion(teclasPulsadas);
            } else if (obj instanceof Ladrillo ladrillo){
                // Compruebo colision con manzana
                if (ladrillo.detectarColision(manzana)){
                    manzana.restarVida(Ladrillo.VIDA_LADRILLO);
                }
            }
        });
    }

    public Manzana getManzana(){
        return manzana; 
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
