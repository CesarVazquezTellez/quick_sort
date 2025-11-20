import java.io.*;
import java.util.*;

public class QuickSortFile {

    private static final String BASE_PATH = "C:\\archivos\\";

    public static void main(String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Ingresa el nombre del archivo de entrada (ej: datos.txt):");
            String inputFileName = br.readLine();

            System.out.println("Ingresa el nombre del archivo de salida (ej: resultado.txt):");
            String outputFileName = br.readLine();

            String inputPath = BASE_PATH + inputFileName;
            String outputPath = BASE_PATH + outputFileName;

            List<Integer> numbers = readNumbersFromFile(inputPath);

            int[] array = numbers.stream().mapToInt(i -> i).toArray();

            quickSort(array, 0, array.length - 1);

            writeNumbersToFile(array, outputPath);

            System.out.println("Archivo generado: " + outputPath);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static List<Integer> readNumbersFromFile(String filename) throws IOException {
        List<Integer> numbers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");
            for (String p : parts) {
                numbers.add(Integer.parseInt(p));
            }
        }
        br.close();
        return numbers;
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void writeNumbersToFile(int[] arr, String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        for (int num : arr) {
            bw.write(num + "\n");
        }
        bw.close();
    }
}
