public class Students {
    private String idEstudiante;
    private String primerNombre;
    private String apellidoPaterno;
    private String genero;
    private String carrera;
    private int nota;

    public Students(String idEstudiante, String primerNombre, String apellidoPaterno, String genero, String carrera, int nota) {
        this.idEstudiante = idEstudiante;
        this.primerNombre = primerNombre;
        this.apellidoPaterno = apellidoPaterno;
        this.genero = genero;
        this.carrera = carrera;
        this.nota = nota;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getGenero(){
        return genero;
    }

    public void setGenero(){
        this.genero = genero;
    }

    public String getCarrera(){
        return carrera;
    }

    public void setCarrera(){
        this.carrera = carrera;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }


    @Override
    public String toString() {
        return "Estudiante{" +
                "primerNombre='" + primerNombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", genero=" + genero + '\'' +
                ", carrera=" + carrera + '\'' +
                ", nota=" + nota +
                '}';
    }
}
