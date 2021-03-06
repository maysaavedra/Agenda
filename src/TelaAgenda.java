import java.util.ArrayList;
import java.util.List;
import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import dao.ContatoDao;
import modelo.Contato;

public class TelaAgenda extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf_nome;
	private JTextField tf_email;
	private JTextField tf_endereco;
	private JTextField tf_datanasc;
	private JTextField tf_ID;
	private JButton btnInserir, btnTodos, btnLimpar,
		btnVoltar, btnAvancar, btnAtualizar, btnBusca, btnRemover;
	private ContatoDao dao;
	private List<Contato> lista;
	private int cursor;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAgenda frame = new TelaAgenda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Criar o frame
	 */
	public TelaAgenda() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(25, 41, 75, 14);
		contentPane.add(lblNome);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(25, 82, 104, 14);
		contentPane.add(lblEmail);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setBounds(25, 118, 122, 14);
		contentPane.add(lblEndereo);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setBounds(25, 150, 151, 14);
		contentPane.add(lblDataDeNascimento);
		
		tf_nome = new JTextField();
		tf_nome.setBounds(184, 39, 240, 20);
		contentPane.add(tf_nome);
		tf_nome.setColumns(10);
		
		tf_email = new JTextField();
		tf_email.setBounds(184, 80, 240, 20);
		contentPane.add(tf_email);
		tf_email.setColumns(10);
		
		tf_endereco = new JTextField();
		tf_endereco.setBounds(184, 116, 240, 20);
		contentPane.add(tf_endereco);
		tf_endereco.setColumns(10);
		
		tf_datanasc = new JTextField();
		tf_datanasc.setBounds(184, 148, 240, 20);
		contentPane.add(tf_datanasc);
		tf_datanasc.setColumns(10);
		
		btnInserir = new JButton("Inserir");
		btnInserir.setBounds(118, 190, 94, 25);
		btnInserir.addActionListener(this);
		contentPane.add(btnInserir);
		
		btnTodos = new JButton("Todos");
		btnTodos.setBounds(233, 227, 105, 23);
		btnTodos.addActionListener(this);
		contentPane.add(btnTodos);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(12, 190, 86, 25);
		btnLimpar.addActionListener(this);
		contentPane.add(btnLimpar);
		
		btnVoltar = new JButton("<<");
		btnVoltar.setBounds(14, 226, 86, 25);
		btnVoltar.addActionListener(this);
		contentPane.add(btnVoltar);
		
		btnAvancar = new JButton(">>");
		btnAvancar.setBounds(360, 226, 105, 25);
		btnAvancar.addActionListener(this);
		contentPane.add(btnAvancar);
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(234, 190, 104, 25);
		btnAtualizar.addActionListener(this);
		contentPane.add(btnAtualizar);
		
		btnBusca = new JButton("Procurar");
		btnBusca.setBounds(116, 226, 96, 25);
		btnBusca.addActionListener(this);
		contentPane.add(btnBusca);
		
		btnRemover = new JButton("Remover");
		btnRemover.setBounds(361, 190, 104, 25);
		btnRemover.addActionListener(this);
		contentPane.add(btnRemover);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(31, 12, 69, 15);
		contentPane.add(lblId);
		
		tf_ID = new JTextField();
		tf_ID.setEditable(false);
		tf_ID.setBounds(76, 10, 114, 19);
		contentPane.add(tf_ID);
		tf_ID.setColumns(10);
		
		dao = new ContatoDao();
		
		lista = (ArrayList<Contato>)dao.getListar();
		cursor = 0;
		carregaAgenda();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLimpar)
		{	limparTela();
		}
		else if(e.getSource() == btnInserir)
		{	dao.adiciona(new Contato(tf_nome.getText(),
					tf_email.getText(), tf_endereco.getText(),
					tf_datanasc.getText(), -1L));
			JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
			lista = (ArrayList<Contato>)dao.getListar();
			cursor = lista.size()-1;
			carregaAgenda();
		}
		else if(e.getSource() == btnAtualizar)
		{	if(janelaConfirmacao("Atualiza????o")) {
				dao.altera(new Contato(tf_nome.getText(),
						tf_email.getText(), tf_endereco.getText(),
						tf_datanasc.getText(), Long.parseLong(tf_ID.getText())));
			//JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
				lista = (ArrayList<Contato>)dao.getListar();
				carregaAgenda();
			}
		}
		else if(e.getSource() == btnVoltar)
		{	if(cursor > 0) cursor--;
			carregaAgenda();
		}
		else if(e.getSource() == btnAvancar)
		{	if(cursor < lista.size()) cursor++;
			carregaAgenda();
		}
		else if(e.getSource() == btnBusca)
		{	String nomeProcurado = JOptionPane.showInputDialog(null, "Entre com o nome a pesquisar:",
				"Pesquisa", JOptionPane.QUESTION_MESSAGE);
			String titulos[]={"ID","Nome:","E-mail:","Endere??o:", "Data de nascimento:"};
			new Tabela(this, (ArrayList<Contato>)dao.getPesquisar(nomeProcurado), titulos);
		}
		else if(e.getSource() == btnRemover)
		{	if(janelaConfirmacao("Exclus??o")) {
				dao.remove(Integer.parseInt(tf_ID.getText()));
				lista = (ArrayList<Contato>)dao.getListar();
				if(cursor > 0) cursor--;
				carregaAgenda();
			}
		}
		else if(e.getSource() == btnTodos)
		{	String titulos[]={"ID","Nome:","E-mail:","Endere??o:", "Data de nascimento:"};
			new Tabela(this, (ArrayList<Contato>)dao.getListar(), titulos);
		}
	}
	
	private boolean janelaConfirmacao(String evento) {
		Object[] options = {"Confirmar", "Cancelar"};
	    int escolha = JOptionPane.showOptionDialog(null,
	    		"Confirma a " + evento + "?", "Agenda",
	          JOptionPane.YES_NO_OPTION,
	          JOptionPane.QUESTION_MESSAGE,
	          null, options, options[0]);
	    return escolha == 0;
		
	}
	
	private void limparTela() {
		tf_nome.setText("");
		tf_email.setText("");
		tf_endereco.setText("");
		tf_datanasc.setText("");
		tf_ID.setText("");
	}
	
	private void carregaAgenda() {
		if(cursor >= lista.size()) limparTela();
		else {
			tf_nome.setText(lista.get(cursor).getNome());
			tf_email.setText(lista.get(cursor).getEmail());
			tf_endereco.setText(lista.get(cursor).getEndereco());
			tf_datanasc.setText(lista.get(cursor).getDataNascimento());
			tf_ID.setText("" + lista.get(cursor).getId());
		}
	}
	
	public void irParaID(Long id) {
		for(int i=0; i<lista.size(); i++)
			if(lista.get(i).getId() == id) {
				cursor = i;
				carregaAgenda();
				break;
			}
	}
}
