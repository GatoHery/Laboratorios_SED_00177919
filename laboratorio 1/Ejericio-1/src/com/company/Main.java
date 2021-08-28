package com.company;

import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        //datos utilizados
            int opcion = 0;
            int p = 0; //numero primo
            int q = 0; //numero primo
            double n = 0;
            int z = 0;
            int k = 0; //co-primo
            int j = 0; //llave privada
            int aux = 0;
            Long aux3;
            double aux4 = 0.0;
            int aux5 = 0;
            int e = 0; //tamano del mensaje
            double datofinal=0;
            String mensaje="";

            long result = 0;
            double temp = 0; //variable para evitar problemas con decimales
            int temp2 = 0; //variable anterior pero en su forma entera


        do {


            System.out.println(menu());
            opcion = scan.nextByte(); scan.nextLine();

            switch(opcion){
                case 1:
                    try {
                        System.out.println("ingrese el primer primo");
                        p = scan.nextByte(); scan.nextLine();

                        if(detectorDePrimos(p) == false){
                            throw new DataException("Ingrese otro numero");
                        }
                    }catch (DataException ex){
                        System.out.println(ex.getMessage());
                        break;
                    }

                    try{
                        System.out.println("ingrese el segundo primo");
                        q = scan.nextByte(); scan.nextLine();

                        if(detectorDePrimos(p) == false){
                            throw new DataException("Ingrese otro numero");
                        }
                    }catch (DataException ex){
                        System.out.println(ex.getMessage());
                        break;
                    }

                    n = p * q;
                    System.out.println("tu primera llave: " + n);
                    z = (p-1) * (q-1);

                    System.out.println("ingrese un numero co-primo de " + z);
                    k = scan.nextByte(); scan.nextLine();
                    System.out.println("tu segunda llave: " + k);

                    try{
                        System.out.println("ingrese un numero que deje residuo 1");
                        System.out.println("x / " + z);
                        aux = scan.nextByte(); scan.nextLine();

                        if (aux%z != 1) {
                            throw new DataException("Ingrese otro numero");
                        }

                        System.out.println("ingrese un numero j que cumpla la ecuacion");
                        System.out.println(k + " * " + "j" + " = " + aux);
                        j = scan.nextByte(); scan.nextLine();

                        if (k*j != aux) {
                            throw new DataException("Ingrese otro numero");
                        }

                    } catch (DataException ex) {
                        System.out.println(ex.getMessage());
                        break;
                    }

                    System.out.println("tu llave secreta: " + j);
                    break;

                case 2:
                    try{
                        if (k == 0 && n == 0) {
                            throw new DataException("no existen llaves en el sistema");
                        }
                    }catch (DataException ex){
                        System.out.println(ex.getMessage());
                    }

                    System.out.println("ingrese el mensaje");
                    mensaje = scan.nextLine();
                    e = mensaje.length();

                    result = e;
                    for (int i = 1; i <= (k-1); i++) {
                        result *= e;
                    }

                    aux3 = result;
                    temp = aux3 / n;
                    temp2 = (int) temp;

                    aux4 = temp - temp2; //extractor de los decimales
                    aux4 *= n;
                    aux5 = (int) Math.round(aux4);

                    System.out.println("Tu mensaje cifrado es: " + aux5);

                    break;

                case 3:
                    try{
                        if (aux5 == 0) {
                            throw new DataException("no puedes descifrar un mensaje no encriptado");
                        }
                    }catch (DataException ex){
                        System.out.println(ex.getMessage());
                    }

                    int llave = 0;
                    System.out.println("ingrese su llave privada: ");
                    llave = scan.nextInt(); scan.nextLine();

                    result = aux5;
                    for (int i = 1; i <= (llave-1); i++) {
                        result *= aux5;
                    }

                    aux3 = result;
                    temp = aux3 / n;
                    temp2 = (int) temp;
                    temp2 *= n;
                    datofinal = result - temp2;

                    System.out.println("su descifrado dio: " + datofinal);

                    if(datofinal == e){
                        System.out.println("acceso permitido");
                        System.out.println(mensaje);
                    }
                    else{
                        System.out.println("llave incorrecta acceso denegado");
                    }


                    break;

            }
        }
        while (opcion != 0);
    }

    static String menu(){
        return "\nMenu Principal\n" + "1.Crear claves\n" + "2.Cifrar mensajes\n" + "3.Decifrar mensaje\n" +
                "0.salir\n" + "Su opcion: ";
    }

    public static boolean detectorDePrimos(int num) {
        if (num == 0 || num == 1 || num == 4) {
            return false;
        }
        for (int x = 2; x < num / 2; x++) {
            if (num % x == 0)
                return false;
        }
        return true;
    }
}


