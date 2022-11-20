package net;
import gui.Tablero;
import gui.VentanaPrincipal;

import java.net.*;
import java.io.*;

public class Cliente {
    public void conectar(VentanaPrincipal gui, Tablero tablero) {
        String hostName = "localhost";
        int portNumber = 1234;//Integer.parseInt(args[1]);

        try {
            Socket kkSocket = new Socket(hostName, portNumber);

            Despachador lector = new Despachador(kkSocket, "lector");//servidor
            lector.gui = gui;
            lector.start();

            Despachador escritor = new Despachador(kkSocket, "escritor");//jugador
            escritor.gui = gui;
            gui.lienzo.despachador = gui.despachador = escritor;
            escritor.start();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}