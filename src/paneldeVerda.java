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
    private static File nombAr;

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
        JButton btn6 = new JButton("Contar palabras");
        JButton btn7 = new JButton("Cifrar archivo");
        JButton btnedita= new JButton("Editar");
        JButton copia = new JButton("Copia");
        JButton cuentaVoc = new JButton("Cuenta Vocales");
        panel1.add(btn);
        panel1.add(btn2);


        JPanel panel2 = new JPanel();
        JPanel panel21 = new JPanel();
        JPanel panel22 = new JPanel();
        JPanel panel23 = new JPanel();
        panel2.setLayout(new GridLayout(3,1));
        panel21.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel22.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel23.setLayout(new FlowLayout(FlowLayout.CENTER));
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
        JLabel norecu= new JLabel("Dale primero a buscar");
        texto_fichero.setPreferredSize(new Dimension(300,400));
        panel1.add(copia);
        panel1.add(norecu);
        norecu.setVisible(false);


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
        panel23.add(btn3);
        panel23.add(btnedita);
        panel23.add(btn4);
        panel23.add(btn6);
        panel23.add(btn7);
        panel23.add(cuentaVoc);
        panel22.add(texto_fichero);

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
        btnedita.setVisible(false);
        //buscar,borrar y mostrar
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int result = fileChooser.showOpenDialog(marco);

                if (result == JFileChooser.APPROVE_OPTION) {

                    File fileName = fileChooser.getSelectedFile();
                    nombAr=fileName;

                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    String direccion = fileName.getAbsolutePath();
                    txt.setText(fileName.getName());
                    txt2.setText(sacaExtension(fileName));
                    txt3.setText(fileName.getAbsolutePath());
                    txt4.setText(String.valueOf((fileName.length()/1024)+"KB"));
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
                    if(fileName.isFile()) {
                        btn3.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                File directorio = new File(fileName.getAbsolutePath());
                                String codigo ="";
                                FileReader fr = null;
                                BufferedReader entrada = null;
                                try {

                                    fr = new FileReader(directorio);
                                    entrada = new BufferedReader(fr);

                                    while ((codigo=entrada.readLine())!=null) {
                                        codigo += entrada.readLine();
                                    }
                                    texto_fichero.setText(codigo);


                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                } finally {
                                    try {
                                        if (null != fr) {
                                            entrada.close();
                                            fr.close();

                                        }
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                }

                                if (!codigo.equals("")) {
                                    btnedita.setVisible(true);
                                }
                            }
                        });
                    }
                    btnedita.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {
                                FileWriter escritor = new FileWriter(fileName.getAbsolutePath());
                                BufferedWriter buff = new BufferedWriter(escritor);

                                String escribo =texto_fichero.getText();
                                buff.write(escribo);
                                buff.close();
                                escritor.close();

                            } catch (IOException e4) {
                                System.out.println("la has liado");
                                e4.printStackTrace();

                            }

                        }
                    });

                    btn6.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            File directorio = new File(fileName.getAbsolutePath());
                            FileReader fr = null;
                            int contador = 0;
                            try {
                                fr = new FileReader(directorio);
                                BufferedReader entrada = new BufferedReader(fr);
                                String cadena = entrada.readLine();
                                while (cadena != null) {
                                    contador = contador + cadena.split("\\s+|\n|,").length;
                                    cadena = entrada.readLine();
                                }
                            } catch (IOException fnfe) {
                                System.out.println(fnfe.getMessage());
                            }
                            JOptionPane.showMessageDialog(null, "El número total de palabras son: " + contador);
                        }
                    });
                    btn7.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            File directorio = new File(fileName.getAbsolutePath());
                            FileReader fr;
                            FileWriter wr;
                            try {
                                fr = new FileReader(directorio);
                                wr = new FileWriter(directorio);
                                BufferedReader entrada = new BufferedReader(fr);
                                BufferedWriter salida = new BufferedWriter(wr);
                                String cadena;
                                cadena = entrada.readLine();
                                while (cadena != null) {
                                    salida.write(cadena + "\n");
                                    cadena = entrada.readLine();
                                }
                                wr.flush();
                                entrada.close();
                                salida.close();
                                fr.close();
                                wr.close();
                            }catch (IOException e2){

                            }
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

/////copiar

        copia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();
                creaCopia copy = new creaCopia();

                if (nombAr != null) {

                    if (nombAr.isFile()) {
                        String nombreAr =JOptionPane.showInputDialog("Introduce nombre del archivo");

                        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        int seleccion = fileChooser.showOpenDialog(marco);
                        if (seleccion == JFileChooser.APPROVE_OPTION) {
                            File directorio2 = fileChooser.getSelectedFile();
                           String nombre = nombAr.getName();
                            String direori = nombAr.getAbsolutePath();
                            String direcop = directorio2.getAbsolutePath();



                            copy.copiando(direori, direcop, nombre,nombreAr);
                        }

                    }



                }
            }
        });
        ///Cuenta vocales
        cuentaVoc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    FileReader lector = new FileReader(nombAr.getAbsolutePath());
                    BufferedReader inBuffer = new BufferedReader(lector);
                    String linea = inBuffer.readLine();
                    int contador=0;

                    while (linea != null) {


                        for (int i = 0; i < linea.length(); i++) {
                            char letra = linea.charAt(i);
                            if (esVocal(letra)) {
                                contador=contador+1;
                            }
                        }
                        linea = inBuffer.readLine();

                    }
                    JOptionPane.showMessageDialog(null, "Las vocales en el texto son: " + contador);
                    inBuffer.close();
                    lector.close();

                } catch (FileNotFoundException e1) {
                e1.printStackTrace();
                 } catch (IOException e2) {
                System.out.println("Error al cerrar el stream");
                }catch(NullPointerException e3)
                {
                    System.out.print("no hay archivos");
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
    public static boolean esVocal(char c) {
        return "aeiouAEIOUáéíóúÁÉÍÓÚüÜ".indexOf(c) > -1;
    }
}