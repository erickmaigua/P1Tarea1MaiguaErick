#include <iostream>
#include <fstream>
#include <limits>

using namespace std;

float Maiuga_CalcularPromedio(float Maiuga_Notas[], int Maiuga_Size) {
    float Maiuga_Suma = 0;
    for (int i = 0; i < Maiuga_Size; ++i) {
        Maiuga_Suma += Maiuga_Notas[i];
    }
    return Maiuga_Suma / Maiuga_Size;
}

void Maiuga_GuardarEnArchivo(float Maiuga_Notas[], int Maiuga_Size, float Maiuga_Promedio) {
    ofstream Maiuga_Archivo("notas.txt");
    if (Maiuga_Archivo.is_open()) {
        Maiuga_Archivo << "Notas: ";
        for (int i = 0; i < Maiuga_Size; ++i) {
            Maiuga_Archivo << Maiuga_Notas[i] << " ";
        }
        Maiuga_Archivo << "\nPromedio: " << Maiuga_Promedio << endl;
        Maiuga_Archivo.close();
        cout << "Datos guardados en notas.txt\n";
    } else {
        cout << "No se pudo abrir el archivo.\n";
    }
}

void Maiuga_IngresarNotas(float Maiuga_Notas[], int Maiuga_Size) {
    for (int i = 0; i < Maiuga_Size; ++i) {
        float Maiuga_Nota;
        bool Maiuga_EntradaValida = false;

        do {
            cout << "Ingrese la nota " << (i + 1) << " (entre 0 y 20): ";
            cin >> Maiuga_Nota;

            if (cin.fail() || Maiuga_Nota < 0 || Maiuga_Nota > 20) {
                cout << "Entrada invalida. Por favor ingrese un numero entre 0 y 20.\n";
                cin.clear();
                cin.ignore(numeric_limits<streamsize>::max(), '\n');
            } else {
                Maiuga_EntradaValida = true;
                Maiuga_Notas[i] = Maiuga_Nota;
            }
        } while (!Maiuga_EntradaValida);
    }
}

int Maiuga_ObtenerOpcionMenu() {
    int Maiuga_Opcion;
    bool Maiuga_EntradaValida = false;

    do {
        cout << "=== Menu ===\n";
        cout << "1. Ingresar notas\n";
        cout << "2. Mostrar promedio\n";
        cout << "3. Guardar en archivo\n";
        cout << "4. Salir\n";
        cout << "Seleccione una opcion: ";
        cin >> Maiuga_Opcion;

        if (cin.fail() || Maiuga_Opcion < 1 || Maiuga_Opcion > 4) {
            cout << "Opcion invalida. Por favor seleccione una opcion entre 1 y 4.\n";
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
        } else {
            Maiuga_EntradaValida = true;
        }
    } while (!Maiuga_EntradaValida);

    return Maiuga_Opcion;
}

int main() {
    float Maiuga_Notas[3];
    float Maiuga_Promedio = 0;
    int Maiuga_Opcion;

    do {
        Maiuga_Opcion = Maiuga_ObtenerOpcionMenu();

        switch (Maiuga_Opcion) {
            case 1:
                Maiuga_IngresarNotas(Maiuga_Notas, 3);
                Maiuga_Promedio = Maiuga_CalcularPromedio(Maiuga_Notas, 3);
                cout << "Notas ingresadas correctamente.\n";
                break;

            case 2:
                if (Maiuga_Promedio > 0) {
                    cout << "El promedio es: " << Maiuga_Promedio << endl;
                } else {
                    cout << "Debe ingresar las notas primero.\n";
                }
                break;

            case 3:
                if (Maiuga_Promedio > 0) {
                    Maiuga_GuardarEnArchivo(Maiuga_Notas, 3, Maiuga_Promedio);
                } else {
                    cout << "Debe ingresar las notas primero.\n";
                }
                break;

            case 4:
                cout << "Saliendo...\n";
                break;
        }

    } while (Maiuga_Opcion != 4);

    return 0;
}
