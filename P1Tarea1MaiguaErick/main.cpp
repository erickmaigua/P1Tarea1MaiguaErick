#include <iostream>
#include <fstream>
#include <limits>

using namespace std;

float calcularPromedio(float notas[], int size) {
    float suma = 0;
    for (int i = 0; i < size; ++i) {
        suma += notas[i];
    }
    return suma / size;
}

void guardarEnArchivo(float notas[], int size, float promedio) {
    ofstream archivo("notas.txt",ios::out   );
    if (archivo.is_open()) {
        archivo << "Notas: ";
        for (int i = 0; i < size; ++i) {
            archivo << notas[i] << " ";
        }
        archivo << "\nPromedio: " << promedio << endl;
        archivo.close();
        cout << "Datos guardados en notas.txt\n";
    } else {
        cout << "No se pudo abrir el archivo.\n";
    }
}

void ingresarNotas(float notas[], int size) {
    for (int i = 0; i < size; ++i) {
        float nota;
        bool entradaValida = false;

        do {
            cout << "Ingrese la nota " << (i + 1) << " (entre 0 y 20): ";
            cin >> nota;

            if (cin.fail() || nota < 0 || nota > 20) {
                cout << "Entrada invalida. Por favor ingrese un numero entre 0 y 20.\n";
                cin.clear();
                cin.ignore(numeric_limits<streamsize>::max(), '\n');
            } else {
                entradaValida = true;
                notas[i] = nota;
            }
        } while (!entradaValida);
    }
}

int obtenerOpcionMenu() {
    int opcion;
    bool entradaValida = false;

    do {
        cout << "=== Menu ===\n";
        cout << "1. Ingresar notas\n";
        cout << "2. Mostrar promedio\n";
        cout << "3. Guardar en archivo\n";
        cout << "4. Salir\n";
        cout << "Seleccione una opcion: ";
        cin >> opcion;

        if (cin.fail() || opcion < 1 || opcion > 4) {
            cout << "Opcion invalida. Por favor seleccione una opcion entre 1 y 4.\n";
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
        } else {
            entradaValida = true;
        }
    } while (!entradaValida);

    return opcion;
}

int main() {
    float notas[3];
    float promedio = 0;
    int opcion;

    do {
        opcion = obtenerOpcionMenu();

        switch (opcion) {
            case 1:
                ingresarNotas(notas, 3);
                promedio = calcularPromedio(notas, 3);
                cout << "Notas ingresadas correctamente.\n";
                break;

            case 2:
                if (promedio > 0) {
                    cout << "El promedio es: " << promedio << endl;
                } else {
                    cout << "Debe ingresar las notas primero.\n";
                }
                break;

            case 3:
                if (promedio > 0) {
                    guardarEnArchivo(notas, 3, promedio);
                } else {
                    cout << "Debe ingresar las notas primero.\n";
                }
                break;

            case 4:
                cout << "Saliendo...\n";
                break;
        }

    } while (opcion != 4);

    return 0;
}
