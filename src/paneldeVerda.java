import javax.swing.*;
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
        JFrame marco = new JFrame();
        marco.setSize(400, 310);
        marco.setVisible(true);
        JTextField txt;
        JTextField txt2;
        JTextField txt3;
        JTextField txt4;
        JTextField txt5;
        JLabel lab;
        JLabel lab2;
        JLabel lab3;
        JLabel lab4;
        JLabel lab5;
        JButton btn;
        JButton btn2;
        JButton btn3;
        JButton btn4;
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panelaux = new JPanel();
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
        panel1.add(btn);
        panel1.add(btn2);
        panel1.add(btn3);
        panel1.add(btn4);
        marco.add(panel1);
        marco.add(panelaux);
        panelaux.add(panel2);
        panel1.setVisible(true);
        panel2.setVisible(false);
        btn.addActionListener(new ActionListener(){
            @Override
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
                panel1.setVisible(false);
                panel2.setVisible(true);
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
