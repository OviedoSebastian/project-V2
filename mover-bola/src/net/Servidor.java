package net;
import java.net.*;
import java.util.ArrayList;

public class Servidor {
    public static void main(String[] args) {
        int portNumber = 1234;
        ArrayList<Despachador> jugadoresenlinea = new ArrayList<>();
        int numeroJugadores=0;

        try {
                ServerSocket serverSocket = new ServerSocket(portNumber);
                while (true) {
                    System.out.println("ESPERANDO JUGADORES...");
                    Socket clientSocket = serverSocket.accept();
                    numeroJugadores++;
                    Despachador lector = new Despachador(clientSocket, "lector");//jugador
                    lector.asignarposicioninicial(numeroJugadores);
                    lector.start();
                    lector.jugadoresenlinea = jugadoresenlinea;

                    Despachador escritor = new Despachador(clientSocket, "escritor");//servidor
                    jugadoresenlinea.add(escritor);
                    escritor.start();
                    if(numeroJugadores==2){
                        serverSocket.close();
                        System.out.println("NUMERO MAXIMO DE JUGADORES ALCANZADO");
                    }
                }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
