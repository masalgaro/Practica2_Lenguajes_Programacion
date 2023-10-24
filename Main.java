import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.stream.*;

public class Main {
    static List<Students>estudiantes;
    public static void main(String[] args) throws IOException {
        cargarArchivo();
        mostrarEstudiantesGananMas(); //mostrarEstudiantes que ganan mas nota ☻
        mostrarEstudiantesPorCarr(); //mostarEstudiantes por carrera ☻
        cantEstudiantesPorCarr(); //cantEstudiantes por carrera ☻
        EstudianteGanaMasPorCarr(); //ganan mas nota por carreras ☻
        EstudianteGanaMas(); //El que gana mas de todos ☻
        promNotaporCarr(); //El promedio por carrera ☻
        MujeresPorCarr(); //Esto es mujeres por carrera lol ☻
        HombresPorCarr(); //manes por carrera ☻

    }
    static void cargarArchivo() throws IOException{
        Pattern pattern =Pattern.compile(",");
        String filename= "student-scores.csv";

        try(Stream<String> lines = Files.lines(Path.of(filename))){
            estudiantes=lines.skip(1).map(line->{
                String[]arr=pattern.split(line);
                return new Students(arr[0],arr[1],arr[2],arr[4],arr[9],Integer.parseInt(arr[10]));
            }).collect(Collectors.toList());
            estudiantes.forEach(System.out::println);
        }
    }
    static Predicate<Students> cuarentaaCien =
            e -> (e.getNota() >= 40 && e.getNota() <= 100);

    static void mostrarEstudiantesGananMas(){ //ganan mas nota
        System.out.printf(
                "%nEstudiante que gana mas nota en total:%n");
        estudiantes.stream()
                .filter(cuarentaaCien)
                .sorted(Comparator.comparing(Students::getNota))
                .forEach(System.out::println);
    }
    static void mostrarEstudiantesPorCarr(){ //estudiantes por carrera
        System.out.printf("%nEstudiantes por carrera:%n");
        Map<String, List<Students>> agrupadoPorCarrera =
                estudiantes.stream()
                        .collect(Collectors.groupingBy(Students::getCarrera));
        agrupadoPorCarrera.forEach(
                (carrera, estudiantesEnCarrera) ->
                {
                    System.out.println(carrera);
                    estudiantesEnCarrera.forEach(
                            estudiante -> System.out.printf(" %s%n", estudiante));
                }
        );
    }
    static void cantEstudiantesPorCarr(){ //num estudiantes
        // cuenta el número de estudiantes por carrera
        System.out.printf("%nConteo de estudiantes por carrera:%n");
        Map<String, Long> conteoEstudiantesPorCarrera =
                estudiantes.stream()
                        .collect(Collectors.groupingBy(Students::getCarrera,
                                TreeMap::new, Collectors.counting()));
        conteoEstudiantesPorCarrera.forEach(
                (carrera, conteo) -> System.out.printf(
                        "%s tiene %d estudiante(s)%n", carrera, conteo));
    }
    static void EstudianteGanaMasPorCarr(){ //ganan mas por carrera
        Function<Students, Integer> porNota = Students::getNota;
        Comparator<Students> NotaDescendete =
                Comparator.comparing(porNota);
        System.out.printf("%nPuntaje mas alto por carrera: %n");
        Map<String, List<Students>> agrupadoPorCarrera =
                estudiantes.stream()
                        .collect(Collectors.groupingBy(Students::getCarrera));
        agrupadoPorCarrera.forEach(
                (carrera, estudianteEnCarrera) ->
                {
                    System.out.print(carrera+": ");
                    Students estudianteMas=estudianteEnCarrera.stream().sorted(NotaDescendete.reversed())
                            .findFirst()
                            .get();
                    System.out.println(estudianteMas.getPrimerNombre()+" "+estudianteMas.getApellidoPaterno()+
                            " ///Cuanta nota? ==> nota: "+estudianteMas.getNota()+" !!!");
                }
        );
    }
    static void EstudianteGanaMas(){ //mas nota en general
        Function<Students, Integer> porNota = Students::getNota;
        Comparator<Students> NotaDescendete =
                Comparator.comparing(porNota);
        Students estudianteMas=estudiantes.stream()
                .sorted(NotaDescendete.reversed())
                .findFirst()
                .get();
        System.out.printf(
                "%nEstudiante que gana mas: %s %s %s %s%s%n",
                estudianteMas
                        .getPrimerNombre(),
                estudianteMas
                        .getApellidoPaterno()," ///Cuanta nota? ==> nota: ",
                estudianteMas
                        .getNota()," !!!");
    }
    static void promNotaporCarr(){
        Map<String, List<Students>> agrupadoPorCarrera =
                estudiantes.stream()
                        .collect(Collectors.groupingBy(Students::getCarrera));
        System.out.println("\nPromedio de notas de los estudiantes por Carrera:");
        agrupadoPorCarrera.forEach((carrera, estudiantesporCarr)-> {
            System.out.print(carrera+": ");
            System.out.println(estudiantesporCarr
                    .stream()
                    .mapToDouble(Students::getNota)
                    .average()
                    .getAsDouble());
        });
    }

    static void HombresPorCarr(){
        //Filtrar solo a los estudiantes homobres y contar por carrera
        System.out.printf("%nConteo de hombres por carrera:%n");
        Map<String, Long> conteoHombresPorCarrera = estudiantes.stream()
                .filter(student -> "male".equals(student.getGenero())) // Filtrar solo hombres
                .collect(Collectors.groupingBy(Students::getCarrera, TreeMap::new, Collectors.counting()));

        conteoHombresPorCarrera.forEach((carrera, conteo) -> System.out.printf("%s tiene %d estudiantes %n", carrera, conteo));
    }

    static void MujeresPorCarr() {
        //Filtrar solo a las estudiantes mujeres y contar por carrera
        System.out.printf("%nConteo de mujeres por carrera:%n");
        Map<String, Long> conteoMujeresPorCarrera = estudiantes.stream()
                .filter(student -> "female".equals(student.getGenero())) // Filtrar solo mujeres
                .collect(Collectors.groupingBy(Students::getCarrera, TreeMap::new, Collectors.counting()));

        conteoMujeresPorCarrera.forEach((carrera, conteo) -> System.out.printf("%s tiene %d estudiantes %n", carrera, conteo));
    }
}
