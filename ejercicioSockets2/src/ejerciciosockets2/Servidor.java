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
        String[] array = {"Un array, es un tipo de dato estructurado que permite almacenar un conjunto de datos.", "Es homogeneo, es decir, todos ellos del mismo tipo y relacionados.","A los datos almacenados en un array se les denomina elementos"};
        String[] array2 = {"Las string son una sucesión o cadena de caracteres. Para definirlas podemos usar comillas simples o dobles."};
        String[] array3 = {"El tipo de dato int es un entero de 32 bits complemento a dos", "Su valor mínimo es -2,147,483,648 y el máximo 2,147,483,647"};
        String[] array4 = {"El tipo de dato double es un dato en coma flotante IEEE 754 de 64 bits y precisión doble."};
        String[] array5 = {"El tipo de dato boolean solamente tiene dos valores posibles: true (verdadero) y false (falso)", "Utilice este tipo de datos como conmutadores para la evaluación de condiciones verdadero/falso.","Utilice este tipo de datos como conmutadores para la evaluación de condiciones verdadero/falso."};
        String[] array6 = {" El tipo Date permite manipular fechas fácilmente. Este tipo convierte las fechas de forma casi automática"};
        String[] array7 = {"Structured Query Language.", "Es un lenguaje de programación para almacenar y procesar información en una base de datos relacional."};
        String[] array8 = {"LocalDate proporciona muchos métodos útiles para manipular y consultar fechas, como sumar o restar días, meses o años, obtener el día de la semana o verificar si una fecha es anterior o posterior a otra fecha."};
        String[] array9 = {"El componente TextField es un campo de texto editable por el usuario"};
        String[] array10 = {"Juanito", "Pepito"};

        Map<String, String[]> diccionario = new HashMap<>();
        diccionario.put("string", array2);
        diccionario.put("int", array3);
        diccionario.put("double", array4);
        diccionario.put("boolean", array5);
        diccionario.put("date", array6);
        diccionario.put("sql", array7);
        diccionario.put("localdate", array8);
        diccionario.put("array", array);
        diccionario.put("textfield", array9);
        diccionario.put("spinner", array10);
        ServerSocket server;
        Socket socket;
        int puerto = 8080;
        DataOutputStream salida;
        DataInputStream entrada;
        BufferedReader teclado = null;
        String opc;
        try {
            server = new ServerSocket(puerto);
            System.out.println("Conectado");
            
            while (true) {
                socket = server.accept();
                entrada = new DataInputStream(socket.getInputStream());
                salida = new DataOutputStream(socket.getOutputStream());
                salida.writeUTF("CONEXCION EXITOSA");
                String clave = entrada.readUTF().toLowerCase();
                if (diccionario.get(clave) == null) {
                    salida.writeUTF("Servidor: Su opción no existe en el diccionario");
                } else {
                    String[] resul = (String[]) diccionario.get(clave);
                    salida.writeUTF(String.join("\n", array));
                    salida.writeUTF("Servidor: Su respuesta es:" + diccionario.get(clave));
                    System.out.println("¿Deseas cerrar el Servidor? Si/No");
                    teclado = new BufferedReader(new InputStreamReader(System.in));
                    opc = teclado.readLine();
                    if (opc.equalsIgnoreCase("Si")) {
                        System.out.println("Cerrando Servidor...");
                        salida.writeUTF("SERVIDOR CERRADO");
                        socket.close();
                        break;
                    }

                }
            }

        } catch (Exception e) {
            System.out.println("error");
        }
    }

}
