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
    public static void main(String[] args){
        String ipPrincipal = " 192.168.100.6",ipSecundario="";
        int puertoPrincipal = 8080 , puertoSecundario=0;
        DataInputStream in;
        DataOutputStream out;
        BufferedReader teclado = null;
        boolean c = true;
        while (c) {
            try {
                while(ipSecundario.equals("") || puertoSecundario  0){
                    
                }
                System.out.println("INGLRE LA IP ");
                teclado = new BufferedReader(new InputStreamReader(System.in));
                ipSecundario = teclado.readLine();
                if (ipSecundario.equals("")) {
                                        
                }
                
                System.out.println("INGLRE EL PUERTO ");
                teclado = new BufferedReader(new InputStreamReader(System.in));
                puertoSecundario = Integer.parseInt(teclado.readLine());
                Socket sc = new Socket(HOST, PUERTO);
                c=false;
                System.out.println("Ingrese la ");
                while (true) {
                    in = new DataInputStream(sc.getInputStream());
                    out = new DataOutputStream(sc.getOutputStream());
                    teclado = new BufferedReader(new InputStreamReader(System.in));
                    String mensaje1 = teclado.readLine();
                    if (mensaje1.matches("^[a-z]+[A-Z]+S$")) {
                        System.out.println("entre");
                        out.writeUTF(mensaje1);
                        String mensaje = in.readUTF();
                        System.out.println(mensaje);                   
                   
                    } else {
                        System.out.println("Incorrecto");
                    }

                }

            } catch (Exception e) {
                System.out.println("ERROR AL CONECTARSE");
            }
        }
    }
}
