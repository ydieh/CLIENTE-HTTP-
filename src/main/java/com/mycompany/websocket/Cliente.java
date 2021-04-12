/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.websocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Crear un cliente HTTP basado en sockets.
 *
 * @author ydieh
 */
public class Cliente {

    public static void main(String[] args) {
        
        final String DIRECCION = "https://google.com/";
        
        URL url;
        
        try {
            url = new URL(DIRECCION);
        } catch (MalformedURLException e) {
            System.err.println("Error en la dirección. NO está bien formada.");
            return;
        }
        
        String host = url.getHost();
        final int PUERTO = 80;
        
        try(Socket socket = new Socket(host, PUERTO)){
            
           PrintStream out = new PrintStream( socket.getOutputStream() );
            BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );

           
            out.println( "GET " + url.getPath() + " HTTP/1.0" );
            out.println();
            

            // Leemos los datos
            String line = in.readLine();
            while( line != null )
            {
                System.out.println( line );
                line = in.readLine();
            }

            
            in.close();
            out.close();
            socket.close();
            
        } catch(UnknownHostException e){
            System.err.println("No se encontró el servidor: " + e.getMessage());
            
        } catch(IOException e){
            System.err.println("Error de entrada/salida: " + e.getMessage());
        }
    }
}