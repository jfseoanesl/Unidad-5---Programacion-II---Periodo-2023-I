/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Excepciones.*;
import Modelo.Computador;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jairo F
 */
public class ImpArchivoTexto implements IComputadorDao {

    private File archivo;
    private FileWriter modoEscritura;
    private Scanner modoLectura;

    public ImpArchivoTexto() {
        this("Registro.pc");
    }

    public ImpArchivoTexto(String ruta) {
        this.archivo = new File(ruta);
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
     * @return the modoEscritura
     */
    public FileWriter getModoEscritura() {
        return modoEscritura;
    }

    /**
     * @param modoEscritura the modoEscritura to set
     */
    public void setModoEscritura(FileWriter modoEscritura) {
        this.modoEscritura = modoEscritura;
    }

    /**
     * @return the modoLectura
     */
    public Scanner getModoLectura() {
        return modoLectura;
    }

    /**
     * @param modoLectura the modoLectura to set
     */
    public void setModoLectura(Scanner modoLectura) {
        this.modoLectura = modoLectura;
    }

    @Override
    public void registrar(Computador c) throws ExcepcionArchivo {
        PrintWriter pw = null;
        try {
            this.modoEscritura = new FileWriter(archivo, true);
            pw = new PrintWriter(this.modoEscritura);
            pw.println(c.getDataFileFormat());

        } catch (IOException ioe) {
            throw new ExcepcionArchivo("El archivo en modo escritura no existe o no pude ser creado");
         
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
    
    private Computador cargarDatos(String[] data){
        int serial = Integer.valueOf(data[0]);
        String marca = data[1];
        double precio = Double.valueOf(data[2]);
        return new Computador(serial, marca, precio);
    }

    @Override
    public List<Computador> leer() throws ExcepcionArchivo {
        List<Computador> lista;
        try {
            this.modoLectura = new Scanner(this.archivo);
            lista = new ArrayList();
            while(this.modoLectura.hasNext()){
                String datos[] = this.modoLectura.nextLine().split(";");
                Computador c = this.cargarDatos(datos);
                lista.add(c);
            }
            return lista;
            
        } catch (FileNotFoundException ioe) {
            throw new ExcepcionArchivo("Error al abrir archivo en modo lectura, no existe");
        }
        finally{
            if(this.modoLectura!=null)
                this.modoLectura.close();
        }
       
    }

    @Override
    public Computador buscar(Computador c) throws ExcepcionArchivo {
        Computador buscado=null;
        try {
            this.modoLectura = new Scanner(this.archivo);
            while(this.modoLectura.hasNext()){
                String datos[] = this.modoLectura.nextLine().split(";");
                Computador aux = this.cargarDatos(datos);
                if(aux.getnSerie()==c.getnSerie()){
                    buscado=aux;
                    break;
                }
            }
            return buscado;
            
        } catch (FileNotFoundException ioe) {
            throw new ExcepcionArchivo("Error al abrir archivo en modo lectura, no existe");
        }
        finally{
            if(this.modoLectura!=null)
                this.modoLectura.close();
        }
    }
    
    private void renombrarArchivo(File tmp) throws IOException{
        if(!tmp.exists())
            throw new IOException("El archivo temporal no existe");
            
        if(!this.archivo.delete()){
            tmp.delete();
            throw new IOException("No es posible eliminar el archivo original");
        }
        
        if(!tmp.renameTo(this.archivo)){
            throw new IOException("No es posible renombrar el archivo temporal");
        }    
    }

    @Override
    public Computador eliminar(Computador c) throws ExcepcionArchivo {
        Computador eliminado=null;
        ImpArchivoTexto archivoTmp = new ImpArchivoTexto("Registro.tmp");
        try {
            this.modoLectura = new Scanner(this.archivo);
            while(this.modoLectura.hasNext()){
                String datos[] = this.modoLectura.nextLine().split(";");
                Computador aux = this.cargarDatos(datos);
                if(aux.getnSerie()!=c.getnSerie()){
                    archivoTmp.registrar(aux);
                }
                else{
                    eliminado = aux;
                }
            }
            this.modoLectura.close();
            this.renombrarArchivo(archivoTmp.archivo);
            return eliminado;
            
        } catch (FileNotFoundException ioe) {
            throw new ExcepcionArchivo("Error al abrir archivo en modo lectura, no existe");
        }
        catch(IOException e){
            throw new ExcepcionArchivo(e.getMessage());
        }
        finally{
            if(this.modoLectura!=null)
                this.modoLectura.close();
        }
    }
    

    public Computador eliminar_lote(Computador c) throws ExcepcionArchivo {
        Computador eliminado=null;
        List<Computador> noEliminados = new ArrayList();
        try {
            this.modoLectura = new Scanner(this.archivo);
            while(this.modoLectura.hasNext()){
                String datos[] = this.modoLectura.nextLine().split(";");
                Computador aux = this.cargarDatos(datos);
                if(aux.getnSerie()!=c.getnSerie()){
                   noEliminados.add(c);
                }
                else{
                    eliminado = aux;
                }
            }
            this.modoLectura.close();
            
            this.modoEscritura = new FileWriter(this.archivo,false);
            PrintWriter pw = new PrintWriter(this.modoEscritura);
            for(Computador pc: noEliminados){
                pw.println(pc.getDataFileFormat());
            }
            pw.close();
            this.modoEscritura.close();
//            this.renombrarArchivo(archivoTmp.archivo);
            return eliminado;
            
        } catch (FileNotFoundException ioe) {
            throw new ExcepcionArchivo("Error al abrir archivo en modo lectura, no existe");
        }
        catch(IOException e){
            throw new ExcepcionArchivo(e.getMessage());
        }
        
    }

}
