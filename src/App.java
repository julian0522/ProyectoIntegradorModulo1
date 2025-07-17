import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    /*  
     * se definen las siguientes variables de clase que tendran un alcance global
     * en toda la aplicacion de registro estudiantil
    */
    static String nombre;
    static double nota1;
    static double nota2;
    static double nota3;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        menu();
    }

    /*
     * Metodo que se encarga de controlar el flujo principal de la aplicacion 
     * con el menu principal del sistema de registro estudiantil
     */
    static private void menu() {
        int opcion;

        do {
            System.out.println("-----------SISTEMA REGISTRO ESTIDIANTIL----------------\n");
            System.out.println("""
                        1. Registrar Datos de un Estudiante
                        2. Mostrar datos del estudiante actual
                        3. Calcular promedio de notas del estudiante actual
                        4. Borrar informacion estudiante actual
                        0. Salir
                    """);
            System.out.print("Ingrese su Opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarEstudiante();
                    break;

                case 2:
                    mostrarDatosEstudiante();
                    break;

                case 3:
                    calcularPromedioNotas();
                    break;

                case 4:
                    borrarEstudiante();
                    break;

                case 0:
                System.out.println("---------Proceso de registro Estudiantil Finalizado---------");
                    scanner.close();
                    break;

                default:
                    System.out.println("El valor ingresado no es valido");
                    break;
            }
        } while (opcion != 0);
    }

    /*
     * Metodo que se encarga del rtegistro de los estudiantes
     */
    static private void registrarEstudiante() {
        boolean valido = false;
        StringBuilder mensaje = new StringBuilder();
        // se define un ciclo para controlar que mientras el formulario no sea valido se van a seguir pidiendo los datos
        while (valido != true) {
            try {
                System.out.println("-----------------------------------------");
                System.out.print("Ingrese el nombre del estudiante: ");
                nombre = scanner.nextLine();
                System.out.print("Ingrese la nota 1: ");
                nota1 = scanner.nextDouble();
                System.out.print("Ingrese la nota 2: ");
                nota2 = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Ingrese la nota 3: ");
                nota3 = scanner.nextDouble();
                scanner.nextLine();

                valido = validarFormulario(nombre, nota1, nota2, nota3, mensaje);

                if (!valido) {
                    System.out.println(mensaje);
                    mensaje = null;
                    continue;
                }

                var confirmacion = confirmacionRegistro();
                if (!confirmacion) {
                    borrarEstudiante();
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar números válidos para las notas.");
                scanner.nextLine();
            }
        }
    }

    /*
     * Metodo que se encarga de validar el formulario de registro de los estudiantes
     * se encarga de validar que el nombre ingresado sea valido y no contenga numeros ni caractes especiales
     * para ello se compara el string con una expresion regular para validar esto;
     * tambien se validan los rangos permitidos para cada nota
     */
    static private boolean validarFormulario(String nombre, double nota1, double nota2, double nota3,
            StringBuilder mensaje) {

        if (nombre == null || nombre.isBlank() || !nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\\\s]+$")) {
            mensaje.append("El nombre ingresado no es valido\n");
        }

        if (nota1 < 0 || nota1 > 5) {
            mensaje.append("La nota 1 no se encuentra dentro del rango estipulado\n");
        }

        if (nota2 < 0 || nota2 > 5) {
            mensaje.append("La nota 2 no se encuentra dentro del rango estipulado\n");
        }

        if (nota1 < 0 || nota3 > 5) {
            mensaje.append("La nota 3 no se encuentra dentro del rango estipulado");
        }

        return (mensaje.length() > 0) ? false : true;
    }

    /*
     * Metodo para manejar la confirmacion del resgistro del estudiante, este metodo se encarga de mostrar un recuento
     * de los datos ingresados y brindar la confirmacion del registro de la informacion
     */
    static private boolean confirmacionRegistro() {
        int eleccion;
        do {
            System.out.println("------------------------------------------");
            System.out.println("Estudiante a Registrar por favor Confirme");
            System.out.println(String.format("""
                    Nombre: %s
                    Nota 1: %.1f
                    Nota 2: %.1f
                    Nota 3: %.1f
                    Confirmar = 1
                    Cancelar = 0
                    """, nombre, nota1, nota2, nota3));
            eleccion = scanner.nextInt();
            scanner.nextLine();

        } while (eleccion != 0 && eleccion != 1);

        return (eleccion == 0) ? false : true;
    }

    /*
     * Este metodo se encarga de mostrar los datos registrados del estudiante
     * en este metodo se valida que haya un estudiante registrado de antemano
     */
    static private void mostrarDatosEstudiante() {
        if (nombre == null) {
            System.out.println("No hay ningun estudiante registrado");
            return;
        }

        System.out.println("Informacion del Estudiante: " + nombre);
        System.out.println(String.format("""
                    Nombre: %s
                    Nota 1: %.1f
                    Nota 2: %.1f
                    Nota 3: %.1f
                """, nombre, nota1, nota2, nota3));
    }

    /* 
     * Metodo para llevar a cabo el calculo del promedio de las notas y adicional a ello definir 
     * si el estudiante registrado aprobo o reprobo 
    */
    static private void calcularPromedioNotas() {
        if (nombre == null) {
            System.out.println("No hay ningun estudiante registrado");
            return;
        }
        // Se usa un try catch para poder controlar el error en caso de que el usuario ingrese un valor que no sea del tipo esperado
        try {
            var promedio = (nota1 + nota2 + nota3) / 3;
            System.out.printf("El promedio del estduaidnte %s es: %.1f", nombre, promedio);
            scanner.nextLine();
            System.out.print("Defina la nota minima con la que el estudiante aprueba: ");
            var notaAprueba = scanner.nextDouble();

            String mensajeAprueba = (promedio < notaAprueba) ? "El estudiante reprobo" : "El estudiante aprobo";
            System.out.println(mensajeAprueba);
        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar números válidos para la nota.");
            scanner.nextLine();
        }
    }

    /*
     * Metodo para borrar el estudiante que se encuentre registrado, 
     * en este metodo se valida que por lo menos el usuario exista de antemano
    */ 
    static private void borrarEstudiante(){
        if (nombre == null){
            System.out.println("No hay ningun estudiante registrado para borrar");
            return;
        }

        nombre = null;
        nota1 = 0;
        nota2 = 0;
        nota3 = 0;
        System.out.printf("Estudiante %s eliminado con exito", nombre);
        scanner.nextLine();
    }
}
