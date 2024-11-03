package Tarea;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.json.JSONObject;

public class P1Tarea1MaiguaErick_Java {

    public static double Maiuga_CalcularPromedio(double[] Maiuga_Notas) {
        double Maiuga_Suma = 0;
        for (double Maiuga_Nota : Maiuga_Notas) {
            Maiuga_Suma += Maiuga_Nota;
        }
        return Maiuga_Suma / Maiuga_Notas.length;
    }

    public static void Maiuga_GuardarEnJson(double[] Maiuga_Notas, double Maiuga_Promedio) {
        JSONObject Maiuga_JsonObject = new JSONObject();
        Maiuga_JsonObject.put("notas", Maiuga_Notas);
        Maiuga_JsonObject.put("promedio", Maiuga_Promedio);

        try (FileWriter Maiuga_File = new FileWriter("notas.json")) {
            Maiuga_File.write(Maiuga_JsonObject.toString(4));
            System.out.println("Datos guardados en notas.json");
        } catch (IOException e) {
            System.out.println("Error al guardar en JSON: " + e.getMessage());
        }
    }

    public static void Maiuga_GuardarEnCsv(double[] Maiuga_Notas, double Maiuga_Promedio) {
        try (FileWriter Maiuga_File = new FileWriter("notas.csv")) {
            Maiuga_File.append("Nota1,Nota2,Nota3,Promedio\n");
            for (double Maiuga_Nota : Maiuga_Notas) {
                Maiuga_File.append(String.valueOf(Maiuga_Nota)).append(",");
            }
            Maiuga_File.append(String.valueOf(Maiuga_Promedio)).append("\n");
            System.out.println("Datos guardados en notas.csv");
        } catch (IOException e) {
            System.out.println("Error al guardar en CSV: " + e.getMessage());
        }
    }

    public static void Maiuga_IngresarNotas(double[] Maiuga_Notas, Scanner Maiuga_Scanner) {
        for (int i = 0; i < Maiuga_Notas.length; i++) {
            double Maiuga_Nota = -1;
            boolean Maiuga_EntradaValida = false;

            while (!Maiuga_EntradaValida) {
                System.out.print("Ingrese la nota " + (i + 1) + " (entre 0 y 20): ");
                try {
                    Maiuga_Nota = Maiuga_Scanner.nextDouble();
                    if (Maiuga_Nota >= 0 && Maiuga_Nota <= 20) {
                        Maiuga_EntradaValida = true;
                        Maiuga_Notas[i] = Maiuga_Nota;
                    } else {
                        System.out.println("Entrada invalida. Por favor ingrese un numero entre 0 y 20.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada invalida. Por favor ingrese un numero valido.");
                    Maiuga_Scanner.next();
                }
            }
        }
    }

    public static int Maiuga_ObtenerOpcionMenu(Scanner Maiuga_Scanner) {
        int Maiuga_Opcion = -1;
        boolean Maiuga_EntradaValida = false;

        while (!Maiuga_EntradaValida) {
            System.out.println("=== Menu ===");
            System.out.println("1. Ingresar notas");
            System.out.println("2. Mostrar promedio");
            System.out.println("3. Guardar en JSON");
            System.out.println("4. Guardar en CSV");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            try {
                Maiuga_Opcion = Maiuga_Scanner.nextInt();
                if (Maiuga_Opcion >= 1 && Maiuga_Opcion <= 5) {
                    Maiuga_EntradaValida = true;
                } else {
                    System.out.println("Opcion invalida. Por favor seleccione una opcion entre 1 y 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcion invalida. Por favor ingrese un numero.");
                Maiuga_Scanner.next();
            }
        }

        return Maiuga_Opcion;
    }

    public static void main(String[] args) {
        Scanner Maiuga_Scanner = new Scanner(System.in);
        double[] Maiuga_Notas = new double[3];
        double Maiuga_Promedio = 0;
        int Maiuga_Opcion;

        do {
            Maiuga_Opcion = Maiuga_ObtenerOpcionMenu(Maiuga_Scanner);

            switch (Maiuga_Opcion) {
                case 1:
                    Maiuga_IngresarNotas(Maiuga_Notas, Maiuga_Scanner);
                    Maiuga_Promedio = Maiuga_CalcularPromedio(Maiuga_Notas);
                    System.out.println("Notas ingresadas correctamente.");
                    break;

                case 2:
                    if (Maiuga_Promedio > 0) {
                        System.out.println("El promedio es: " + Maiuga_Promedio);
                    } else {
                        System.out.println("Debe ingresar las notas primero.");
                    }
                    break;

                case 3:
                    if (Maiuga_Promedio > 0) {
                        Maiuga_GuardarEnJson(Maiuga_Notas, Maiuga_Promedio);
                    } else {
                        System.out.println("Debe ingresar las notas primero.");
                    }
                    break;

                case 4:
                    if (Maiuga_Promedio > 0) {
                        Maiuga_GuardarEnCsv(Maiuga_Notas, Maiuga_Promedio);
                    } else {
                        System.out.println("Debe ingresar las notas primero.");
                    }
                    break;

                case 5:
                    System.out.println("Saliendo...");
                    break;
            }

        } while (Maiuga_Opcion != 5);

        Maiuga_Scanner.close();
    }
}
