package Tarea;


import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.json.JSONObject;

public class P1Tarea1MaiguaErick_Java {

    public static double calcularPromedio(double[] notas) {
        double suma = 0;
        for (double nota : notas) {
            suma += nota;
        }
        return suma / notas.length;
    }

    public static void guardarEnJson(double[] notas, double promedio) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("notas", notas);
        jsonObject.put("promedio", promedio);

        try (FileWriter file = new FileWriter("notas.json")) {
            file.write(jsonObject.toString(4));
            System.out.println("Datos guardados en notas.json");
        } catch (IOException e) {
            System.out.println("Error al guardar en JSON: " + e.getMessage());
        }
    }

    public static void guardarEnCsv(double[] notas, double promedio) {
        try (FileWriter file = new FileWriter("notas.csv")) {
            file.append("Nota1,Nota2,Nota3,Promedio\n");
            for (double nota : notas) {
                file.append(String.valueOf(nota)).append(",");
            }
            file.append(String.valueOf(promedio)).append("\n");
            System.out.println("Datos guardados en notas.csv");
        } catch (IOException e) {
            System.out.println("Error al guardar en CSV: " + e.getMessage());
        }
    }

    public static void ingresarNotas(double[] notas, Scanner scanner) {
        for (int i = 0; i < notas.length; i++) {
            double nota = -1;
            boolean entradaValida = false;

            while (!entradaValida) {
                System.out.print("Ingrese la nota " + (i + 1) + " (entre 0 y 20): ");
                try {
                    nota = scanner.nextDouble();
                    if (nota >= 0 && nota <= 20) {
                        entradaValida = true;
                        notas[i] = nota;
                    } else {
                        System.out.println("Entrada invalida. Por favor ingrese un numero entre 0 y 20.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada invalida. Por favor ingrese un numero valido.");
                    scanner.next();
                }
            }
        }
    }

    public static int obtenerOpcionMenu(Scanner scanner) {
        int opcion = -1;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.println("=== Menu ===");
            System.out.println("1. Ingresar notas");
            System.out.println("2. Mostrar promedio");
            System.out.println("3. Guardar en JSON");
            System.out.println("4. Guardar en CSV");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            try {
                opcion = scanner.nextInt();
                if (opcion >= 1 && opcion <= 5) {
                    entradaValida = true;
                } else {
                    System.out.println("Opcion invalida. Por favor seleccione una opcion entre 1 y 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcion invalida. Por favor ingrese un numero.");
                scanner.next();
            }
        }

        return opcion;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] notas = new double[3];
        double promedio = 0;
        int opcion;

        do {
            opcion = obtenerOpcionMenu(scanner);

            switch (opcion) {
                case 1:
                    ingresarNotas(notas, scanner);
                    promedio = calcularPromedio(notas);
                    System.out.println("Notas ingresadas correctamente.");
                    break;

                case 2:
                    if (promedio > 0) {
                        System.out.println("El promedio es: " + promedio);
                    } else {
                        System.out.println("Debe ingresar las notas primero.");
                    }
                    break;

                case 3:
                    if (promedio > 0) {
                        guardarEnJson(notas, promedio);
                    } else {
                        System.out.println("Debe ingresar las notas primero.");
                    }
                    break;

                case 4:
                    if (promedio > 0) {
                        guardarEnCsv(notas, promedio);
                    } else {
                        System.out.println("Debe ingresar las notas primero.");
                    }
                    break;

                case 5:
                    System.out.println("Saliendo...");
                    break;
            }

        } while (opcion != 5);

        scanner.close();
    }
}
