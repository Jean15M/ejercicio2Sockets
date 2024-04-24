/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciosockets2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 *
 * @author jeans
 */
public class Cliente {

    public static void main(String[] args) {
        String ipPrincipal = "127.0.0.1", ipSecundario = "", puertoVal = "";
        int puertoPrincipal = 8080, puertoSecundario = 0;
        DataInputStream in;
        DataOutputStream out;
        BufferedReader teclado = null;
        boolean validador = true;
        try {
            while (validador == true) {
                System.out.println("INGRESE LA IP ");
                teclado = new BufferedReader(new InputStreamReader(System.in));
                ipSecundario = teclado.readLine();
                System.out.println("INGRESE EL PUERTO ");
                teclado = new BufferedReader(new InputStreamReader(System.in));
                puertoVal = teclado.readLine();
                if (ipSecundario.equals("") || puertoVal.equals("")) {
                    System.out.println("CAMPOS VACIOS");
                } else {
                    if (ipSecundario.equals(ipPrincipal) && puertoVal.equals("8080")) {
                        puertoSecundario = Integer.parseInt(puertoVal);
                        validador = false;
                    } else {
                        System.out.println("Error de conexion. Ingrese nuevamente!!!!");
                        validador = true;
                    }
                }
            }
            try {
                //Socket sc = new Socket(ipSecundario, puertoSecundario);
                while (true) {
                    Socket sc = new Socket(ipSecundario, puertoSecundario);
                    //sc.setKeepAlive(true);
                    System.out.println("CONECTADO CORRECTAMENTE CON EL SERVIDOR");
                    System.out.println("-----INGRESE UNA PALABRA------ ");
                    in = new DataInputStream(sc.getInputStream());
                    out = new DataOutputStream(sc.getOutputStream());
                    String mensaje1 = teclado.readLine();
                    out.writeUTF(mensaje1);
                    String mensaje = in.readUTF();
                    System.out.println(mensaje);

                }

            } catch (ConnectException e) {
                System.out.println("DESCONEXIÓN DEL SERVIDOR");
            } catch (SocketTimeoutException e) {
                System.out.println("ERROR DE CONEXIÓN");
            }

        } catch (Exception e) {
            System.out.println("ERROR AL CONECTARSE");
        }
    }

}
