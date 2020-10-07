import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RMISecurityManager;

public class creaTexto {

public String creandoTexto(String nombre, String direccion,String texto) {
    String direc = "";
    try {
        FileWriter escritor = new FileWriter(direccion + "\\" + nombre + ".txt");
        direc=(direccion + "\\" + nombre + ".txt");
        BufferedWriter buffescritor = new BufferedWriter(escritor);
        buffescritor.write(texto);

        buffescritor.close();
        escritor.close();


    } catch (IOException e) {
    
    }
    return  direc;
}

}
