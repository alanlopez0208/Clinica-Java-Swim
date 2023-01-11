import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;


public class AgregarCita extends JFrame implements ActionListener {
    //Atributos
    JLabel titulo,pac, esp, med, fecha, horaDisp, dia, disp;
    JComboBox comboPac,comboEsp,comboMed,comboHora;
    JTextField txtFecha;
    JButton btnaAniadir, btnSalir, btnEditar;
    File cita = new File("Imagenes/Cita.txt");
    FileReader leer;
    File nomMed;
    FileWriter escribir;
    BufferedReader bf;
    ArrayList<String> listaTemp;
    String[] listaPac, listaMed, otraLista;
    JCalendar calendario;
    JDateChooser fechaCalendar;
    JButton btnVerDisp;
    SimpleDateFormat sdf;

    //Constructor
    public AgregarCita() {
        super("Agregar Cita");
        configVentana();
        compontentes();
        //pacientes();
    }

    public void configVentana() {
        this.setSize(800, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
    }

    public void compontentes() {

        titulo= new JLabel("Agendar Cita");
        titulo.setBounds(322,30,243,28);
        titulo.setFont(new Font("Bodoni MT",Font.BOLD,20));


        pac = new JLabel("Paciente");
        pac.setBounds(20, 100, 60, 20);
        pac.setFont(new Font("Calibri",Font.BOLD,15));


        comboPac = new JComboBox(pacientes());
        comboPac.setBounds(160, 100, 150, 30);
        comboPac.setFont(new Font("Calibri",Font.BOLD,15));
        comboPac.setBackground(new Color( 241, 234 ,231));


        esp = new JLabel("Especialidad");
        esp.setBounds(20, 140, 150, 30);
        esp.setFont(new Font("Calibri",Font.BOLD,15));

        String[] especilidades = {"<Selecciona>", "Ginecologia", "Dermatologia", "Nutricion"};
        comboEsp = new JComboBox(especilidades);
        comboEsp.setBounds(160, 140, 150, 30);
        comboEsp.setFont(new Font("Calibri",Font.BOLD,15));
        comboEsp.setBackground(new Color( 241, 234 ,231));

        med = new JLabel("Medico: ");
        med.setBounds(20, 180, 80, 20);
        med.setFont(new Font("Calibri",Font.BOLD,15));
        med.setBackground(new Color( 241, 234 ,231));

        comboMed = new JComboBox();
        comboMed.setEnabled(false);
        comboMed.setBounds(160, 180, 150, 30);
        comboMed.setFont(new Font("Calibri",Font.BOLD,15));
        comboMed.setBackground(new Color( 241, 234 ,231));


        fecha = new JLabel("Seleccione la fecha de la cita en los dias ");
        fecha.setBounds(400, 90, 260, 20);
        fecha.setFont(new Font("Calibri",Font.BOLD,15));
        fecha.setBackground(new Color( 241, 234 ,231));

        //Se agrega el JDate Chosser
        calendario = new JCalendar();
        calendario.setBounds(370, 121, 300, 200);
        comboPac.setBackground(new Color( 241, 234 ,231));

        btnVerDisp = new JButton("Ver Disponibilidad");
        btnVerDisp.setBounds(440, 340, 150, 27);
        btnVerDisp.setFont(new Font("Calibri",Font.BOLD,15));
        btnVerDisp.setBackground(new Color( 241, 234 ,231));
        //btnVerDisp.setEnabled(false);

        String formato = "dd/MM/yyyy";
        sdf = new SimpleDateFormat(formato);

        //Se agrega el choser para cambiar el formato UwU

        dia = new JLabel("El dia");
        dia.setBounds(20, 260, 40, 20);
        dia.setFont(new Font("Calibri",Font.BOLD,15));
        dia.setVisible(false);

        txtFecha = new JTextField(sdf.format(calendario.getDate()));
        txtFecha.setBounds(80, 260, 130, 27);
        txtFecha.setVisible(false );
        txtFecha.setEditable(false);

        disp = new JLabel("Esta disponible");
        disp.setBounds(212, 264, 120, 21);
        disp.setFont(new Font("Calibri",Font.BOLD,15));
        disp.setVisible(false);

        horaDisp = new JLabel("Hora Disponible");
        horaDisp.setBounds(20, 310, 100, 20);
        horaDisp.setFont(new Font("Calibri",Font.BOLD,15));
        horaDisp.setVisible(false);

        comboHora = new JComboBox();
        comboHora.setBounds(160, 308, 150, 30);
        comboHora.addItem("<Seleccionar>");
        comboHora.setEnabled(false);
        comboHora.setFont(new Font("Calibri",Font.BOLD,15));
        comboHora.setBackground(new Color( 241, 234 ,231));
        comboHora.setVisible(false);

        btnaAniadir = new JButton("Añadir");
        btnaAniadir.setBounds(490, 410, 100, 27);
        btnaAniadir.setFont(new Font("Calibri",Font.BOLD,15));
        btnaAniadir.setBackground(new Color( 241, 234 ,231));


        btnSalir = new JButton("Salir");
        btnSalir.setBounds(620, 410, 80, 30);
        btnSalir.setFont(new Font("Calibri",Font.BOLD,15));
        btnSalir.setBackground(new Color( 241, 234 ,231));

        btnEditar= new JButton("Editar");
        btnEditar.setBounds(490,410,100,27);
        btnEditar.setVisible(false);
        btnEditar.setFont(new Font("Calibri",Font.BOLD,15));
        btnEditar.setBackground(new Color( 241, 234 ,231));

        //Se añaden las acciones
        comboEsp.addActionListener(this);
        comboMed.addActionListener(this);
        btnVerDisp.addActionListener(this);
        btnaAniadir.addActionListener(this);
        btnSalir.addActionListener(this);
        btnEditar.addActionListener(this);


        //Se añaden al frame
        this.add(titulo);
        this.add(pac);
        this.add(comboPac);
        this.add(esp);
        this.add(comboEsp);
        this.add(med);
        this.add(comboMed);
        this.add(fecha);
        this.add(calendario);
        this.add(horaDisp);
        this.add(comboHora);
        this.add(dia);
        this.add(txtFecha);
        this.add(disp);
        this.add(btnVerDisp);
        this.add(btnaAniadir);
        this.add(btnEditar);
        this.add(btnSalir);
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


    public static  void actualizarTabla(DefaultTableModel tabla, int fila, String [] arreglo){

        for (int i=0;i<tabla.getColumnCount(); i++){
            tabla.setValueAt(arreglo[i],fila,i);
        }
    }

    public void editarCita(String medico,String especialidad,String pacientes, String Fecha,
                                String Hora){

        for (int i=0;i<comboMed.getMaximumRowCount();i++){
            if(medico.equals(comboMed.getItemAt(i))){
                comboMed.setSelectedIndex(i);
            }
        }

        for (int i=0;i<comboEsp.getMaximumRowCount();i++){
            if(especialidad.equals(comboEsp.getItemAt(i))){
                comboEsp.setSelectedIndex(i);
            }
        }

        for (int i=0;i<comboPac.getMaximumRowCount();i++){
            if(pacientes.equals(comboMed.getItemAt(i))){
                comboPac.setSelectedIndex(i);
            }
        }

        txtFecha.setText(Fecha);

        for (int i=0;i<comboHora.getMaximumRowCount();i++){
            if(Hora.equals(comboHora.getItemAt(i))){
                comboHora.setSelectedIndex(i);
            }
        }

        btnEditar.setVisible(true);
        btnaAniadir.setVisible(false);
    }


    //Metodo para el combox de los pacientes
    public String[] pacientes() {
        try {
            cita = new File("Imagenes/infoPacientes.txt");
            leer = new FileReader(cita);
            bf = new BufferedReader(leer);
            listaTemp = new ArrayList<String>();
            String lect2 = bf.readLine();
            while (lect2 != null) {
                String[] rwofields = lect2.split(",");
                listaTemp.add(rwofields[0]);
                lect2 = bf.readLine();
            }
            listaPac = new String[listaTemp.size() + 1];
            listaPac[0] = "<Selecciona>";
            for (int i = 0; i < listaTemp.size(); i++) {
                listaPac[i + 1] = listaTemp.get(i);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return listaPac;
    }
        @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object objeto=actionEvent.getSource();

        if (objeto ==btnVerDisp){
            if(comboMed.getSelectedIndex()!=0 && comboMed.getSelectedIndex()!=-1){
                try {
                    txtFecha.setText(sdf.format(calendario.getDate()));
                    comboHora.removeAllItems();
                    comboHora.addItem("<Selecciona>");
                    //Primero tenemos que buscar el nombre seleccionado en el archivo de texto seleccionado
                    leer = new FileReader("Imagenes/Horario.txt");
                    bf = new BufferedReader(leer);

                    //Enseguida vamos a buscar el nombre que fue seleccionado
                    String [] dias={"Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"};

                    String buscamiento= bf.readLine();

                    //Primero hago la lecutra del archivo
                    while (buscamiento!=null) {
                        //Aqui hago un  arreglo
                        String[] arreglo = buscamiento.split(",");
                        //Se debe de encontrar el nombre despues de eso
                        if (arreglo[0].equals(comboMed.getSelectedItem().toString())){
                            //Se va a hacer un recorrdio del arreglo hasta llegar al dia indicado
                            int i=0;
                            while(i<arreglo.length){
                                if(arreglo[i].equals(dias[calendario.getDate().getDay()])){
                                    //Despues de que se descubra que dia es el que tanto jodido quiera la cita
                                    //Se pondran las horas en el JCombox
                                    disp.setVisible(true);
                                    disp.setText("Esta disponible");
                                    disp.setForeground(new Color(104, 122, 100));
                                    dia.setVisible(true);
                                    txtFecha.setVisible(true);
                                    comboHora.setVisible(true);
                                    horaDisp.setVisible(true);
                                    int incial=(Integer.parseInt(arreglo[i+1]));
                                    int fin=(Integer.parseInt(arreglo[i+2]));
                                    int contador =fin-incial;
                                    comboHora.addItem(incial+":00");
                                    for (int x=1;x<=contador;x++){
                                        comboHora.addItem(incial+x+":00");
                                    }
                                    comboHora.setEnabled(true);
                                    return;
                                }
                                i++;
                            }
                            if (i==(arreglo.length)){
                                disp.setVisible(true);
                                disp.setText("No esta disponible");
                                dia.setVisible(true);
                                txtFecha.setVisible(true);
                                comboHora.setVisible(true);
                                horaDisp.setVisible(true);
                                disp.setForeground(new Color(225 ,146, 81));
                                comboHora.setEnabled(false);
                            }
                        }
                        buscamiento=bf.readLine();
                    }
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,e.getLocalizedMessage());
                }
            }else{
                JOptionPane.showMessageDialog(null,"No has seleccionado Ningun Medico ");
            }
        }
        if(objeto==comboEsp){
            if (comboEsp.getSelectedIndex()!=0){
                try {
                    //Se crea para escribir
                    cita = new File("Imagenes/infoDocs.txt");
                    leer = new FileReader(cita);
                    bf = new BufferedReader(leer);
                    listaTemp = new ArrayList<String>();

                    String lect2=bf.readLine();
                    comboMed.removeAllItems();
                    comboMed.addItem("<Selecciona >");
                    if(comboEsp.getSelectedIndex()!=0){
                        while (lect2 != null) {
                            String[] rwofields = lect2.split(",");
                            if (rwofields[3].toString().equals(Objects.requireNonNull(comboEsp.getSelectedItem()).toString())){
                                comboMed.addItem(rwofields[1]);
                            }
                            lect2 = bf.readLine();
                        }
                    }
                }catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                comboMed.setEnabled(true);
            }else{
                comboMed.setEnabled(false);
            }
        }
        if (objeto==btnaAniadir){
            if((comboPac.getSelectedIndex()!=0)&&(comboEsp.getSelectedIndex()!=0)&&(comboMed.getSelectedIndex()!=0)&&(comboHora.getSelectedIndex()!=0)){
                try {
                    cita = new File("Imagenes/Cita.txt");
                    escribir = new FileWriter(cita);

                    //Se van a escribir en la tabla
                    escribir.write(comboMed.getSelectedItem().toString()+","+comboEsp.getSelectedItem().toString()+","+
                            comboPac.getSelectedItem().toString()+","+sdf.format(calendario.getDate())+","+comboHora.getSelectedItem().toString()+"\n");
                    escribir.close();

                    //Se añaden las cosas
                    VerCitas.modelo.addRow(new Object[]{comboMed.getSelectedItem().toString(),comboEsp.getSelectedItem().toString(),
                            comboPac.getSelectedItem().toString(),sdf.format(calendario.getDate()),comboHora.getSelectedItem().toString()});

                    JOptionPane.showMessageDialog(null,"Se ha añadido la cita de "+comboPac.getSelectedItem().toString()+
                            " con el doctor "+comboMed.getSelectedItem().toString()+" con exito");

                    //Vuelve todo al estado natural UwU Como yo
                    comboPac.setSelectedIndex(0);
                    comboEsp.setSelectedIndex(0);
                    comboMed.setSelectedIndex(0);
                    comboHora.setSelectedIndex(0);
                    calendario.setDate(new Date());
                    txtFecha.setText(sdf.format(calendario.getDate()));
                    comboMed.setEnabled(false);
                    comboHora.setEnabled(false);


                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,e);
                }
            }else{
                JOptionPane.showMessageDialog(null,"Necesitas seleccionar todos los compoentes");
            }
        }
        if (objeto==btnEditar){
            if(comboMed.getSelectedIndex()!=0 && comboMed.getSelectedIndex()!=-1){
                try {
                    txtFecha.setText(sdf.format(calendario.getDate()));
                    String arregloNuevo= comboMed.getSelectedItem().toString()+","+comboEsp.getSelectedItem().toString()+
                            ","+comboPac.getSelectedItem().toString()+","+txtFecha.getText()+","+comboHora.getSelectedItem().toString();

                    String arregloViejo= VerCitas.modelo.getValueAt(VerCitas.tabla.getSelectedRow(),0)+","+
                         VerCitas.modelo.getValueAt(VerCitas.tabla.getSelectedRow(),1)+","+
                         VerCitas.modelo.getValueAt(VerCitas.tabla.getSelectedRow(),2)+","+
                           VerCitas.modelo.getValueAt(VerCitas.tabla.getSelectedRow(),3)+","+
                            VerCitas.modelo.getValueAt(VerCitas.tabla.getSelectedRow(),4);

                    JOptionPane.showMessageDialog(null,arregloNuevo);
                    String [] arregloTemp = new String[5];
                    arregloTemp[0]=comboMed.getSelectedItem().toString();
                    arregloTemp[1]=comboEsp.getSelectedItem().toString();
                    arregloTemp[2]=comboPac.getSelectedItem().toString();
                    arregloTemp[3]= txtFecha.getText();
                    arregloTemp[4]= comboHora.getSelectedItem().toString();

                   modificar(cita,arregloViejo,arregloNuevo);
                   actualizarTabla(VerCitas.modelo,VerCitas.modelo.getRowCount()-1, arregloTemp);
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,e.getLocalizedMessage());
                }
            }
        }
        if (objeto== btnSalir){
            if((comboPac.getSelectedIndex()!=0)||(comboEsp.getSelectedIndex()!=0)||(comboMed.getSelectedIndex()!=0)||(comboHora.getSelectedIndex()!=0)){
                int opcion =JOptionPane.showConfirmDialog(null,"Tienes Datos por Guardar\n¿Estas seguro que deseas salir?",
                        "Datos escritos",JOptionPane.YES_NO_OPTION);
                if(opcion==JOptionPane.YES_NO_OPTION){
                    dispose();
                    comboPac.setSelectedIndex(0);
                    comboEsp.setSelectedIndex(0);
                    //comboMed.setSelectedIndex(0);
                    comboHora.setSelectedIndex(0);
                }
            }else{
                dispose();
                comboPac.setSelectedIndex(0);
                comboEsp.setSelectedIndex(0);
                //comboMed.setSelectedIndex(0);
                comboHora.setSelectedIndex(0);
            }
        }
    }
}//Fin de la clase
