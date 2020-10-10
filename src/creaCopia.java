import java.io.*;

public class creaCopia {

    public void copiando(String original, String direcCop, String nomb) {
        try {
            String extension;
            int index = nomb.lastIndexOf('.');
            extension=nomb.substring(index);
            nomb=nomb.substring(0,index);

            FileInputStream leer = new FileInputStream(original);
            FileOutputStream copia = new FileOutputStream(direcCop+"\\"+nomb+"_copia"+extension);
            BufferedInputStream bufferleer = new BufferedInputStream(leer);
            BufferedOutputStream buffercopia = new BufferedOutputStream(copia);

            int codigo;
            while((codigo=bufferleer.read())!=-1){
                buffercopia.write(codigo);
            }
            bufferleer.close();
            buffercopia.close();
            leer.close();
            copia.close();


        } catch (IOException e) {
            System.out.println("la has liado");
            e.printStackTrace();

        }

    }

}
