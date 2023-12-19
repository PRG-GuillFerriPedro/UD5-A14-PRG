/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ud5.a14.pedroguillferri;

import java.util.Scanner;

/**
 *
 * @author batoi
 */
public class UD5A14PedroGuillFerri {

    public static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        inicio();
    }

    public static void inicio() {
        int[] numSorteo = generarSorteo();
        int apuestas = comprobacionNumApuestas();
        int[] nivelesPremio = new int[apuestas];
        boolean[] complementario = new boolean[apuestas];
        for (int i = 0; i <= apuestas - 1; i++) {
            System.out.printf("----- Apuesta %d ----\n", i + 1);
            int[] apuesta = generarApuesta();
            mostrarCombinacion(apuesta);
            nivelesPremio[i] = obtenerNivelDePremio(apuesta, numSorteo);
            if (apuesta[6] == numSorteo[6]) {
                complementario[i] = true;
            }
        }
        System.out.println("");
        mostrarTotalAPagar(apuestas);
        System.out.println("");
        System.out.println("--------- SIMULADOR DE SORTEO ---------");
        mostrarCombinacion(numSorteo);
        System.out.println("");
        mostrarPremiosObtenidos(nivelesPremio, complementario);
    }

    public static int[] generarApuesta() {
        int[] array = new int[7];
        for (int i = 0; i <= array.length - 1; i++) {
            array[i] = generarAleatorio();
            for (int j = 0; j <= i - 1; j++) {
                if (array[j] != 0) {
                    do {
                        if (array[i] == array[j]) {
                            array[i] = generarAleatorio();
                        }
                    } while (array[i] == array[j]);

                }
            }
        }
        return array;
    }

    public static void mostrarCombinacion(int[] combinacion) {
        ordenarArray(combinacion);
        System.out.println("+----+----+----+----+----+----+--C--+");
        System.out.printf("| %02d | %02d | %02d | %02d | %02d | %02d | %02d  |\n", combinacion[0], combinacion[1], combinacion[2], combinacion[3], combinacion[4], combinacion[5], combinacion[6]);
        System.out.println("+----+----+----+----+----+----+-----+");
    }

    public static int[] generarSorteo() {
        int[] array = new int[7];
        for (int i = 0; i <= array.length - 1; i++) {
            array[i] = generarAleatorio();
            for (int j = 0; j <= i - 1; j++) {
                if (array[j] != 0) {
                    do {
                        if (array[i] == array[j]) {
                            array[i] = generarAleatorio();

                        }
                    } while (array[i] == array[j]);

                }
            }
        }
        return array;
    }

    public static int obtenerNivelDePremio(int[] apuesta, int[] combinacionGanadora) {
        int aciertos = 0;
        for (int i = 0; i <= apuesta.length - 1; i++) {
            for (int j = 0; j < apuesta.length-1; j++) {
                if (apuesta[j] == combinacionGanadora[i]) {
                    aciertos++;
                }
            }
        }
        return aciertos;
    }

    public static void mostrarPremiosObtenidos(int[] nivelesDePremio, boolean[] complementario) {
        int totalGanado = 0;
        System.out.println("---------- PREMIOS OBTENIDOS -----------");
        for (int i = 0; i < nivelesDePremio.length; i++) {
            System.out.printf("Apuesta %d: %d Aciertos (%d€)\n", i + 1, nivelesDePremio[i], obtenerTotalPremio(nivelesDePremio, i, complementario));
            totalGanado += obtenerTotalPremio(nivelesDePremio, i, complementario);
        }
        System.out.printf("Total ganado: %d€\n", totalGanado);
    }

    public static int obtenerTotalPremio(int[] nivelesDePremio, int posicion, boolean[] complementario) {
        if (nivelesDePremio[posicion] == 0 && nivelesDePremio[posicion] == 1 && nivelesDePremio[posicion] == 2) {
            return 0;
        } else if (nivelesDePremio[posicion] == 3) {
            return 50;
        } else if (nivelesDePremio[posicion] == 4) {
            return 500;
        } else if (nivelesDePremio[posicion] == 5) {
            if (complementario[posicion]) {
                return 50000;
            }
            return 10000;
        } else if (nivelesDePremio[posicion] == 6) {
            return 500000;
        }
        return 0;
    }

    public static void mostrarTotalAPagar(int numApuestas) {
        double precioAPagar = 0;
        if (numApuestas == 1) {
            precioAPagar = 1.25;
        } else if (numApuestas >= 2 && numApuestas <= 3) {
            precioAPagar = 1.5 * numApuestas;
        } else if (numApuestas >= 4 && numApuestas <= 5) {
            precioAPagar = 2 * numApuestas;
        } else if (numApuestas >= 6 && numApuestas <= 7) {
            precioAPagar = 12 * numApuestas;
        } else if (numApuestas == 8) {
            precioAPagar = 18 * numApuestas;
        }

        System.out.println("---- Total a Pagar ----");
        System.out.println("+----+----+----+----+----+");
        System.out.printf("|  Apuestas:   |    %d    |\n", numApuestas);
        System.out.println("+----+----+----+----+----+");
        System.out.printf("|  A  Pagar    |  %.2f€  |\n", precioAPagar);
        System.out.println("+----+----+----+----+----+");
    }

    public static int generarAleatorio() {

        return ((int) (Math.random() * ((49 - 1) + 1) + 1));
    }

    public static int comprobacionNumApuestas() {
        int num = 0;
        do {
            num = pedirEnteroAUsuario();
            if (num >= 1 && num <= 8) {
                System.out.println("");
                System.out.println("");
                System.out.println("");
                return num;
            } else {
                System.out.println("Apuestas incorrectas. Introdúcelas de nuevo.");
            }
        } while (num >0 || num <9);
        return num;
    }

    public static int pedirEnteroAUsuario() {
        do {
            System.out.print("¿Cuántas apuestas quieres realizar?: ");
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            System.out.println("Apuestas incorrectas. Introdúcelas de nuevo.");
            scanner.next();
        } while (true);
    }

    public static void ordenarArray(int[] array) {

        for (int i = 0; i < array.length - 2; i++) {
            int indiceElementoMenor = i;
            for (int j = i + 1; j < array.length - 1; j++) {
                if (array[j] < array[indiceElementoMenor]) {
                    indiceElementoMenor = j;
                }

            }
            if (indiceElementoMenor != i) {
                int aux = array[indiceElementoMenor];
                array[indiceElementoMenor] = array[i];
                array[i] = aux;
            }
        }
    }
}
