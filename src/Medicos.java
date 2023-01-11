//Aqui es donde se abriba la ventna de los medicos

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Medicos extends JFrame implements ActionListener {

    ArrayList lista = new ArrayList();
    JLabel nombres;
    JTextField txtNombres;
    JLabel apellidos, titulo;
    JTextField txtApellidos;
    JLabel especialidad;
    JComboBox comboEspecialidad;
    JButton btnModificarHorario, btnagr, btnsalr,btnGuardar;
    FileWriter escribir,escribir2,escribir3;
    File infoDocs= new File("Imagenes/InfoDocs.txt");
    FileReader fileReader=null;
    BufferedReader leer=null;

    Horario horario = new Horario();



    //Constructor de los medicos
    public Medicos() {
        super("Agregar Medico");
        configVentana();
        elementos();
    }


    public void configVentana() {
        this.setSize(500, 350);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
    }

    public void elementos() {

        titulo= new JLabel("Añadir Medico");
        titulo.setBounds(190,40,200,28);
        titulo.setFont(new Font("Bodoni MT",Font.BOLD,20));


        nombres = new JLabel("Nombres: ");
        nombres.setBounds(50, 103, 80, 40);
        nombres.setFont(new Font("Calibri",Font.BOLD,15));


        txtNombres = new JTextField();
        txtNombres.setBounds(150, 100, 260, 30);

        apellidos = new JLabel("Apellidos:");
        apellidos.setBounds(50, 146, 80, 40);
        apellidos.setFont(new Font("Calibri",Font.BOLD,15));


        txtApellidos = new JTextField();
        txtApellidos.setBounds(150, 143, 260, 30);

        especialidad = new JLabel("Especialidad: ");
        especialidad.setBounds(50, 188, 264, 40);
        especialidad.setFont(new Font("Calibri",Font.BOLD,15));


        String[] especilidades = {"---------------", "Ginecologia", "Dermatologia", "Nutricion"};

        comboEspecialidad = new JComboBox(especilidades);
        comboEspecialidad.setBounds(151, 185, 264, 27);
        comboEspecialidad.setFont(new Font("Calibri",Font.BOLD,15));
        comboEspecialidad.setBackground(new Color( 241, 234 ,231));
        comboEspecialidad.setFont(new Font("Calibri",Font.BOLD,15));


        btnagr = new JButton("Agregar Horario");
        btnagr.setBounds(180, 260, 150, 27);
        btnagr.setFont(new Font("Calibri",Font.BOLD,15));
        btnagr.setBackground(new Color( 241, 234 ,231));


        btnModificarHorario= new JButton("Modificar Horario");
        btnModificarHorario.setBounds(180,260,150,27);
        btnModificarHorario.setVisible(false);
        btnModificarHorario.setFont(new Font("Calibri",Font.BOLD,15));
        btnModificarHorario.setBackground(new Color( 241, 234 ,231));



        btnsalr = new JButton("Salir");
        btnsalr.setBounds(360, 260, 80, 27);
        btnsalr.setFont(new Font("Calibri",Font.BOLD,15));
        btnsalr.setBackground(new Color( 241, 234 ,231));



        btnGuardar= new JButton("Guardar");
        btnGuardar.setBounds(50, 260, 100, 27);
        btnGuardar.setVisible(false);
        btnGuardar.setFont(new Font("Calibri",Font.BOLD,15));
        btnGuardar.setBackground(new Color( 241, 234 ,231));



        //Se añaden las acciones a los botones
        btnModificarHorario.addActionListener(this);
        btnagr.addActionListener(this);
        btnsalr.addActionListener(this);
        btnGuardar.addActionListener(this);

        this.add(titulo);
        this.add(nombres);
        this.add(txtNombres);
        this.add(apellidos);
        this.add(txtApellidos);
        this.add(especialidad);
        this.add(comboEspecialidad);
        this.add(btnagr);
        this.add(btnsalr);
        this.add(btnModificarHorario);
        this.add(btnGuardar);
        this.add(btnModificarHorario);
        this.getContentPane().setBackground(new Color(246, 242, 239));
    }


    public static void Escribir(File fFichero,String cadena)
    {
        // Declaramos un buffer de escritura
        BufferedWriter bw;
        try
        {
            // Comprobamos si el archivo no existe y si es asi creamos uno nuevo.
            if(!fFichero.exists())
            {
                fFichero.createNewFile();
            }
            // Luego de haber creado el archivo procedemos a escribir dentro de el.
            bw = new BufferedWriter(new FileWriter(fFichero,true));
            bw.write(cadena);
            bw.write("\n");
            bw.close();
        }catch(Exception e)
        {
            System.out.println(e);
        }

    }

    public static void borrar (File Ffichero)
    {
        try
        {
            // Comprovamos si el fichero existe  de ser así procedemos a borrar el archivo
            if(Ffichero.exists())
            {
                Ffichero.delete();
                System.out.println("Ficherro Borrado");
            }

        }catch(Exception e)
        {
            System.out.println(e);
        }
    }


    public static void modificar(File fAntiguo,String aCadena,String nCadena)
    {
       /*
            Las dos lienas de codigo siguientes, basicamente lo que hacen es generar un numero aleatorio y
            asignarselos a una nueva variable "nFnuevo" (Nombre Fichero Nuevo) la cual es igual a la ruta
            del directorio padre "fAntiguo" mas  la palabra auxiliar seguido del numero aletorio y la extension
            del archivo nuevo
       * */
        Random numaleatorio = new Random(3816L);
        String nFnuevo = fAntiguo.getParent()+"/auxiliar"+String.valueOf(Math.abs(numaleatorio.nextInt()))+".txt";

        // Creo un nuevo archivo
        File fNuevo= new File(nFnuevo);
        // Declaro un nuevo buffer de lectura
        BufferedReader br;
        try
        {
            /*Valido si el fichero antiguo que pasamos como parametro existe, si es asi procedo a leer el
            contenido que hay dentro de el
             */
            if(fAntiguo.exists())
            {
                br = new BufferedReader(new FileReader(fAntiguo));

                String linea;
                /* Mientras el contenido del archivo sea diferente de null procedo a comprar  la linea a modificar con
                lo que hay dentro del archivo, si linea es igual a aCadena escribo el contenido de aCadena en mi nuevo
                fichero(Auxiliar) de lo contrario escribo el mismo contenido que ya tenia el antiguo fichero en mi fichero auxiliar

                 */
                while((linea=br.readLine()) != null)
                {
                    if(linea.equals(aCadena))
                    {
                        Escribir(fNuevo,nCadena);

                    }
                    else
                    {
                        Escribir(fNuevo,linea);
                    }
                }

                // Cierro el buffer de lectura
                br.close();

                // Capturo el nombre del fichero antiguo
                String nAntiguo = fAntiguo.getName();
                // Borro el fichero antiguo
                borrar(fAntiguo);
                //Renombro el fichero auxiliar con el nombre del fichero antiguo
                fNuevo.renameTo(fAntiguo);
            }
            else
            {
                System.out.println("Fichero no Existe");
            }

        }catch(Exception e)
        {
            System.out.println(e);
        }
    }


    public void editarMedicos(String nombre,String apelldos,String especialidad){
        txtNombres.setText(nombre);
        txtApellidos.setText(apelldos);
        for (int i=0;i<comboEspecialidad.getMaximumRowCount();i++){
            if(especialidad.equals(comboEspecialidad.getItemAt(i))){
                comboEspecialidad.setSelectedIndex(i);
            }
        }
        btnModificarHorario.setVisible(true);
        btnGuardar.setVisible(true);
        btnagr.setVisible(false);
    }

    public static  void actualizarTabla(DefaultTableModel tabla,int fila,String [] arreglo){

        for (int i=0;i<tabla.getColumnCount(); i++){
            tabla.setValueAt(arreglo[i],fila,i);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        Object objeto = evento.getSource();

        if (objeto == btnModificarHorario) {
            horario.setVisible(true);
        }

        if (objeto == btnsalr) {
            if ((!txtNombres.getText().isEmpty()) || (!txtApellidos.getText().isEmpty()) || (comboEspecialidad.getSelectedIndex() != 0)) {
                int opcion =JOptionPane.showConfirmDialog(null,"Tienes Datos por Guardar\n¿Estas seguro que deseas salir?",
                        "Datos escritos",JOptionPane.YES_NO_OPTION);
                if(opcion==JOptionPane.YES_NO_OPTION){
                    dispose();
                    txtNombres.setText(null);
                    txtApellidos.setText(null);
                    comboEspecialidad.setSelectedIndex(0);
                }
            }else{
                dispose();
            }
        }
        if (objeto == btnGuardar) {
            if ((!txtNombres.getText().isEmpty()) && (!txtApellidos.getText().isEmpty()) && (comboEspecialidad.getSelectedIndex() != 0)) {
                try {

                    String arregloVijeo = (VerMedicos.modelo.getRowCount()) + "," + VerMedicos.modelo.getValueAt(VerMedicos.tabla.getSelectedRow(), 1).toString() + ","
                            + VerMedicos.modelo.getValueAt(VerMedicos.tabla.getSelectedRow(), 2).toString() +
                            "," + VerMedicos.modelo.getValueAt(VerMedicos.tabla.getSelectedRow(), 3).toString();

                    String[] arregloTemp = new String[4];
                    arregloTemp[0] = Integer.toString((VerMedicos.modelo.getRowCount()));
                    arregloTemp[1] = txtNombres.getText();
                    arregloTemp[2] = txtApellidos.getText();
                    arregloTemp[3] = comboEspecialidad.getSelectedItem().toString();

                    Object[] columna = new Object[]{(VerMedicos.modelo.getRowCount() + 1), txtNombres.getText(), txtApellidos.getText(), comboEspecialidad.getSelectedItem().toString()};
                    String arregloNuevo = VerMedicos.modelo.getRowCount() + "," + txtNombres.getText() + "," + txtApellidos.getText() + "," +
                            comboEspecialidad.getSelectedItem().toString();

                    modificar(infoDocs, arregloVijeo, arregloNuevo);
                    actualizarTabla(VerMedicos.modelo, VerMedicos.modelo.getRowCount() - 1, arregloTemp);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "No se puede Añadir medico por que faltan Datos");
            }
        }
        if (objeto == btnagr) {
            if ((!txtNombres.getText().isEmpty()) && (!txtApellidos.getText().isEmpty()) && (comboEspecialidad.getSelectedIndex() != 0)) {
                //Creando la Matriz de la tabla
                try {
                    escribir = new FileWriter("Imagenes/Temporal.txt", true);

                    escribir.write(txtNombres.getText() + "," + txtApellidos.getText() + "," + comboEspecialidad.getSelectedItem().toString() + "\n");
                    //String  arregloNuevo= (VerMedicos.modelo.getRowCount()+1)+","+txtNombres.getText()+","+txtApellidos.getText()
                    //       +","+comboEspecialidad.getSelectedItem();
                    //JOptionPane.showMessageDialog(null,arregloNuevo);

                    //Escribir(infoDocs,arregloNuevo);
                    //Object [] columna= new Object[]{ (VerMedicos.modelo.getRowCount()+1),txtNombres.getText(),txtApellidos.getText(),comboEspecialidad.getSelectedItem().toString()};
                    //VerMedicos.modelo.addRow(columna);
                    horario.setVisible(true);
                    escribir.close();
                    txtNombres.setText(null);
                    txtApellidos.setText(null);
                    comboEspecialidad.setSelectedIndex(0);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se puede Añadir medico por que faltan Datos");
            }
        }
    }
}//Fin de la clase de medico