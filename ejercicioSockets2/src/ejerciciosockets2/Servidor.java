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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jeans
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Map<String,String> diccionario = new HashMap<>();
        diccionario.put("string", "1");
        diccionario.put("int", "2");
        diccionario.put("double", "3");
        diccionario.put("boolean", "4");
        diccionario.put("date", "5");
        diccionario.put("sql", "6");
        diccionario.put("localdate", "7");
        diccionario.put("array", "8");
        diccionario.put("textfield", "9");
        diccionario.put("spinner", "10");
        ServerSocket server;
        Socket socket;
        int puerto = 8080;
        DataOutputStream salida;
        DataInputStream entrada;
        BufferedReader teclado;
        String opc;
        try {
            server = new ServerSocket(puerto);
            System.out.println("Conectado");
            while (true) {
                socket = server.accept();
                entrada = new DataInputStream(socket.getInputStream());
                salida = new DataOutputStream(socket.getOutputStream());
                String clave = entrada.readUTF().toLowerCase();
                System.out.println("Cliente: Clave seleccionada: "+clave);
                System.out.println("pasé");
                System.out.println("clave busqueda"+diccionario.get(clave));
                if(diccionario.get(clave)==null){
                    System.out.println("entre");
                    salida.writeUTF("Servidor: Su opción no existe en el diccionario");
                }else{
                    System.out.println("entré");
                    salida.writeUTF("Servidor: Su respuesta es:"+diccionario.get(clave));
                    System.out.println("¿Deseas cerrar el Servidor? Si/No");
                    teclado = new BufferedReader(new InputStreamReader(System.in));
                    opc = teclado.readLine();
                    if(opc.equalsIgnoreCase("Si")){
                        System.out.println("Cerrando Servidor...");
                        socket.close();
                        break;
                    }
                    
                }  
            }

        } catch (Exception e) {
            System.out.println("error");
        }    }
    
}
