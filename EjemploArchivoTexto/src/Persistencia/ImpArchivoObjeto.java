/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Excepciones.ExcepcionArchivo;
import Modelo.Computador;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Jairo F
 */
public class ImpArchivoObjeto implements IComputadorDao {

    private File archivo;
    private FileInputStream modoLectura;
    private FileOutputStream modoEscritura;

    public ImpArchivoObjeto() {
        this("RegistroPC.bin");
    }

    public ImpArchivoObjeto(String path) {
        this.archivo = new File(path);
    }

    private void guardarArchivo(List<Computador> lista) throws ExcepcionArchivo {
        try {
            this.modoEscritura = new FileOutputStream(this.archivo);
            ObjectOutputStream oos = new ObjectOutputStream(this.modoEscritura);
            oos.writeObject(lista);
            oos.close();

        } catch (FileNotFoundException e) {
            throw new ExcepcionArchivo("Erro al abrir archivo de objetos, no existe");
        } catch (SecurityException e) {
            throw new ExcepcionArchivo("No tiene acceso para el archivo en modo escritura");
        } catch (NullPointerException e) {
            throw new ExcepcionArchivo("EL manejador de archivo en escritura en Null");
        } catch (IOException e) {
            throw new ExcepcionArchivo("Error al escribir en el archivo");
        }

    }

    private List<Computador> leerArchivo() throws ExcepcionArchivo {
        
        if(!this.archivo.exists()){
            return new ArrayList<Computador>();
        }
        
        try {
            this.modoLectura = new FileInputStream(this.archivo);
            ObjectInputStream ois = new ObjectInputStream(this.modoLectura);
            List<Computador> lista = (List<Computador>)ois.readObject();
            ois.close();
            return lista;

        } catch (FileNotFoundException e) {
            throw new ExcepcionArchivo("Erro al abrir archivo de objetos en modo lectura , no existe");
        } catch (SecurityException e) {
            throw new ExcepcionArchivo("No tiene acceso para el archivo en modo lectura");
        } catch (StreamCorruptedException e) {
            throw new ExcepcionArchivo("Error con el flujo de datos de cabecera del objeto");
        } catch (NullPointerException e) {
            throw new ExcepcionArchivo("EL manejador de archivo en lectura en Null");
        } catch (IOException e) {
            throw new ExcepcionArchivo("Error al leer en el archivo");
        }
        catch(ClassNotFoundException e){
            throw new ExcepcionArchivo("Error, el objeto leido del archivo no tiene clase definida");
        }

    }

    @Override
    public void registrar(Computador c) throws ExcepcionArchivo {
        List<Computador> lista = this.leerArchivo();
        lista.add(c);
        this.guardarArchivo(lista);

    }

    @Override
    public List<Computador> leer() throws ExcepcionArchivo {
            return this.leerArchivo();
    }

    @Override
    public Computador buscar(Computador c) throws ExcepcionArchivo {
          List<Computador> lista = this.leerArchivo();
          for(Computador b: lista){
              if(b.getnSerie()==c.getnSerie()){
                  return b;
              }
          }
          return null;
    }

    @Override
    public Computador eliminar(Computador c) throws ExcepcionArchivo {
        List<Computador> lista = this.leerArchivo();
        Iterator<Computador>i = lista.iterator();
        Computador eliminado=null;
        while(i.hasNext()){
            Computador leido = i.next();
            if(leido.getnSerie()==c.getnSerie()){
                eliminado = leido;
                i.remove();
            }
        }
        this.guardarArchivo(lista);
        return eliminado;
        
    }

    /**
     * @return the archivo
     */
    public File getArchivo() {
        return archivo;
    }

    /**
     * @param archivo the archivo to set
     */
    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    /**
     * @return the modoLectura
     */
    public FileInputStream getModoLectura() {
        return modoLectura;
    }

    /**
     * @param modoLectura the modoLectura to set
     */
    public void setModoLectura(FileInputStream modoLectura) {
        this.modoLectura = modoLectura;
    }

    /**
     * @return the modoEscritura
     */
    public FileOutputStream getModoEscritura() {
        return modoEscritura;
    }

    /**
     * @param modoEscritura the modoEscritura to set
     */
    public void setModoEscritura(FileOutputStream modoEscritura) {
        this.modoEscritura = modoEscritura;
    }

    @Override
    public Computador eliminar_lote(Computador c) throws ExcepcionArchivo {
        return null;
    }

}
