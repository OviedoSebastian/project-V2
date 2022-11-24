package gui;

import levels.Laberinto;
import net.Despachador;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Tablero extends Canvas implements KeyListener {

    Laberinto laberinto = new Laberinto();
    int [][] lab = laberinto.obtenerLaberinto();
    public HashMap<String, Jugador> jugadores = new HashMap<>(2);
    public String jugadorPresente = "";
    public Despachador despachador;
    private final int ancho = 30;
    private final int alto = 30;
    private final int movimiento = 30;

    public Tablero()
    {
        super();
        setBackground(Color.white);
        setSize(690,390);
        addKeyListener(this);
        setFocusable(true);
   }

    public void paint(Graphics g) {
        int posicion = 30;
        for (Jugador j: jugadores.values()) {
            laberinto.paintfield(g);
            g.setColor(j.login);
            g.fillOval(j.x, j.y, ancho, alto);
            posicion+=posicion;
        }
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) { }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_UP:
                if(lab[(jugadores.get(jugadorPresente).y/30)-1][jugadores.get(jugadorPresente).x/30] != 1){
                    jugadores.get(jugadorPresente).y -= movimiento;
                }
                break;
            case KeyEvent.VK_RIGHT:
               if(lab[(jugadores.get(jugadorPresente).y) /30][(jugadores.get(jugadorPresente).x/30)+1] != 1){
                    jugadores.get(jugadorPresente).x += movimiento;
               }
                break;
            case KeyEvent.VK_DOWN:
                if(lab[(jugadores.get(jugadorPresente).y/30)+1][(jugadores.get(jugadorPresente).x/30)] != 1){
                    jugadores.get(jugadorPresente).y += movimiento;
                }
                break;
            case KeyEvent.VK_LEFT:
                if(lab[jugadores.get(jugadorPresente).y/30][(jugadores.get(jugadorPresente).x/30)-1] != 1){
                    jugadores.get(jugadorPresente).x -= movimiento;
                }
                break;
        }

        int _x = jugadores.get(jugadorPresente).x;
        int _y = jugadores.get(jugadorPresente).y;
        despachador.send("mover:" + jugadorPresente + "," + _x + "," + _y );
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) { }
}
