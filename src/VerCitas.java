import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class VerCitas extends JFrame implements ActionListener {

    JLabel titulo,buscar;
    JTextField txBuscar;
    public static DefaultTableModel modelo= new DefaultTableModel();
    public static JTable tabla;
    JScrollPane scroll;
    public static File infoCita= new File("Imagenes/Cita.txt");
    FileReader leer;
    JButton btnModificar,btnSalir,btnBuscar;
    AgregarCita agregarCita= new AgregarCita();

    //Constructor
    public VerCitas(){
        super("Ver Citas");
        configVentana();
        addComponentes();
        agregarTabla();
    }

    public void configVentana(){
        this.setSize(700,550);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
    }

    //Metodo para agregrar los datos a la tabla al inicio
    public void agregarTabla(){
        try{
            //Para añadir los datos a la tabla se necesitan lo siguente
            //Para leer lo que hay en el archivo
                leer= new FileReader(infoCita);
                BufferedReader bf= new BufferedReader(leer);

            String informacion= bf.readLine();

            while (informacion!=null){
                String [] rwofields= informacion.split(",");
                VerCitas.modelo.addRow(rwofields);
                informacion=bf.readLine();
            }
            bf.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al guardar datos");
        }
    }

    public void addComponentes(){

        titulo= new JLabel("Medicos Presentes en la Clinca");
        titulo.setBounds(200,40,290,28);
        titulo.setFont(new Font("Bodoni MT",Font.BOLD,20));

        buscar= new JLabel("Buscar");
        buscar.setBounds(50,90,60,20);
        buscar.setFont(new Font("Calibri",Font.BOLD,15));

        txBuscar= new JTextField();
        txBuscar.setBounds(120,90,300,20);

        //Esto sirve para que no se pueda editar la columna
        modelo= new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column<1){
                    return true;
                }else{
                    return false;
                }
            }
        };

        modelo.addColumn("Doctor");
        modelo.addColumn("Especialidad");
        modelo.addColumn("Pacientes");
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora");

        tabla= new JTable(modelo);
        scroll= new JScrollPane(tabla);
        scroll.setBounds(50,150,600,250);


        btnModificar= new JButton("Modificar");
        btnModificar.setBounds(370,440,100,30);
        btnModificar.setFont(new Font("Calibri",Font.BOLD,15));
        btnModificar.setBackground(new Color( 241, 234 ,231));


        btnSalir= new JButton("Salir");
        btnSalir.setBounds(490,440,90,27);
        btnSalir.setFont(new Font("Calibri",Font.BOLD,15));
        btnSalir.setBackground(new Color( 241, 234 ,231));


        btnBuscar= new JButton("Buscar");
        btnBuscar.setBounds(450,90,90,27);
        btnBuscar.setFont(new Font("Calibri",Font.BOLD,15));
        btnBuscar.setBackground(new Color( 241, 234 ,231));



        //Se le agregan acciones
        btnModificar.addActionListener(this);
        btnSalir.addActionListener(this);

        this.add(titulo);
        this.add(buscar);
        this.add(txBuscar);
        this.add(scroll);
        this.add(btnModificar);
        this.add(btnSalir);
        this.add(btnBuscar);
        this.getContentPane().setBackground(new Color(246, 242, 239));

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object objeto= actionEvent.getSource();
        if(objeto==btnModificar){
            int arreglo= tabla.getSelectedRow();
            if (arreglo!=-1){
                agregarCita.editarCita(modelo.getValueAt(tabla.getSelectedRow(),0).toString(),
                        modelo.getValueAt(tabla.getSelectedRow(),1).toString(),
                        modelo.getValueAt(tabla.getSelectedRow(),2).toString(),
                        modelo.getValueAt(tabla.getSelectedRow(),3).toString(),
                        modelo.getValueAt(tabla.getSelectedRow(),4).toString());
                agregarCita.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null,"No has seleccionado ningun Medico");
            }
        }
        if (objeto== btnSalir){
            dispose();
        }
    }
}
