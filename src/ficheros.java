import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ficheros extends JFrame implements ActionListener {
    private JTextField txt;
    private JTextField txt2;
    private JTextField txt3;
    private JTextField txt4;
    private JTextField txt5;
    private JLabel lab;
    private JLabel lab2;
    private JLabel lab3;
    private JLabel lab4;
    private JLabel lab5;
    private JButton btn;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panelaux;
    public ficheros(){
        super("Actividad 4 (FILE)");
        setLayout(new FlowLayout());
        panel1.setSize(300,300);
        panel2.setSize(300,300);
        panel1.setLayout(new FlowLayout());
        panel2.setLayout(new FlowLayout());
        txt = new JTextField(30);
        txt2 = new JTextField(30);
        txt3 = new JTextField(30);
        txt4 = new JTextField(30);
        txt5 = new JTextField(30);
        lab = new JLabel("Nombre: ");
        lab2 = new JLabel("Formato: ");
        lab3 = new JLabel("Ruta:     ");
        lab4 = new JLabel("Tamaño: ");
        lab5 = new JLabel("Última modificación: ");
        panel2.add(lab);
        panel2.add(txt);
        panel2.add(lab2);
        panel2.add(txt2);
        panel2.add(lab3);
        panel2.add(txt3);
        panel2.add(lab4);
        panel2.add(txt4);
        panel2.add(lab5);
        panel2.add(txt5);
        btn = new JButton("Buscar...");
        btn2 = new JButton("Crear");
        btn3 = new JButton("Mostrar");
        btn4 = new JButton("Borrar");
        btn.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        panel1.add(btn);
        panel1.add(btn2);
        panel1.add(btn3);
        panel1.add(btn4);
        add(panel1);
        add(panelaux);
        panelaux.add(panel2);
        panel1.setVisible(true);
        panel2.setVisible(false);
    }
    public static void main(String[] args) {
        ficheros test = new ficheros();
        test.setDefaultCloseOperation(EXIT_ON_CLOSE);
        test.setSize(400, 310);
        test.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
        fileChooser.setFileFilter(imgFilter);

        int result = fileChooser.showOpenDialog(this);

        if (result != JFileChooser.CANCEL_OPTION) {

            File fileName = fileChooser.getSelectedFile();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

            if ((fileName == null) || (fileName.getName().equals(""))) {
                txt.setText("...");
            } else {
                txt.setText(fileName.getName());
                txt2.setText(sacaExtension(fileName));
                txt3.setText(fileName.getAbsolutePath());
                txt4.setText(String.valueOf((fileName.length()/1000)+"KB"));
                txt5.setText(dateFormat.format(fileName.lastModified()));
            }
        }
        panel1.setVisible(false);
        panel2.setVisible(true);
    }
    public static String format(long time) {
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); return sdf.format(new Date(time));
    }
    public String sacaExtension(File fileName){
        String name = fileName.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf);
    }
}
