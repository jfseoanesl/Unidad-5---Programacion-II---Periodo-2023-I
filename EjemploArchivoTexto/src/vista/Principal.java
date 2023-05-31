/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import Excepciones.*;
import Modelo.Computador;
import Modelo.ListaComputador;
import java.util.List;

/**
 *
 * @author Jairo F
 */
public class Principal {

    public static void main(String[] args) {
       
        
        // creacion de objetos 
        Computador pc1 = new Computador(123, "HP", 2000);
        Computador pc2 = new Computador(345, "Asus", 4000);
        Computador pc3 = new Computador(567, "Lenovo", 6000);

        // Intancia de objeto de logica de aplicacion
        ListaComputador catalogo = new ListaComputador();

        // 1. escritura en archivo
        try {
            catalogo.registrar(pc1);
            catalogo.registrar(pc2);
            catalogo.registrar(pc3);
            System.out.println("Caso 1: Registro de datos en archivo");
        } catch (ExcepcionArchivo e) {
            System.out.println("Excepcion: " + e.getMessage());
        }
        
        // 2. Lectura de archivo
        System.out.println("-------------------------------------");
        
        System.out.println("Caso 2: Lectura de archivo\n");
        try {
            List<Computador> lista = catalogo.leer();
            for (Computador c : lista) {
                System.out.println(c);
            }
        } catch (ExcepcionArchivo e) {
            System.out.println("Excepcion: " + e.getMessage());
        }
        
        // 3. Busqueda de registro por criterio
        System.out.println("-------------------------------------");
        System.out.println("Caso 3: Busqueda por serial 123\n");
        try {
            Computador busqueda = catalogo.buscar(new Computador(123));
            if(busqueda!=null){
                System.out.println("Resultado: Encontrado");
                System.out.println(busqueda);
            }
            else{
                System.out.println("Resultado: No encontrado");
            }
        } catch (ExcepcionArchivo e) {
            System.out.println("Excepcion: " + e.getMessage());
        }
        
        //4. Eliminacion de registro por criterio
        System.out.println("-------------------------------------");
        
        System.out.println("Caso 4: Eliminacion por serial 123\n");
        try {
            Computador eliminado = catalogo.eliminar_lote(new Computador(123));
            if(eliminado!=null){
                System.out.println("Resultado: Eliminado");
                System.out.println(eliminado);
            }
            else{
                System.out.println("Resultado: No encontrado");
            }
        } catch (ExcepcionArchivo e) {
            System.out.println("Excepcion: " + e.getMessage());
        }
        
        //5. Lectura de archivo post - eliminacion
        System.out.println("-------------------------------------");
        
        System.out.println("Caso 2: Lectura de archivo\n");
        try {
            List<Computador> lista = catalogo.leer();
            for (Computador c : lista) {
                System.out.println(c);
            }
        } catch (ExcepcionArchivo e) {
            System.out.println("Excepcion: " + e.getMessage());
        }
    }
}
