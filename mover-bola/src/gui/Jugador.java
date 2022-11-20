package gui;

import java.awt.*;
import java.nio.file.attribute.UserDefinedFileAttributeView;

public class Jugador {
    public Color login;
    public String nickname;
    public int x;
    public int y;

    public Jugador(String nick, Color log, int x, int y){
        nickname = nick;
        login = log;
        this.x = x;
        this.y = y;
    }

}
