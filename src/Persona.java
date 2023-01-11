//Clase en donde se almacenaran los datos mientras se guardan el los archivos de texto

public class Persona {
    //Atributos de la clase medico
    private String nombreMed,apellMed,especialidad;
    //Atributos de la clase Paciente


    public void setNombreMed(String nombreMed) {
        this.nombreMed = nombreMed;
    }

    public void setApellMed(String apellMed) {
        this.apellMed = apellMed;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNombreMed() {
        return nombreMed;
    }

    public String getApellMed() {
        return apellMed;
    }

    public String getEspecialidad() {
        return especialidad;
    }
}
