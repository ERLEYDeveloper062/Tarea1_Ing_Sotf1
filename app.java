import java.io.FileNotFoundException;
import java.util.Scanner;

public class app {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la ruta de la carpeta: ");
        String RutaArchivo = scanner.nextLine();
        System.out.println("Ingresa la palabra a buscar: ");
        String buscarPalabra = scanner.nextLine();

        ContadorPalabras contarPalabras = new ContadorPalabras(RutaArchivo, buscarPalabra);
        contarPalabras.ContarOcurrenciasCarpeta();
    }
}
