package gereclientes;


// * Esta classe suporta um modelo de Tabelas , em que a informação é Obtida através de uma collecção de clientes
//Baseada em exemplos da O'Reilly

import javax.swing.table.*;
import java.util.*;

public class ClienteModelo extends AbstractTableModel {
  String titles[] = new String[] {
    "Nome", "Morada", "Nif"
  };

  Class types[] = new Class[] {
    String.class, String.class, Integer.class
  };

  Object data[][];

  Collection<Cliente> clientes ;


  public ClienteModelo(Collection<Cliente> clientes) {
      this.clientes = clientes;
      this.readClientes(clientes);
  }

  // Implement the methods of the TableModel interface we're interested
  // in. Only getRowCount( ), getColumnCount( ), and getValueAt( ) are
  // required. The other methods tailor the look of the table.
  public int getRowCount( ) { return data.length; }
  public int getColumnCount( ) { return titles.length; }
    @Override
  public String getColumnName(int c) { return titles[c]; }
    @Override
  public Class getColumnClass(int c) { return types[c]; }
  public Object getValueAt(int r, int c) { return data[r][c]; }

// ler cada cliente para a tabela
  public void readClientes(Collection<Cliente> clientes) {
    data = new Object[clientes.size()][titles.length];
    int i =0;
    for (Cliente tmp : clientes ){
      data[i][0] = tmp.getNome();
      data[i][1] = tmp.getMorada();
      data[i][2] = tmp.getNif();
        i++;
    }

    // avisa quem ouve
    fireTableDataChanged( );
  }
}
