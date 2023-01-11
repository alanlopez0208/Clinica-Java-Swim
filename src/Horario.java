import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Objects;

//Esta clase va hacer de la ventana para añadir el horario del medico
class Horario extends JFrame implements ActionListener {

    JLabel titulo,diasDisp,llunes,lmar,lmierl,ljue,lvier;
    JLabel desLun,desMar,desMier,desJue,desVier;
    JLabel hastLun,hastMar,hastMier,hastJue,hastVier;
    JCheckBox lun,mar,mier,jue,vier;
    JComboBox comboDisp,comboDesLun, comboDesMar,comboDesMier,comboDesJue,comboDesVier,
            comboHastLun,comboHastMar,comboHastJue, comboHastMier, comboHastaVier;
    JButton btnAgregar,btnSalir;

    ArrayList<String> lista;
    File infoDoca= new File("Imagenes/InfoDocs.txt");
    File horario= new File("Imagenes/Horario.txt");
    File temporal= new File("Imagenes/Temporal.txt");
    FileWriter fw,escribir;
    FileReader fileReader,fileReader2;
    BufferedReader leer,leer2;


    //Constructor de la clase
    public Horario() {
        super("Seleccionar Horario");
        this.setSize(600,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        titulo= new JLabel("Añadir Horario");
        titulo.setBounds(230,50,200,28);
        titulo.setFont(new Font("Bodoni MT",Font.BOLD,20));


        diasDisp = new JLabel("Dias Dispoibles: ");
        diasDisp.setBounds(30,100,140,20);
        diasDisp.setFont(new Font("Calibri",Font.BOLD,15));


        lun= new JCheckBox("Lunes");
        lun.setBounds(150,100,70,20);
        lun.setFont(new Font("Calibri",Font.BOLD,15));
        lun.setFont(new Font("Calibri",Font.BOLD,15));


        mar= new JCheckBox("Martes");
        mar.setBounds(220,100,70,20);
        mar.setFont(new Font("Calibri",Font.BOLD,15));


        mier= new JCheckBox("Miercoles");
        mier.setBounds(310,100,90,20);
        mier.setFont(new Font("Calibri",Font.BOLD,15));

        jue= new JCheckBox("Jueves");
        jue.setBounds(410,100,80,20);
        jue.setFont(new Font("Calibri",Font.BOLD,15));


        vier= new JCheckBox("Viernes");
        vier.setBounds(490,100,80,20);

        llunes= new JLabel("Lunes");
        llunes.setBounds(30,150,80,20);
        llunes.setFont(new Font("Calibri",Font.BOLD,15));
        llunes.setBackground(new Color( 241, 234 ,231));

        desLun= new JLabel("Desde:");
        desLun.setBounds(120,150,80,20);
        desLun.setFont(new Font("Calibri",Font.BOLD,15));

        String[] horaDes={"8","9","10","11","12","13","14","15","15"};
        comboDesLun= new JComboBox(horaDes);
        comboDesLun.setBounds(190,145,80,30);
        comboDesLun.setEnabled(false);
        comboDesLun.setFont(new Font("Calibri",Font.BOLD,15));
        comboDesLun.setBackground(new Color( 241, 234 ,231));


        hastLun= new JLabel("Hasta:");
        hastLun.setBounds(330,150,40,20);
        hastLun.setFont(new Font("Calibri",Font.BOLD,15));

        String[] horaHast={"9","10","11","12","13","14","15","16","17"};
        comboHastLun= new JComboBox(horaHast);
        comboHastLun.setBounds(390,145,80,30);
        comboHastLun.setEnabled(false);
        comboHastLun.setFont(new Font("Calibri",Font.BOLD,15));
        comboHastLun.setBackground(new Color( 241, 234 ,231));

        lmar= new JLabel("Martes");
        lmar.setBounds(30,190,80,20);
        lmar.setFont(new Font("Calibri",Font.BOLD,15));
        lmar.setBackground(new Color( 241, 234 ,231));

        desMar= new JLabel("Desde:");
        desMar.setBounds(120,190,50,20);
        desMar.setFont(new Font("Calibri",Font.BOLD,15));

        comboDesMar= new JComboBox(horaDes);
        comboDesMar.setBounds(190,185,80,30);
        comboDesMar.setEnabled(false);
        comboDesMar.setFont(new Font("Calibri",Font.BOLD,15));
        comboDesMar.setBackground(new Color( 241, 234 ,231));

        hastMar= new JLabel("Hasta:");
        hastMar.setBounds(330,190,40,20);
        hastMar.setFont(new Font("Calibri",Font.BOLD,15));

        comboHastMar= new JComboBox(horaHast);
        comboHastMar.setBounds(390,190,80,30);
        comboHastMar.setEnabled(false);
        comboHastMar.setFont(new Font("Calibri",Font.BOLD,15));
        comboHastMar.setBackground(new Color( 241, 234 ,231));

        lmierl= new JLabel("Miercoles");
        lmierl.setBounds(30,230,90,20);
        lmierl.setFont(new Font("Calibri",Font.BOLD,15));
        lmierl.setBackground(new Color( 241, 234 ,231));
        desMier= new JLabel("Desde:");
        desMier.setBounds(120,230,50,20);
        desMier.setFont(new Font("Calibri",Font.BOLD,15));

        comboDesMier= new JComboBox(horaDes);
        comboDesMier.setBounds(190,230,80,30);
        comboDesMier.setEnabled(false);
        comboDesMier.setFont(new Font("Calibri",Font.BOLD,15));
        comboDesMier.setBackground(new Color( 241, 234 ,231));

        hastMier= new JLabel("Hasta:");
        hastMier.setBounds(330,230,40,20);
        hastMier.setFont(new Font("Calibri",Font.BOLD,15));

        comboHastMier= new JComboBox(horaHast);
        comboHastMier.setBounds(390,230,80,30);
        comboHastMier.setEnabled(false);
        comboHastMier.setFont(new Font("Calibri",Font.BOLD,15));
        comboHastMier.setBackground(new Color( 241, 234 ,231));


        ljue= new JLabel("Jueves");
        ljue.setBounds(30,270,80,20);
        ljue.setFont(new Font("Calibri",Font.BOLD,15));
        ljue.setBackground(new Color( 241, 234 ,231));

        desJue= new JLabel("Desde:");
        desJue.setBounds(120,270,50,20);
        desJue.setFont(new Font("Calibri",Font.BOLD,15));

        comboDesJue= new JComboBox(horaDes);
        comboDesJue.setBounds(190,270,80,30);
        comboDesJue.setEnabled(false);
        comboDesJue.setFont(new Font("Calibri",Font.BOLD,15));
        comboDesJue.setBackground(new Color( 241, 234 ,231));


        hastJue= new JLabel("Hasta:");
        hastJue.setBounds(330,270,40,20);
        hastJue.setFont(new Font("Calibri",Font.BOLD,15));


        comboHastJue= new JComboBox(horaHast);
        comboHastJue.setBounds(390,270,80,30);
        comboHastJue.setEnabled(false);
        comboHastJue.setFont(new Font("Calibri",Font.BOLD,15));
        comboHastJue.setBackground(new Color( 241, 234 ,231));

        lvier= new JLabel("Viernes");
        lvier.setBounds(30,320,80,20);
        lvier.setFont(new Font("Calibri",Font.BOLD,15));
        lvier.setBackground(new Color( 241, 234 ,231));

        desVier= new JLabel("Desde:");
        desVier.setBounds(120,320,50,20);
        desVier.setFont(new Font("Calibri",Font.BOLD,15));

        comboDesVier= new JComboBox(horaDes);
        comboDesVier.setBounds(190,320,80,30);
        comboDesVier.setEnabled(false);
        comboDesVier.setFont(new Font("Calibri",Font.BOLD,15));
        comboDesVier.setBackground(new Color( 241, 234 ,231));


        hastVier= new JLabel("Hasta:");
        hastVier.setBounds(330,320,40,20);
        hastVier.setFont(new Font("Calibri",Font.BOLD,15));

        comboHastaVier= new JComboBox(horaHast);
        comboHastaVier.setBounds(390,320,80,30);
        comboHastaVier.setEnabled(false);
        comboHastaVier.setFont(new Font("Calibri",Font.BOLD,15));
        comboHastaVier.setBackground(new Color( 241, 234 ,231));


        btnAgregar= new JButton("Añadir");
        btnAgregar.setBounds(350,400,80,27);
        btnAgregar.setFont(new Font("Calibri",Font.BOLD,15));
        btnAgregar.setBackground(new Color( 241, 234 ,231));


        btnSalir= new JButton("Salir");
        btnSalir.setBounds(450,400,70,27);
        btnSalir.setFont(new Font("Calibri",Font.BOLD,15));
        btnSalir.setBackground(new Color( 241, 234 ,231));

        //Se añaden las acciones
        lun.addActionListener(this);
        mar.addActionListener(this);
        mier.addActionListener(this);
        jue.addActionListener(this);
        vier.addActionListener(this);
        btnAgregar.addActionListener(this);
        btnSalir.addActionListener(this);

        //Se añaden los componentes
        this.add(titulo);
        this.add(diasDisp);
        this.add(lun);
        this.add(mar);
        this.add(mier);
        this.add(jue);
        this.add(vier);
        this.add(llunes);
        this.add(desLun);
        this.add(comboDesLun);
        this.add(hastLun);
        this.add(comboHastLun);

        this.add(lmar);
        this.add(desMar);
        this.add(comboDesMar);
        this.add(hastMar);
        this.add(comboHastMar);

        this.add(lmierl);
        this.add(desMier);
        this.add(comboDesMier);
        this.add(hastMier);
        this.add(comboHastMier);

        this.add(ljue);
        this.add(desJue);
        this.add(comboDesJue);
        this.add(hastJue);
        this.add(comboHastJue);

        this.add(lvier);
        this.add(desVier);
        this.add(comboDesVier);
        this.add(hastVier);
        this.add(comboHastaVier);

        this.add(btnAgregar);
        this.add(btnSalir);
        this.getContentPane().setBackground(new Color(246, 242, 239));
    }

    public void modificarHorario(){

    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        Object ob= ev.getSource();
        if(ob== lun){
            if(lun.isSelected()){
                comboDesLun.setEnabled(true);
                comboHastLun.setEnabled(true);
            }else {
                comboDesLun.setEnabled(false);
                comboHastLun.setEnabled(false);
            }
        }
        if(ob==mar){
            if(mar.isSelected()){
                comboDesMar.setEnabled(true);
                comboHastMar.setEnabled(true);
            }else{
                comboDesMar.setEnabled(false);
                comboHastMar.setEnabled(false);
            }
        }
        if(ob== mier){
            if (mier.isSelected()){
                comboDesMier.setEnabled(true);
                comboHastMier.setEnabled(true);
            }else{
                comboDesMier.setEnabled(false);
                comboHastMier.setEnabled(false);
            }
        }
        if(ob==jue){
            if(jue.isSelected()){
                comboDesJue.setEnabled(true);
                comboHastJue.setEnabled(true);
            }else{
                comboDesJue.setEnabled(false);
                comboHastJue.setEnabled(false);
            }
        }
        if(ob==vier){
            if(vier.isSelected()){
                comboDesVier.setEnabled(true);
                comboHastaVier.setEnabled(true);
            }else{
                comboDesVier.setEnabled(false);
                comboHastaVier.setEnabled(false);
            }
        }
        if(ob == btnAgregar){
            try{

                fileReader= new FileReader("Imagenes/Temporal.txt");
                leer= new BufferedReader(fileReader);
                int filas=0;
                String buscar= leer.readLine();

                //Esto es para saber el numero de de lines que tiene el archvio.
                while (buscar!=null){
                    filas++;
                    buscar=leer.readLine();
                }
                fileReader.close();

                fileReader2 = new FileReader(temporal);

                leer2 = new BufferedReader(fileReader2);

                String buscar2 = leer2.readLine();


                //Se añade el objeto escribir
                escribir= new FileWriter(horario,true);

                int a=1;
                //JOptionPane.showMessageDialog(null,a);
                while(buscar2 !=null){
                    String [] temporaL = buscar2.split(",");
                    if(a==(filas)){
                        Medicos.Escribir(infoDoca,filas+","+temporaL[0]+","+temporaL[1]+","+temporaL[2]);
                        escribir.write(temporaL[0]+",");
                        String arreglo2[]= {Integer.toString(filas), temporaL[0],temporaL[1],temporaL[2]};
                        VerMedicos.modelo.addRow(arreglo2);
                    }
                    a++;
                    buscar2=leer2.readLine();
                }
                //JOptionPane.showMessageDialog(null,a);
                //Primero se añaden
                //VerMedicos.modelo.addRow(Medicos.fila);
                //Ingresar datos en la tabla

                lista = new ArrayList<String>();

                if (lun.isSelected()){
                    lista.add(lun.getText());
                    lista.add(comboDesLun.getSelectedItem().toString());
                    lista.add(comboHastLun.getSelectedItem().toString());
                }
                if(mar.isSelected()){
                    lista.add(mar.getText());
                    lista.add(comboDesMar.getSelectedItem().toString());
                    lista.add(comboHastMar.getSelectedItem().toString());
                }
                if(mier.isSelected()){
                    lista.add(mier.getText());
                    lista.add(comboDesMier.getSelectedItem().toString());
                    lista.add(comboHastMier.getSelectedItem().toString());
                }
                if(jue.isSelected()){
                    lista.add(jue.getText());
                    lista.add(comboDesJue.getSelectedItem().toString());
                    lista.add(comboHastJue.getSelectedItem().toString());
                }
                if(vier.isSelected()){
                    lista.add(vier.getText());
                    lista.add(comboDesVier.getSelectedItem().toString());
                    lista.add(comboHastaVier.getSelectedItem().toString());
                }

                //Con este for añadimos la informacion necesaria para agendar las citas
                for(int i=0;i<lista.size();i++){
                    escribir.write(lista.get(i)+",");
                }
                //Se hace la sepracacion de linea
                escribir.write("\n");
                escribir.close();

                //Aparece el mensaje que se registraron los datos con Exito
                JOptionPane.showMessageDialog(null,"Se han añadido con Extio los Datos del Medico y sus Horarios");

                //Todo vuelve al estado natural
                lun.setSelected(false);
                mar.setSelected(false);
                mier.setSelected(false);
                jue.setSelected(false);
                vier.setSelected(false);

                //Tambien las CheckBox
                comboDesLun.setSelectedIndex(0);
                comboHastLun.setSelectedIndex(0);
                comboDesMar.setSelectedIndex(0);
                comboHastMar.setSelectedIndex(0);
                comboDesMier.setSelectedIndex(0);
                comboHastMier.setSelectedIndex(0);
                comboDesJue.setSelectedIndex(0);
                comboHastJue.setSelectedIndex(0);
                comboDesVier.setSelectedIndex(0);
                comboHastaVier.setSelectedIndex(0);

                //Se bloquean UwU
                comboDesLun.setEnabled(false);
                comboHastLun.setEnabled(false);
                comboDesMar.setEnabled(false);
                comboHastMar.setEnabled(false);
                comboDesMier.setEnabled(false);
                comboHastMier.setEnabled(false);
                comboDesJue.setEnabled(false);
                comboHastJue.setEnabled(false);
                comboDesVier.setEnabled(false);
                comboHastaVier.setEnabled(false);

                //Despues se cierra la ventana por si gustan añadir a otro medico
                this.dispose();
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
        if (ob == btnSalir){
            if((lun.isSelected())||(mar.isSelected())||(mier.isSelected())||(jue.isSelected())||(vier.isSelected())){
                int opcion =JOptionPane.showConfirmDialog(null,"Tienes Datos por Guardar\n¿Estas seguro que deseas salir?",
                        "Datos escritos",JOptionPane.YES_NO_OPTION);
                if(opcion==JOptionPane.YES_NO_OPTION){
                    dispose();
                }
            }else{
                dispose();
            }
        }
    }
}//Fin de la clase de Horario