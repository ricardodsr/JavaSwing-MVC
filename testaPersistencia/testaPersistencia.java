        import java.util.*;
        import java.io.*;
        
 public class testaPersistencia {   
    
     public static void readInText(){
        Cronometro cron = new Cronometro(); 
        double stop;
        
        cron.start(); 
        readClientesInTextMode("c:\\clientes.csv"); 
        stop = cron.getTime(); 
        System.out.println(stop);   
    }
    
     public static void readInBinary(){
        Cronometro cron = new Cronometro(); 
        double stop;    
        cron.start(); 
        readClientesInBinary("c:\\clientes.binary"); 
        stop = cron.getTime(); 
        System.out.println(stop);   
    }
    
    public static void writeInBinary(){
        Clientes cliente = readClientesInTextMode("c:\\clientes.csv"); 
        try {  
            Cronometro cron = new Cronometro(); 
            double stop ; 
            cron.start(); 
            cliente.writeBinary("c:\\clientes.binary"); 
            stop = cron.getTime(); 
            System.out.println(stop); 
        }catch (Exception e ) { System.err.println(e.getMessage()); }
    
       
    }
       public static void writeInText(){
        Clientes cliente = readClientesInTextMode("c:\\clientes.csv"); 
        try {  
            Cronometro cron = new Cronometro(); 
            double stop ; 
            cron.start(); 
            cliente.writeText("c:\\clientes.texto"); 
            stop = cron.getTime(); 
            System.out.println(stop); 
        }catch (Exception e ) { System.err.println(e.getMessage()); }
    
       
    }
         
    /** lê os clientes de um ficheiro binário gravado através do método writeObject */
    private static  Clientes readClientesInBinary(String path){
        Clientes clientes= null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
            clientes = (Clientes ) in.readObject();
            in.close();
        }catch ( Exception e ) { System.err.println(e.getMessage()) ; return null;}
    
        return clientes; 
    }
    
    private static Clientes readClientesInTextMode(String path){
        String linha = "";
        StringTokenizer str;
        String nome, morada;
        Clientes clientes = new Clientes();
        int   nif;
        BufferedReader ficheiro = null;
        //abrir para leitura o ficheiro.
        try {  ficheiro = new BufferedReader(new FileReader(path));}
        catch (IOException e){  System.err.println(e.getMessage()); return null ; }
      
        try {
            while (ficheiro.ready() ) { //enquanto não chegar ao fim do ficheiro
                linha = ficheiro.readLine(); //ler uma linha
                str = new StringTokenizer(linha,";"); // ; é o separador de campos
                //obter o nif
                nif = Integer.parseInt(str.nextToken());
                //obter o nome
                nome = str.nextToken();
                //obter o endereço
                morada = str.nextToken();

                //Criar objecto e inserir
            try { clientes.add(new Cliente(nome, nif, morada));}
            catch (ClientesException  e) { System.err.println(e.getMessage()); }    
        }
        ficheiro.close();        
     } catch (IOException e) {System.err.println( e.toString()) ; }
        return clientes;
    }   
}
