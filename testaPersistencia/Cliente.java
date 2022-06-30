/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 


/*
 * Uma Classe que suporta um cliente.
 *
 */


/**
    O objecto cliente suporta os dados de um cliente e operações básicas de alteração e criação.
*/
import java.util.*;
import java.io.*;

public class Cliente implements Cloneable, Comparable<Cliente>, Serializable{

    // Atribuitos do Cliente
    private String nome;
    private int nif;
    private String morada;


    /**
     * Constructores  - Os três tipos : vazio , partes e cópia
     */

    /** Construtor vazio */
   public Cliente(){
       this.nome = "" ;
       this.nif = 0 ;
       this.morada = "" ;
    }

   /** Construtor a partir das partes */
    public Cliente(String nome , int nif, String morada){
       this.nome = nome;
       this.nif = nif;
       this.morada = morada;
    }

    /** Construtor de cópia */
    public Cliente(Cliente cliente){
        this.nome = cliente.getNome();
        this.nif = cliente.getNif();
        this.morada = cliente.getMorada();
    }



   /**
    *  set's e get's para os atribuitos do cliente
    */

   /** setCliente é um modificador de todos os atribuitos */
    public void setCliente(String nome, int nif, String morada){
       this.nome = nome;
       this.nif = nif;
       this.morada = morada;
    }

    public void setNome(String nome){ this.nome = nome; }
    public String getNome() { return this.nome; }
    public void setMorada(String morada) { this.morada = morada; }
    public String getMorada() { return this.morada; }
    public void setNif(int nif) { this.nif = nif; }
    public int getNif() { return this.nif; }

        /**
         * Implementação das interfaces Cloneable e Comparable e o método toString, equals
         */
   public Cliente clone(){ return new Cliente (this); }

   /**  Comparaveis através do número do seu número de contribuinte */
    public int compareTo(Cliente cliente){ return ( this.nif - cliente.getNif() ); }

    /**  Cliente resultante : "Nome ;nif;morada; */
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(this.nome + ";");
        s.append(this.nif +";");
        s.append(this.morada + ";");

        return s.toString();
    }

    /**  Compara um cliente atraves dos seus campos */
    public boolean equals(Cliente cliente) {
        return (
                this.nome == cliente.getNome() &&
                this.morada == cliente.getMorada() &&
                this.nif == cliente.getNif() );
    }
    /**
     * Funções de serialização , write and read
     */

    public void write(String path) throws IOException{
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
            out.writeObject(this);
            out.flush();
            out.close();
        }
        catch (IOException e) { throw e; }
    }

    public void write(ObjectOutputStream out ) throws IOException{
       try {out.writeObject(this);}
       catch (IOException e) { throw e; }

       out.flush();

    }

    public void read(ObjectInputStream in) throws IOException, ClassNotFoundException{
        try {
            Cliente tmp = (Cliente) in.readObject();
            this.nome = tmp.nome;
            this.nif = tmp.nif;
            this.morada = tmp.morada;
        }
        catch (IOException e ) { throw e;}
        catch (ClassNotFoundException e ) { throw e;}
    }

    public void read(String path) throws IOException, ClassNotFoundException{
        try{
            ObjectInputStream in = new ObjectInputStream( new FileInputStream(path));
            Cliente tmp = (Cliente) in.readObject();
            this.nome = tmp.nome;
            this.nif = tmp.nif;
            this.morada = tmp.morada;
        }
        catch (IOException e ) { throw e;}
        catch (ClassNotFoundException e ) { throw e ; }
    }
}