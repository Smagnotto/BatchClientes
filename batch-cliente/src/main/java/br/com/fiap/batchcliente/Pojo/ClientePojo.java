package br.com.fiap.batchcliente.Pojo;

/**
 * ClientePojo
 */

public class ClientePojo {

    public ClientePojo() { }

    public ClientePojo(String nome) {
        this.nome = nome;
    }

    private String nome;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "ClientePojo [nome=" + nome + "]";
    }

}