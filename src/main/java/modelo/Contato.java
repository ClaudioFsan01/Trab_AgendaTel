package modelo;

import java.io.Serializable;

public class Contato implements Serializable {

    private String nome;
    private String telefone;
    
    public Contato(String nome,String telefone) {
        setNome(nome);
        setTelefone(telefone);
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome=nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
}
