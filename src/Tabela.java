import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import modelo.Contato;

public class Tabela extends JFrame {
	private static final long serialVersionUID = 1L;
    private JTable tabela;
    /*Construtor da classe; irá construir o JFrame e a JTable*/
    public Tabela(final TelaAgenda pai, ArrayList<Contato> contatos, String titulos[]) {
    	setLayout(new FlowLayout());//tipo de layout
        setSize(new Dimension(700, 200));//tamanho do frame
        setLocationRelativeTo(null);//centralizado
        setTitle("Meus contatos");//titulo
        String dados[][] = new String[contatos.size()][5];
        for(int i=0; i<contatos.size(); i++)
        {	Contato contato = contatos.get(i);
        	dados[i][0] = "" + contato.getId();
        	dados[i][1] = contato.getNome();
        	dados[i][2] = contato.getEmail();
        	dados[i][3] = contato.getEndereco();
        	dados[i][4] = contato.getDataNascimento();
        }
        //instanciando a JTable
        tabela=new JTable(dados,titulos);
        tabela.setPreferredScrollableViewportSize(new Dimension(650,100));//barra de rolagem
        tabela.setFillsViewportHeight(true);
        tabela.setDefaultEditor(Object.class, null);
        //adicionando a tabela em uma barra de rolagem, ficará envolta , pela mesma 
        JScrollPane scrollPane=new JScrollPane(tabela);  
        tabela.addMouseListener(new MouseAdapter() {
        	  public void mouseClicked(MouseEvent e) {
        		  if(e.getClickCount() == 2) {
        			  JTable target = (JTable)e.getSource();
        			  int linha = target.getSelectedRow();
        			  pai.irParaID(Long.parseLong((String) tabela.getModel().getValueAt(linha, 0)));
        			  dispose();
        		  }
        	  }
        	});
        add(scrollPane);
        setVisible(true);
    }
}
