import java.util.ArrayList;

import dao.ContatoDao;
import modelo.Contato;

public class TesteAplicacao {

	public static void main(String[] args) {
		ContatoDao dao = new ContatoDao();
		dao.adiciona(new Contato("Carlos", "cedmenezes@gmail.com", "XYZ", "01/06/1981", 1L));
		dao.adiciona(new Contato("Bianca", "bia@gmail.com", "ABC", "03/04/2005", 2L));
		
		System.out.println("Lista de contatos:");
		ArrayList<Contato> c = (ArrayList<Contato>) dao.getListar();
		for(Contato x:c) System.out.println(x);

		dao.remove(1); //removo Carlos
		Contato contato = new Contato
                    ("Bianca da Silva Santos", "bia@gmail.com", "Rua ABC, 72 - casa 3", "03/04/2005", 2L);
		dao.altera(contato); //altero Bianca
		
		System.out.println("Lista de contatos alterada:");
		c = (ArrayList<Contato>) dao.getListar();
		for(Contato x:c) System.out.println(x);
	}
}
