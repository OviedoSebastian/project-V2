package levels;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Leer_niveles {
    private static File level;
    public static int [][] buildmaze(){
        String thisLine ="";
        int lectorlinea =0;
        int [][]maze = new int[13][23];

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/levels/level1.txt"));

            while ((thisLine = br.readLine()) != null) {

                for(int idx=0;idx<thisLine.length();idx++){
                    int temp = Character.getNumericValue(thisLine.charAt(idx));
                    if(temp ==1 || temp==0){
                        maze[lectorlinea][idx]= temp;
                    }
                }
                lectorlinea++;
            }
        } catch(IOException e) {
            System.out.println("No se encontro el archivo");
        }
        return maze;
    }

}
