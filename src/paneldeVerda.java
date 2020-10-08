import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.nio.file.Files.delete;

public class paneldeVerda extends Component {
    private static String dir;

    public static void main(String[] args) {
        JFrame marco = new JFrame("Puntuable AD");

        marco.setSize(600, 400);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setLayout(new BorderLayout(10, 0));
        JTabbedPane pestañas = new JTabbedPane();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        JPanel panel1 = new JPanel();
        JPanel crear = new JPanel();
        JButton btn = new JButton("Buscar...");
        JButton btn2 = new JButton("Crear");
        JButton btn3 = new JButton("Mostrar");
        JButton btn4 = new JButton("Borrar");
        JButton btn5 = new JButton("Crea Texto");
        panel1.add(btn);
        panel1.add(btn2);


        JPanel panel2 = new JPanel();
        JPanel panel21 = new JPanel();
        JPanel panel22 = new JPanel();
        JPanel panel23 = new JPanel();
        panel2.setLayout(new GridLayout(3,1));
        panel21.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel22.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel23.setLayout(new GridLayout(2,1));
        JTextField txt = new JTextField(20);
        JTextField txt2 = new JTextField(20);
        JTextField txt3 = new JTextField(20);
        JTextField txt4 = new JTextField(20);
        JTextField txt5 = new JTextField(20);
        JTextArea contenido_texto= new JTextArea();
        JTextArea texto_fichero = new JTextArea();

        JTextField nombrearchivo = new JTextField(25);
        JLabel lab= new JLabel("Nombre: ");
        JLabel lab2 = new JLabel("Formato: ");
        JLabel lab3 = new JLabel("Ruta:     ");
        JLabel lab4 = new JLabel("Tamaño: ");
        JLabel lab5 = new JLabel("Última modificación: ");
        JLabel nombre_archiv_crear = new JLabel("Nombre del archivo");
        JLabel nombre_texto_fichero = new JLabel("Contenido del fichero");
        texto_fichero.setPreferredSize(new Dimension(300,200));
        panel21.add(lab);
        panel21.add(txt);
        panel21.add(lab2);
        panel21.add(txt2);
        panel21.add(lab3);
        panel21.add(txt3);
        panel21.add(lab4);
        panel21.add(txt4);
        panel21.add(lab5);
        panel21.add(txt5);
        panel22.add(btn3);
        panel22.add(btn4);
        panel23.add(nombre_texto_fichero);
        panel23.add(texto_fichero);

        panel2.add(panel21);
        panel2.add(panel22);
        panel2.add(panel23);

        pestañas.addTab("Panel 1",panel1);
        pestañas.addTab("Panel 2",panel2);
        panel1.add(panel3);
        panel1.add(panel4);
        panel4.add(panel5);



        marco.add(pestañas);

        contenido_texto.setPreferredSize(new Dimension(300,200));

        panel3.add(nombre_archiv_crear);
        panel3.add(nombrearchivo);
        panel5.add(btn5);
        panel4.add(contenido_texto);
        marco.setVisible(true);
        panel3.setVisible(false);
        panel4.setVisible(false);
        panel5.setVisible(false);
        //buscar,borrar y mostrar
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int result = fileChooser.showOpenDialog(marco);

                if (result == JFileChooser.APPROVE_OPTION) {

                    File fileName = fileChooser.getSelectedFile();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    String direccion = fileName.getAbsolutePath();
                    txt.setText(fileName.getName());
                    txt2.setText(sacaExtension(fileName));
                    txt3.setText(fileName.getAbsolutePath());
                    txt4.setText(String.valueOf((fileName.length()/1000)+"KB"));
                    txt5.setText(dateFormat.format(fileName.lastModified()));
                    btn4.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
                            File directorio = new File(fileName.getAbsolutePath());
                            try
                            {
                                JOptionPane.showMessageDialog(null, "Has borrado el archivo");
                                directorio.delete();

                            }catch(Exception ex){
                                System.out.println(ex);
                            }
                        }
                    });
                    btn3.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            File directorio = new File(fileName.getAbsolutePath());
                            String codigo = "";
                            FileReader fr = null;
                            BufferedReader entrada = null;
                            try {
                                fr = new FileReader(directorio);
                                entrada = new BufferedReader(fr);

                                while(entrada.ready()){
                                    codigo += entrada.readLine();
                                }

                            }catch(IOException e1) {
                                e1.printStackTrace();
                            }finally{
                                try{
                                    if(null != fr){
                                        fr.close();
                                    }
                                }catch (Exception e2){
                                    e2.printStackTrace();
                                }
                            }
                            texto_fichero.setText(codigo);
                        }
                    });
                }
            }
        });
/////crear archivo
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int seleccion = fileChooser.showOpenDialog(crear);

                if (seleccion == JFileChooser.APPROVE_OPTION)
                {
                    File directorio = fileChooser.getSelectedFile();

                    panel3.setVisible(true);
                    panel4.setVisible(true);
                    panel5.setVisible(true);

                    btn5.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e) {
                            String nombre = nombrearchivo.getText();
                            String direccion = directorio.getAbsolutePath();
                            String texto=contenido_texto.getText();

                            creaTexto texto1 = new creaTexto();
                            dir=(texto1.creandoTexto(nombre,direccion,texto));

                            panel3.setVisible(false);
                            panel4.setVisible(false);
                            panel5.setVisible(false);
                        }

                    });

                }

            }
        });
    }



    public static String format(long time) {
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); return sdf.format(new Date(time));
    }
    public static String sacaExtension(File fileName){
        String name = fileName.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf);
    }
}