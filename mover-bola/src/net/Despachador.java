package net;

import gui.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Despachador extends Thread {
    private PrintWriter out;
    private BufferedReader in;
    private String tipo = "lector";
    private Socket socket;
    public VentanaPrincipal gui = null;
    public ArrayList<Despachador> jugadoresenlinea = new ArrayList<>();
    public HashMap<String, Jugador> jugadores = new HashMap<>(2);

    private int Xnicial=0, Yinicial=0;

    public Despachador(Socket socket, String tipo) {
        try {
            this.in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.socket = socket;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void run(){
        try {
            iniciarJuego();
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void iniciarJuego() throws IOException  {
        String inputLine;
        while ((inputLine = in.readLine()) != null) {

            if (gui != null) {  // Cliente
                String[] datosJugadores = inputLine.split("#");
                for (String jugador: datosJugadores) {
                    String[] data = jugador.split(",");
                    gui.lienzo.jugadores.put(data[0] , new Jugador(data[0], seleccionarcolor(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])));
                    System.out.println(datosJugadores[0]);

                }
                gui.lienzo.repaint();
            }

            if (gui == null) {  // Servidor
                String[] datos = inputLine.split(":");
                if (datos[0].equals("login")) {
                    jugadores.put(datos[1] , new Jugador(datos[1], seleccionarcolor(datos[1]), Xnicial, Yinicial));
                } else if (datos[0].equals("mover")) {
                    String[] datosJugador = datos[1].split(",");
                    jugadores.get(datosJugador[0]).x = Integer.parseInt(datosJugador[1]);
                    jugadores.get(datosJugador[0]).y = Integer.parseInt(datosJugador[2]);
                }

                String[] lista = new String[jugadores.size()];
                int index = 0;
                for (Jugador e: jugadores.values()) {
                    lista[index++] = e.nickname + "," + e.x + "," + e.y;
                }

                for (Despachador e: jugadoresenlinea) {
                    e.send(String.join("#", lista));
                }
            }
        }
    }

    public void send(String inputLine)
    {
        try {
            out.println(inputLine);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Color seleccionarcolor(String seleccionadoColor){
        switch (seleccionadoColor){
            case "rojo":
                return Color.RED;
            case "verde":
                return Color.GREEN;
            case "azul":
                return Color.BLUE;
            default:
                return Color.black;
        }
    }

    public void asignarposicioninicial(int numerojugadores){
        switch (numerojugadores){
            case 1:
                Xnicial=30;
                Yinicial=30;
            break;
            case 2:
                Xnicial=630;
                Yinicial=330;
            break;
        }

    }

}