/*
 * Esta es la Ventana Principal.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import javax.swing.*;

/**
 *
 * @Alan Guillermo
 */
public class VentanaPrincipal extends JFrame implements ActionListener{


    FondoPanel fondo= new FondoPanel();
    JLabel titulo= new JLabel();
    JLabel subtitulo= new JLabel();
    JButton btnAgregar= new JButton();

    JMenuBar barra= new JMenuBar();
    JMenu menuMedico= new JMenu("Medicos");
    JMenuItem medAgregar = new JMenuItem("Agregar");
    JMenuItem medVer= new JMenuItem("Ver");
    JMenu menuPac= new JMenu("Pacientes");
    JMenuItem pacVer= new JMenuItem("Ver");
    JMenu menuCitas= new JMenu("Citas");
    JMenuItem citaVer= new JMenuItem("Ver");
    JMenuItem citaAgregar= new JMenuItem("Agregar");

    Medicos medicos= new Medicos();
    VerMedicos verMedicos= new VerMedicos();
    AgregPacientes agregPacientes= new AgregPacientes();
    VerPacientes verPacientes= new VerPacientes();
    AgregarCita agregarCita= new AgregarCita();
    VerCitas verCitas= new VerCitas();

    public VentanaPrincipal(){
        super("Clinica de la Mujer");


        //Aqui van a hacer para los metodos
        ajustesVentana();


        //Añadimos los iconos
        ImageIcon imagenDoc= new ImageIcon("Imagenes/IconoMed.png");
        Icon iconoDoc= new ImageIcon(imagenDoc.getImage().getScaledInstance(50,50,50));

        ImageIcon imagenPac= new ImageIcon("Imagenes/IconoPac.png");
        Icon iconoPac= new ImageIcon(imagenPac.getImage().getScaledInstance(50,50,50));

        ImageIcon imagenCitas= new ImageIcon("Imagenes/IconoHora.png");
        Icon iconoCitas= new ImageIcon(imagenCitas.getImage().getScaledInstance(50,50,50));

        ImageIcon imagenAgreg= new ImageIcon("Imagenes/IconoAgregar.png");
        Icon iconoAgreg= new ImageIcon(imagenAgreg.getImage().getScaledInstance(20,20,20));

        ImageIcon imagenVer= new ImageIcon("Imagenes/IconoVer.png");
        Icon iconoVer= new ImageIcon(imagenVer.getImage().getScaledInstance(25,25,25));

        //Se agrega Bienvenido
        titulo.setText("Bienvenido");
        titulo.setFont(new Font("Bodoni MT",Font.BOLD,50));
        titulo.setBounds(320,100,300,50);

        //Se agerega Clinica de la Mujer
        subtitulo.setText("Clinica de la Mujer");
        subtitulo.setFont(new Font("Bodoni MT",Font.BOLD,50));
        subtitulo.setBounds(320,170,400,100);

        //Se agrega el boton para añadir a nuevos pacientes
        btnAgregar.setText("Agregar Pacientes");
        btnAgregar.setFont(new Font("Calibri",Font.BOLD,15));
        btnAgregar.setBounds(400,300,200,60);
        btnAgregar.setIcon(iconoPac);
        btnAgregar.setBackground(new Color( 241, 234 ,231));

        //Se agrega la barra de menu

        //Primero los medicos
        menuMedico.setIcon(iconoDoc);
        menuMedico.setFont(new Font("Calibri",Font.BOLD,15));
        menuPac.setIcon(iconoPac);
        menuPac.setFont(new Font("Calibri",Font.BOLD,15));
        menuCitas.setIcon(iconoCitas);
        menuCitas.setFont(new Font("Calibri",Font.BOLD,15));

        menuMedico.add(medAgregar);
        medAgregar.setFont(new Font("Calibri",Font.BOLD,15));
        medAgregar.setIcon(iconoAgreg);
        menuMedico.add(medVer);
        medVer.setFont(new Font("Calibri",Font.BOLD,15));
        medVer.setIcon(iconoVer);

        //Despues los Pacientes
        menuPac.add(pacVer);
        pacVer.setFont(new Font("Calibri",Font.BOLD,15));
        pacVer.setIcon(iconoVer);

        //Despues se añaden Citas
        menuCitas.add(citaAgregar);
        citaAgregar.setIcon(iconoAgreg);
        citaAgregar.setFont(new Font("Calibri",Font.BOLD,15));
        menuCitas.add(citaVer);
        citaVer.setFont(new Font("Calibri Light",Font.BOLD,15));
        citaVer.setIcon(iconoVer);

        barra.setBackground(new Color( 241, 234 ,231));
        barra.add(menuMedico);
        barra.add(menuPac);
        barra.add(menuCitas);

        //Se agregan todas las acciones
        medAgregar.addActionListener(this);
        medVer.addActionListener(this);
        pacVer.addActionListener(this);
        btnAgregar.addActionListener(this);
        citaAgregar.addActionListener(this);
        citaVer.addActionListener(this);

        //Se añade los objetos
        this.setJMenuBar(barra);
        this.add(titulo);
        this.add(subtitulo);
        this.add(btnAgregar);
        //Se añade la imagen
        this.add(fondo);
    }

    public void ajustesVentana(){

        this.setSize(900,550);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ev){
        Object objeto= ev.getSource();
        if(objeto==medAgregar){
            medicos.setVisible(true);
        }
        if(objeto== medVer){
            verMedicos.setVisible(true);
        }
        if(objeto == btnAgregar){
            agregPacientes.setVisible(true);
        }
        if (objeto == pacVer){
            verPacientes.setVisible(true);
        }
        if(objeto== citaAgregar){
            agregarCita.setVisible(true);
        }
        if (objeto== citaVer){
            verCitas.setVisible(true);
        }
    }
    public static void main(String[] args) {
        VentanaPrincipal ventana= new VentanaPrincipal();
        JPanel panelsito;
    }

    class FondoPanel extends JPanel{
        private Image imagen;
        public void paint(Graphics g){
            imagen= new ImageIcon("Imagenes/Fondo.png").getImage();
            g.drawImage(imagen, 0, 0, getWidth(),getHeight(),this);
            setOpaque(false);
            super.paint(g);
        }
    }
}