package natalunifebe.unifebe.edu.br.firebase2018.dao;

import java.io.Serializable;

public class Alunos implements Serializable{

    private String key;
    private String nome;
    private String instituicao;
    private String ano;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Alunos(String nome, String instituicao, String ano) {
        this.nome = nome;
        this.instituicao = instituicao;
        this.ano = ano;
    }

    public Alunos() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "Alunos{" +
                "key='" + key + '\'' +
                ", nome='" + nome + '\'' +
                ", instituicao='" + instituicao + '\'' +
                ", ano='" + ano + '\'' +
                '}';
    }
}
