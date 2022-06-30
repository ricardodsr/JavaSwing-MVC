
 

/**
 * Classe Clientes , implementa uma HashMap de cliente .
 */


import java.io.*;
import java.util.*;

public class Clientes extends Observable implements Serializable{
    /** Atribuitos de Clientes */
    private HashMap<String,Cliente> nomes;
    private HashMap<Integer, Cliente> nifs;
    /**
     * Construtores
     */

    /** Construtor vazio */
    public Clientes(){
        this.nomes = new HashMap<String,Cliente>();
        this.nifs  = new HashMap<Integer, Cliente> ();
    }

    /** Construtor das partes , recebe uma collec��o de clientes */
    public Clientes(Collection<Cliente> clientes){
        for (Cliente cliente : clientes){
            Cliente newCliente = cliente.clone();
            this.nomes.put(newCliente.getNome(), newCliente);
            this.nifs.put(newCliente.getNif(), newCliente);
        }

    }

    /** Construtor de c�pia , clona cada um dos clientes */
    public Clientes(Clientes clientes){this( clientes.getValues());}


    /**
     * clone(), toString, equals()
     */
    public Clientes clone(){ return new Clientes(this.nomes.values()); }
    public String toString(){ return ("Total de clientes : "+ this.nomes.size()); }

    /** Verifica se os objectos c�pias dos mesmos clientes */
    public boolean equals(Clientes clientes){
        boolean ok = true;
        Iterator<Cliente> it = clientes.getValues().iterator();
        while(it.hasNext()){
            if (this.contains( it.next())  == true  ) return false;
        }
        return true;
    }

    /** get's e set's */

    /** getValues retorna uma cole�ao de cliente */
    public Collection<Cliente> getValues(){ return this.nomes.values();}

    /** Set funciona como um construtor mas altera o estado da base de dados
     *  Se houver uma entrada repetida ser� substituida pelo novo cliente
     */
    public void setValues(Collection<Cliente> clientes){
        for (Cliente cliente : clientes){
            Cliente newCliente = cliente.clone();
            this.nomes.put(newCliente.getNome(), newCliente);
            this.nifs.put(newCliente.getNif(), newCliente);
        }
        this.setChanged();
        this.notifyObservers();
    }

    /** Adiciona um cliente , lan�a uma excep�ao se um cliente com o mesmo nome ou nif ja existir */
    public void add(Cliente cliente)throws ClientesException{
        if (this.contains(cliente.getNome()))
            throw new ClientesException("Cliente " + cliente.getNome() + "j� existe  e por isso n�o foi adicionado");
        else
            if (this.contains(cliente.getNif()))
                throw new ClientesException("Cliente " + cliente.getNif() + "j� existe e por isso n�o foi adicionado ");
            else{
                Cliente tmp = cliente.clone();
                this.nomes.put(cliente.getNome(), tmp);
                this.nifs.put(cliente.getNif(), tmp);
                this.setChanged();
                this.notifyObservers();
            }
    }

    /** Adiciona um cliente , se j� existir algum com o mesmo nome ou nif , este � substituido */
    public void replace(Cliente cliente, String nome)throws ClientesException{
     Cliente tmp = this.nomes.remove(nome);
     if (tmp != null){
           this.nifs.remove(tmp.getNif());
           this.nomes.put(cliente.getNome(), cliente);
           this.nifs.put(cliente.getNif(), cliente);
           this.setChanged();
           this.notifyObservers();
        }
     else
        throw new ClientesException("Cliente " + nome + " n�o existe ");

     }
     public void replace(Cliente cliente, int nif)throws ClientesException{
     Cliente tmp = this.nifs.remove(nif);
     if (tmp != null){
           this.nomes.remove(tmp.getNome());
           this.nomes.put(cliente.getNome(), cliente);
           this.nifs.put(cliente.getNif(), cliente);
           this.setChanged();
           this.notifyObservers();
        }
     else
        throw new ClientesException("Cliente " + nif + " n�o existe ");
     }

    /** Remove um cliente , se este existir . Se este nao existir � lan�ada uma excep��o
     * A pesquisa � realizada por nome do cliente.
     */

    public Cliente  remove(Cliente cliente) throws ClientesException{
        if (this.nomes.remove(cliente.getNome()) != null){
            this.setChanged();
            this.notifyObservers();
            return this.nifs.remove(cliente.getNif());
        }
        else  throw new ClientesException("Cliente \" " + cliente.getNome() + "\" n�o existe ");

    }

    /** Verifica se um cliente existe no objecto. A pesquisa � efectuada por nome */
    public boolean contains(Cliente cliente){
        return this.nomes.containsKey(cliente.getNome());
    }
    /** Verifica se um cliente existe */
    public boolean contains(String nome){
        return this.nomes.containsKey(nome);
    }
    /** Verifica se um cliente existe */
    public boolean contains(int num){
        return this.nifs.containsKey(num);
    }

    /** Retorna um cliente , pesquisa por nome , lan�a ClienteNotFoundException se este nao existir */
    public Cliente get(String key) throws ClientesException{
         Cliente tmp = this.nomes.get(key);
        if (tmp != null)  return tmp.clone();
        else  throw new ClientesException("Cliente \" " + key + "\" n�o existe ");
    }

    /** Retorna um cliente , pesquisa por nif , lan�a ClienteNotFoundException se este nao existir */
    public Cliente get(int key) throws ClientesException{
         Cliente tmp = this.nifs.get(key);
        if (tmp != null)  return tmp.clone();
        else  throw new ClientesException("Cliente \" " + key + "\" n�o existe ");
    }

    /** Write cliente */
     public void writeBinary(String path) throws IOException{
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
            out.writeObject(this);
            out.flush();
            out.close();
        }
        catch (IOException e) { throw e; }
    }

    public void writeBinary(ObjectOutputStream out ) throws IOException{
       try {out.writeObject(this);}
       catch (IOException e) { throw e; }
       out.flush();
    }

    
    public void writeText(BufferedWriter out ) throws IOException{
        try {
            for (Cliente cliente : this.nomes.values() ){
                StringBuffer s = new StringBuffer();
                 s.append(cliente.getNome() + ";");
                 s.append(cliente.getNif() +";");
                 s.append(cliente.getMorada() + ";\n");
                 out.write(s.toString());
                }
                out.close();
            }catch ( IOException e ) { throw e; };
        }

     public void writeText(String path) throws IOException{
        try {
            BufferedWriter out = new  BufferedWriter(new FileWriter(path));
            for (Cliente cliente : this.nomes.values() ){
                StringBuffer s = new StringBuffer();
                 s.append(cliente.getNome() + ";");
                 s.append(cliente.getNif() +";");
                 s.append(cliente.getMorada() + ";\n");
                 out.write(s.toString());
                }
                out.close();
            }catch ( IOException e ) { throw e; };

        }
    }

