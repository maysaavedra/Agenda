/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public class Contato {
	private Long id;
	private String nome;
	private String email;
	private String endereco;
	private String dataNascimento;
	
	public Contato() {}
	public Contato(String nome, String email, String endereco,
			String dataNascimento, Long id) {
		this.nome = nome;
		this.email = email;
		this.endereco = endereco;
		this.dataNascimento = dataNascimento;
		this.id = id;
	}
	
	// metodos get e set para id, nome, email, endereco e dataNascimento
	public String getNome() {
		return this. nome;
	}
	public void setNome(String novo) {
		this. nome = novo;
	}
	public String getEmail() {
		return this. email;
	}
	public void setEmail(String novo) {
		this. email = novo;
	}
	public String getEndereco() {
		return this. endereco;
	}
	public void setEndereco(String novo) {
		this. endereco = novo;
	}
	public Long getId() {
		return this. id;
	}
	public void setId(Long novo) {
		this. id = novo;
	}
	public String getDataNascimento() {
		return this. dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this. dataNascimento = dataNascimento;
	}
	@Override
	public String toString() {
		return "Contato [id=" + id + ", nome=" + nome + ", email=" + email + ", endereco=" + endereco
				+ ", dataNascimento=" + dataNascimento + "]";
	}
}