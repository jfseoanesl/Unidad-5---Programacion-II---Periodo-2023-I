/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Excepciones.*;
import Persistencia.IComputadorDao;
import Persistencia.ImpArchivoObjeto;
import Persistencia.ImpArchivoTexto;
import java.util.List;

/**
 *
 * @author Jairo F
 */
public class ListaComputador  implements IComputadorDao {
    private IComputadorDao datos;

    public ListaComputador() {
        this.datos = new ImpArchivoTexto();
//        this.datos = new ImpArchivoObjeto();
    }

    
    
    @Override
    public void registrar(Computador c) throws ExcepcionArchivo {
        this.datos.registrar(c);
    }

    @Override
    public List<Computador> leer() throws ExcepcionArchivo {
        return this.datos.leer();
    }

    @Override
    public Computador buscar(Computador c) throws ExcepcionArchivo {
        return this.datos.buscar(c);
    }

    @Override
    public Computador eliminar(Computador c) throws ExcepcionArchivo {
        return this.datos.eliminar(c);
    }
    
    public Computador eliminar_lote(Computador c) throws ExcepcionArchivo {
        return this.datos.eliminar(c);
    }
    
}
