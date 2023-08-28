import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ContadorPalabras {
    private String archivo;
    private String buscarPalabra;

    /**
     * Constructor que define el contador, 
     * en él se almacenan la ruta del archivo y la palabra que se busca
     */

    public ContadorPalabras(String archivo, String buscarPalabra){
        this.archivo = archivo;
        this.buscarPalabra = buscarPalabra;
    }

    /**
     * La funcion se encarga de contar la ocurrencias o coincidencias dentro del archivo.
     * Usa el metodo scanner para leer el archivo linea por linea.
     * Al final llama a la funcion contadorOcurrenciasLineas para contar las ocurrencias en esa linea.
     */
    public int contarOcurrenciasArchivo(File archivo) throws FileNotFoundException{
        int contador = 0;
        try (Scanner scanner = new Scanner(archivo)){
            while (scanner.hasNextLine()){
                String linea = scanner.nextLine();
                contador += contadorOcurrenciasLineas(linea);
            }
        }catch (FileNotFoundException e){
            System.err.println("No se encontró el archivo" + archivo.getName());
        }
        System.out.println("Ocurrencias en " + archivo.getName() + ": " + contador);
        return contador;
    }

    /*
     * Cuenta las ocurrencias que hay en cada linea de texto.
     * Luego verifica si la palabra coincide con la buscada y por ello incrementa el contador,
     */
    private int contadorOcurrenciasLineas(String linea) {
        int contador = 0;
        String[] palabras = linea.split("[^a-zA-Zá-úÁ-ÚñÑ]+");
        for (String palabra : palabras) {
            if (palabra.equals(buscarPalabra)) {
                contador++;
            }
        }
        return contador;
    }

    /*
     * Realiza el conteo de todas la ocurrencias de los archivos en la carpeta.
     * Comprueba si la ruta de la carpeta está correcta, luego obtiene la lista de los archivos.
     * Verifica el tipo de archivo y si es compatible.
     * LLama a contarOcurrencuasArchivos para contar las ocurrencias en ese archivo y un sumador para contar el total de coincidencias.
     */
    public void ContarOcurrenciasCarpeta() throws FileNotFoundException {
        File carpeta = new File(archivo);
        System.out.println(" la ruta seleccionada es " + carpeta);
        if(carpeta.exists() && carpeta.isDirectory()){
            File[] archivos = carpeta.listFiles();
            int totalOcurrencias = 0;
            boolean EncuentraArchivosTexto = false;
            for (File archivo : archivos){
                if(archivo.isFile() && esTipoArchivo(archivo.getName())){
                    int ocurrencias = contarOcurrenciasArchivo(archivo);
                    if (ocurrencias > 0){
                        EncuentraArchivosTexto = true;
                    }
                    totalOcurrencias += ocurrencias;
                }
            }
            if (EncuentraArchivosTexto){
                System.out.println("Total ocurrencias en la carpeta: " + totalOcurrencias);
            } else {
                System.out.println("No se encontraron archivos de texto");
            }
        } else {
            System.err.println("No se encuentra la carpeta idicada: " + archivo);
        }
    }

    /*
     * Verifica el tipo de archivo es admitida y devuelve un true de lo contrario retorna un false
     */
    private boolean esTipoArchivo(String tipoArchivo) {
        return tipoArchivo.endsWith(".txt") || tipoArchivo.endsWith(".xml") ||
               tipoArchivo.endsWith(".json") || tipoArchivo.endsWith(".csv");
    }
    
}
