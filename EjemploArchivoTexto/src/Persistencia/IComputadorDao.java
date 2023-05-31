/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import Excepciones.*;
import Modelo.Computador;
import java.util.List;

/**
 *
 * @author Jairo F
 */
public interface IComputadorDao {
    
    void registrar(Computador c) throws ExcepcionArchivo;
    List<Computador> leer() throws ExcepcionArchivo;
    Computador buscar(Computador c) throws ExcepcionArchivo;
    Computador eliminar(Computador c)throws ExcepcionArchivo;   
    Computador eliminar_lote(Computador c)throws ExcepcionArchivo;   
    
}
