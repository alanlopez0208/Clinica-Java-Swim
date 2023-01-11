import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;


public class AgregPacientes extends JFrame implements ActionListener {

    //Atributos
    JLabel titulo,nom,apell,tel,peso,ed,tipSa,aler,enfer,altura,cm,kg;
    JTextField txNom,txApell,txTel,txPeso,txEd,txTipSa,txtAltu;
    JTextArea txAler,txEnfer;
    JButton btnGuar, btnSalir, btnEditar;
    JComboBox comboTipSan;
    JScrollPane scroll1,scroll2;
    public static File infoPac= new File("Imagenes/infoPacientes.txt");
    FileReader leer;
    FileWriter escribir;
    BufferedReader buffer;

    //Clases
    AgregarCita agregarCita= new AgregarCita();

    //Constructor de la clase;
    public AgregPacientes(){
        super("Agregar Pacientes");
        ajustesVentana();
        elementos();
    }
    public void ajustesVentana(){
        this.setSize(620,700);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
    }

    public void elementos(){

        titulo= new JLabel("Añadir Paciente");
        titulo.setBounds(208,36,243,28);
        titulo.setFont(new Font("Bodoni MT",Font.BOLD,20));

        nom= new JLabel("Nombre: ");
        nom.setBounds(50,80,60,20);

        txNom= new JTextField();
        txNom.setBounds(160,80,275,27);

        apell= new JLabel("Apellidos: ");
        apell.setBounds(50,120,60,20);

        txApell= new JTextField();
        txApell.setBounds(160,120,275,27);

        tel= new JLabel("Telefono: ");
        tel.setBounds(50,160,60,20);

        txTel= new JTextField();
        txTel.setBounds(160,160,275,27);

        peso= new JLabel("Peso: ");
        peso.setBounds(50,200,60,20);

        txPeso= new JTextField();
        txPeso.setBounds(160,200,90,27);

        kg= new JLabel("Kg");
        kg.setBounds(270,200,20,21);

        ed= new JLabel("Edad: ");
        ed.setBounds(50,240,60,20);

        txEd= new JTextField();
        txEd.setBounds(160,240,90,27);

        altura= new JLabel("Altura: ");
        altura.setBounds(50,280,60,20);

        txtAltu= new JTextField();
        txtAltu.setBounds(160,280,90,27);

        cm= new JLabel("Cm");
        cm.setBounds(270,280,20,21);

        tipSa= new JLabel("Tipo de Sangre: ");
        tipSa.setBounds(50,320,100,20);

        String[] vector={"------","O+","O-","A+","A-","B+","B-","AB+","AB-"};
        comboTipSan= new JComboBox(vector);
        comboTipSan.setBounds(160,320,120,30);
        comboTipSan.setBackground(new Color( 241, 234 ,231));


        aler= new JLabel("Alergias: ");
        aler.setBounds(50,380,60,20);

        txAler = new JTextArea();
        //Metodo pata que haga el salto de linea busnod entre espacios de las palabras
        txAler.setWrapStyleWord(true);

        //Para que haga el salto de linea en cualquier parte de la palabra
        txAler.setLineWrap(true);

        //Se añade un ScrollPane a txAler
        scroll1= new JScrollPane(txAler);
        scroll1.setBounds(160,380,270,80);
        scroll1.setBackground(new Color( 241, 234 ,231));


        enfer= new JLabel("Enfermedades: ");
        enfer.setBounds(50,510,100,80);

        txEnfer = new JTextArea();
        //Metodo pata que haga el salto de linea busnod entre espacios de las palabras
        txEnfer.setWrapStyleWord(true);
        //Para que haga el salto de linea en cualquier parte de la palabra
        txEnfer.setLineWrap(true);

        //Se añade el Scroll
        scroll2= new JScrollPane(txEnfer);
        scroll2.setBounds(160,480,270,125);
        scroll2.setBackground(new Color( 241, 234 ,231));


        btnGuar= new JButton("Guardar");
        btnGuar.setBounds(360,620,80,30);
        btnGuar.setBackground(new Color( 241, 234 ,231));


        btnEditar= new JButton("Guardar");
        btnEditar.setBounds(360,620,80,30);
        btnEditar.setVisible(false);
        btnEditar.setBackground(new Color( 241, 234 ,231));

        btnSalir= new JButton("Salir");
        btnSalir.setBounds(480,620,80,30);
        btnSalir.setBackground(new Color( 241, 234 ,231));

        //Se añaden las acciones
        btnGuar.addActionListener(this);
        btnSalir.addActionListener(this);
        btnEditar.addActionListener(this);

        //Se añaden los objetos al frame
        this.add(titulo);
        this.add(nom);
        this.add(txNom);
        this.add(apell);
        this.add(txApell);
        this.add(tel);
        this.add(txTel);
        this.add(peso);
        this.add(txPeso);
        this.add(ed);
        this.add(cm);
        this.add(kg);
        this.add(txEd);
        this.add(altura);
        this.add(txtAltu);
        this.add(tipSa);
        this.add(comboTipSan);
        this.add(aler);
        this.add(scroll1);
        this.add(scroll2);
        this.add(enfer);
        this.add(btnGuar);
        this.add(btnSalir);
        this.add(btnEditar);
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

    public void editarPacientes(String nombre,String apelldos,String telelfono, String peso,
                                String edad, String altura,String tipSangre){
        txNom.setText(nombre);
        txApell.setText(apelldos);
        txTel.setText(telelfono);
        txPeso.setText(peso);
        txEd.setText(edad);
        txtAltu.setText(altura);

        for (int i=0;i<comboTipSan.getMaximumRowCount();i++){
            if(tipSangre.equals(comboTipSan.getItemAt(i))){
                comboTipSan.setSelectedIndex(i);
            }
        }
        btnEditar.setVisible(true);
        btnGuar.setVisible(false);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object objeto=actionEvent.getSource();
        if(objeto == btnGuar){
            if((!txNom.getText().isEmpty())&&(!txApell.getText().isEmpty())&&(!txTel.getText().isEmpty())&&
                    (!txPeso.getText().isEmpty())&&(!txtAltu.getText().isEmpty())&&(!txEd.getText().isEmpty())&&
                    (comboTipSan.getSelectedIndex()!=0)&&(!txAler.getText().isEmpty())&&(!txEnfer.getText().isEmpty())){
                try{
                    long numero = Long.parseLong((txTel).getText());
                    try{
                        long  peso= Integer.parseInt(txPeso.getText());
                        try{
                            int altura= Integer.parseInt((txtAltu.getText()));
                            try{
                                int edad= Integer.parseInt(txEd.getText());
                                try{
                                    //Creamos el objeto para que escriba en el archivo de texto
                                    escribir= new FileWriter(infoPac,true);

                                    //Añadimos la info a el archivo de texto
                                    escribir.write(txNom.getText()+","+txApell.getText()+","+numero+","+peso+","+
                                            altura+","+edad+","+(comboTipSan.getSelectedItem().toString())+"\n");
                                    //Concluimos la escritura
                                    escribir.close();

                                    //Despues se agregan a la tabla
                                    VerPacientes.modelo.addRow(new Object[]{txNom.getText(),txApell.getText()
                                            ,numero,peso,altura,edad,(comboTipSan.getSelectedItem().toString())});
                                    //agregarCita.setVisible(true);
                                    txNom.setText(null);
                                    txApell.setText(null);
                                    txTel.setText(null);
                                    txPeso.setText(null);
                                    txEd.setText(null);
                                    txtAltu.setText(null);
                                    txAler.setText(null);
                                    txEnfer.setText(null);
                                    JOptionPane.showMessageDialog(null,"Datos Guardados con Extito");

                                }catch (Exception e){
                                    JOptionPane.showMessageDialog(null,e);
                                }
                            }catch (Exception e){
                                JOptionPane.showMessageDialog(null,"Solo debes de ingresar numeros en la edad");
                            }
                        }catch (Exception e){
                            JOptionPane.showMessageDialog(null,e);
                        }
                    }catch (Exception e){
                        JOptionPane.showMessageDialog(null,"Ingrese solo numeros para el peso");
                    }
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,"Ingresa solo numeros en el telefono");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Faltan de obtener datos");
            }
        }
        if (objeto== btnEditar){
            if((!txNom.getText().isEmpty())&&(!txApell.getText().isEmpty())&&(!txTel.getText().isEmpty())&&
                    (!txPeso.getText().isEmpty())&&(!txtAltu.getText().isEmpty())&&(!txEd.getText().isEmpty())&&
                    (comboTipSan.getSelectedIndex()!=0)&&(!txAler.getText().isEmpty())&&(!txEnfer.getText().isEmpty())){
                try{
                    long numero = Long.parseLong((txTel).getText());
                    try{
                        long  peso= Integer.parseInt(txPeso.getText());
                        try{
                            int altura= Integer.parseInt((txtAltu.getText()));
                            try{
                                int edad= Integer.parseInt(txEd.getText());
                                try{
                                    String arregloVijeo = VerPacientes.modelo.getValueAt(VerPacientes.tabla.getSelectedRow(),0).toString()+","+VerPacientes.modelo.getValueAt(VerPacientes.tabla.getSelectedRow(),1).toString()+","
                                            +VerPacientes.modelo.getValueAt(VerPacientes.tabla.getSelectedRow(),2).toString()+
                                            ","+ VerPacientes.modelo.getValueAt(VerPacientes.tabla.getSelectedRow(),3).toString()+
                                            ","+ VerPacientes.modelo.getValueAt(VerPacientes.tabla.getSelectedRow(),4).toString()+
                                            ","+ VerPacientes.modelo.getValueAt(VerPacientes.tabla.getSelectedRow(),5).toString()+
                                            ","+ VerPacientes.modelo.getValueAt(VerPacientes.tabla.getSelectedRow(),6).toString();

                                    String arregloNuevo = txNom.getText()+","+txApell.getText()+","+txTel.getText()+","
                                            +peso+","+altura+","+edad+","+(comboTipSan.getSelectedItem().toString());

                                    String [] arregloTemp= new String[7];
                                    arregloTemp[0]= txNom.getText();
                                    arregloTemp[1]= txApell.getText();
                                    arregloTemp[2]= txTel.getText();
                                    arregloTemp[3]= txPeso.getText();
                                    arregloTemp[4]= txtAltu.getText();
                                    arregloTemp[5]= Integer.toString(edad);
                                    arregloTemp[6]= comboTipSan.getSelectedItem().toString();

                                    modificar(infoPac,arregloVijeo,arregloNuevo);

                                   //JOptionPane.showMessageDialog(null,arregloTemp);
                                   actualizarTabla(VerPacientes.modelo,(VerPacientes.modelo.getRowCount()-1),arregloTemp);

                                    /*//Creamos el objeto para que escriba en el archivo de texto
                                    escribir= new FileWriter(infoPac,true);

                                    //Añadimos la info a el archivo de texto
                                    escribir.write(txNom.getText()+","+txApell.getText()+","+numero+","+peso+","+
                                            altura+","+edad+","+(comboTipSan.getSelectedItem().toString())+"\n");
                                    //Concluimos la escritura
                                    escribir.close();

                                    //Despues se agregan a la tabla
                                    VerPacientes.modelo.addRow(new Object[]{txNom.getText(),txApell.getText()
                                            ,numero,peso,altura,edad,(comboTipSan.getSelectedItem().toString())});
                                    agregarCita.setVisible(true);
                                    */
                                }catch (Exception e){
                                    JOptionPane.showMessageDialog(null,e);
                                }
                            }catch (Exception e){
                                JOptionPane.showMessageDialog(null,"Solo debes de ingresar numeros en la edad");
                            }
                        }catch (Exception e){
                            JOptionPane.showMessageDialog(null,e);
                        }
                    }catch (Exception e){
                        JOptionPane.showMessageDialog(null,"Ingrese solo numeros para el peso");
                    }
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,"Ingresa solo numeros en el telefono");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Faltan de obtener datos");
            }
        }if(objeto==btnSalir){
            if ((!txNom.getText().isEmpty())||(!txApell.getText().isEmpty())||(!txTel.getText().isEmpty())||
                    (!txPeso.getText().isEmpty())||(!txtAltu.getText().isEmpty())||(!txEd.getText().isEmpty())||
                    (comboTipSan.getSelectedIndex()!=0)||(!txAler.getText().isEmpty())||(!txEnfer.getText().isEmpty())){
                int opcion =JOptionPane.showConfirmDialog(null,"Tienes Datos por Guardar\n¿Estas seguro que deseas salir?",
                        "Datos escritos",JOptionPane.YES_NO_OPTION);
                if(opcion==JOptionPane.YES_NO_OPTION){
                    dispose();
                    txNom.setText(null);
                    txApell.setText(null);
                    txTel.setText(null);
                    txPeso.setText(null);
                    txEd.setText(null);
                    txtAltu.setText(null);
                    comboTipSan.setSelectedIndex(0);
                }
            }else{
                dispose();
                txNom.setText(null);
                txApell.setText(null);
                txTel.setText(null);
                txPeso.setText(null);
                txEd.setText(null);
                txtAltu.setText(null);
                comboTipSan.setSelectedIndex(0);
            }
        }
    }//Fin del metodo ActionListenr
}//Fin de la clase
