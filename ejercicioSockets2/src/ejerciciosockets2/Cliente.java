/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciosockets2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author jeans
 */
public class Cliente {

    public static void main(String[] args) {
        String ipPrincipal = " 192.168.100.6", ipSecundario = "", puertoVal = "";
        int puertoPrincipal = 8080, puertoSecundario = 0;
        DataInputStream in;
        DataOutputStream out;
        BufferedReader teclado = null;
        boolean validador = true;
        try {
            while (validador == true) {
                System.out.println("INGLRE LA IP ");
                teclado = new BufferedReader(new InputStreamReader(System.in));
                ipSecundario = teclado.readLine();
                System.out.println("INGLRE EL PUERTO ");
                teclado = new BufferedReader(new InputStreamReader(System.in));
                puertoVal = teclado.readLine();
                if (ipSecundario.equals("") || puertoVal.equals("") ) {
                    System.out.println("CAMPOS VACIOS");
                } else {
                    if (ipSecundario.equals("192.168.100.6") && puertoVal.equals("8080")) {
                        puertoSecundario= Integer.parseInt(puertoVal);
                        validador = false;
                    } else {
                        System.out.println("Error de conexion. Ingrese nuevamente!!!!");
                        validador= true;
                    }
                }
            }
            Socket sc = new Socket(ipSecundario, puertoSecundario);
            System.out.println("-----INGRESE UNA PALABR------ ");
            while (true) {
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());

                String mensaje1 = teclado.readLine();
                if (mensaje1.matches("^[a-zA-Z]+$")) {
                    
                    out.writeUTF(mensaje1);
                    String mensaje = in.readUTF();
                    System.out.println(mensaje);
                } else {
                    System.out.println("NO SE ACEPTA NUMEROS");
                }
            }

        } catch (Exception e) {
            System.out.println("ERROR AL CONECTARSE");
            System.out.println(e.toString());
        }
    }

}
