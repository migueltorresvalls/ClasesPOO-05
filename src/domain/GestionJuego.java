package domain;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

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
        for (String t : teclasPulsadas.keySet()){
            if (t.equals(tecla)){
                System.out.println(t + " = " + tecla);
                teclasPulsadas.put(tecla, true);
            } else {
                teclasPulsadas.put(tecla, false);
            }
        }
        System.out.println(teclasPulsadas);
    }

    public void initTeclas(){
        teclasPulsadas.put("derecha", true);
        teclasPulsadas.put("izquierda", false);
        teclasPulsadas.put("arriba", false);
        teclasPulsadas.put("abajo", false);

        System.out.println(teclasPulsadas);

        juego.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                switch (e.getKeyCode()){
                    case KeyEvent.VK_RIGHT :
                        //setDireccion("derecha");
                        teclasPulsadas.put("derecha", true);
                        teclasPulsadas.put("izquierda", false);
                        teclasPulsadas.put("arriba", false);
                        teclasPulsadas.put("abajo", false);
                        break;
                    case KeyEvent.VK_LEFT :
                        teclasPulsadas.put("derecha", false);
                        teclasPulsadas.put("izquierda", true);
                        teclasPulsadas.put("arriba", false);
                        teclasPulsadas.put("abajo", false);
                        //setDireccion("izquierda");
                        break;
                    case KeyEvent.VK_UP :
                        teclasPulsadas.put("derecha", false);
                        teclasPulsadas.put("izquierda", false);
                        teclasPulsadas.put("arriba", true);
                        teclasPulsadas.put("abajo", false);
                        //setDireccion("arriba");
                        break;
                    case KeyEvent.VK_DOWN :
                        teclasPulsadas.put("derecha", false);
                        teclasPulsadas.put("izquierda", false);
                        teclasPulsadas.put("arriba", false);
                        teclasPulsadas.put("abajo", true);
                        //setDireccion("abajo");
                        break;
                    /* Para moverse arriba a la derecha
                     * case KeyEvent.VK_UP && KeyEvent.VK_RIGHT
                            teclasPulsadas.put("derecha", true);
                            teclasPulsadas.put("izquierda", false);
                            teclasPulsadas.put("arriba", true);
                            teclasPulsadas.put("abajo", false);
                     */
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
