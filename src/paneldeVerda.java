import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class paneldeVerda extends Component {
    public static void main(String[] args) {
        JFrame marco = new JFrame("Puntuable AD");
        marco.setSize(600, 400);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setLayout(new BorderLayout(10, 0));
        JTabbedPane pestañas = new JTabbedPane();

        JPanel panel1 = new JPanel();
        JButton btn = new JButton("Buscar...");
        JButton btn2 = new JButton("Crear");
        JButton btn3 = new JButton("Mostrar");
        JButton btn4 = new JButton("Borrar");
        panel1.add(btn);
        panel1.add(btn2);
        panel1.add(btn3);
        panel1.add(btn4);


        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        JTextField txt = new JTextField(20);
        JTextField txt2 = new JTextField(20);
        JTextField txt3 = new JTextField(20);
        JTextField txt4 = new JTextField(20);
        JTextField txt5 = new JTextField(20);
        JLabel lab= new JLabel("Nombre: ");
        JLabel lab2 = new JLabel("Formato: ");
        JLabel lab3 = new JLabel("Ruta:     ");
        JLabel lab4 = new JLabel("Tamaño: ");
        JLabel lab5 = new JLabel("Última modificación: ");
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

        pestañas.addTab("Panel 1",panel1);
        pestañas.addTab("Panel 2",panel2);

        marco.add(pestañas);
        pestañas.add(panel1);
        pestañas.add(panel2);

        marco.setVisible(true);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
                fileChooser.setFileFilter(imgFilter);
                int result = fileChooser.showOpenDialog(marco);

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
