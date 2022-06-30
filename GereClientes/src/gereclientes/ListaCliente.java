// Frame para a tabela de pesquisas 
// baseado em exemplos da O'Reilly
package gereclientes;

import java.awt.*;
import java.util.Collection;
import javax.swing.*;

public class ListaCliente extends JFrame {

  public ListaCliente( Collection<Cliente> clientes) {
    super("Custom TableModel Test");
    setSize(300, 200);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    JTable tb = new JTable( new ClienteModelo(clientes));


    tb.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    tb.setColumnSelectionAllowed(true);

    JScrollPane jsp = new JScrollPane(tb);
    getContentPane( ).add(jsp, BorderLayout.CENTER);
  }

  public static void main(Collection<Cliente> clientes) {
      ListaCliente ft = new ListaCliente(clientes);
      ft.setVisible(true);
  }
}

