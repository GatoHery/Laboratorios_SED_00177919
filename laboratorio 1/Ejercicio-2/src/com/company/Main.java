package com.company;

import java.util.Scanner;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class Main {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        //datos usados
        String mensaje = "";
        String llave1 = "";
        String llave2 = "";
        int opcion = 0;
        byte[] aux = new byte[0];

        do {
            System.out.println(menu());
            opcion = scan.nextInt(); scan.nextLine();

            switch (opcion){
                case 1:
                    System.out.println("ingre un texto a encriptar");
                    mensaje = scan.nextLine();

                    System.out.println("Ingrese su primera llave de 8 digitos");
                    llave1= scan.nextLine();

                    System.out.println("Ingrese su segunda llave de 8 digitos");
                    llave2= scan.nextLine();

                    break;

                case 2:
                    aux = cifrar(mensaje, llave1);
                    System.out.println("su mensaje ha sido encriptado");
                    break;
                case 3:
                    String txt = descifrar(aux, llave2);
                    System.out.println("Mensaje descencriptado");
                    System.out.println(txt);
                    break;
            }
        }while (opcion != 0);
    }

    public static byte[] cifrar(String mensaje, String llave) throws Exception
    {
        SecretKeyFactory secretFactory = SecretKeyFactory.getInstance("DES");
        DESKeySpec keyspec = new DESKeySpec(llave.getBytes());
        SecretKey secretKey = secretFactory.generateSecret(keyspec);

        Cipher cifrado = Cipher.getInstance("DES");
        cifrado.init(Cipher.ENCRYPT_MODE, secretKey);

        String solucion1 = null;
        String solucion2 = "";

            solucion1 = new String(cifrado.update(mensaje.getBytes()), "ISO-8859-1");
            solucion2 = new String(cifrado.doFinal(), "ISO-8859-1");

        return (solucion1+solucion2).getBytes("ISO-8859-1");
    }

    public static String descifrar(byte[] bufferCifrado, String clave) throws Exception
    {
        SecretKeyFactory secretFactory = SecretKeyFactory.getInstance("DES");
        DESKeySpec keyspec = new DESKeySpec(clave.getBytes());
        SecretKey secretKey = secretFactory.generateSecret(keyspec);

        Cipher cifrado = Cipher.getInstance("DES");
        cifrado.init(Cipher.DECRYPT_MODE, secretKey);

        String solucion1 = null;
        String solucion2 = "";

            solucion1 = new String(cifrado.update(bufferCifrado), "ISO-8859-1");
            solucion2 = new String(cifrado.doFinal(), "ISO-8859-1");

        return solucion1 + solucion2;
    }

    static String menu(){
        return "\nMenu Principal\n" + "1.Crear claves\n" + "2.Cifrar mensajes\n" + "3.Decifrar mensaje\n" +
                "0.salir\n" + "Su opcion: ";
    }

}
