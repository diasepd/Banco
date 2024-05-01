package models;
import enums.Classificacao;
import enums.Status;
import java.time.LocalDateTime;

public abstract class Usuario {
    private String id;
    private Classificacao classificacao;
    private String nome;
    private LocalDateTime dataDeCadastro;
    private Status status;
    private Banco banco;
    private ContaCorrente contaCorrente;
    private ContaInvestimento contaInvestimento;

    public Usuario(String id, String nome, Banco banco) {
        this.id = id;
        this.nome = nome;
        this.banco = banco;
        dataDeCadastro = LocalDateTime.now();
        status = Status.ATIVO;
        contaCorrente = new ContaCorrente(1, id, banco);
        banco.setUsuario(this);
    }

    public Usuario(String id, String nome, Banco banco, double valor) {
        this(id, nome, banco);
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public Classificacao getClassificacao() {return classificacao;}
    public void setClassificacao(Classificacao classificacao) {this.classificacao = classificacao;}
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public LocalDateTime getDataDeCadastro() {return dataDeCadastro;}
    public void setDataDeCadastro(LocalDateTime dataDeCadastro) {this.dataDeCadastro = dataDeCadastro;}
    public Status getStatus() {return status;}
    public void setStatus(Status status) {this.status = status;}
    public Banco getBanco() {return banco;}
    public void setBanco(Banco banco) {this.banco = banco;}
    public ContaCorrente getContaCorrente() {return contaCorrente;}
    public void setContaCorrente(ContaCorrente contaCorrente) {this.contaCorrente = contaCorrente;}
    public ContaInvestimento getContaInvestimento() {return contaInvestimento;}
    public void setContaInvestimento(ContaInvestimento contaInvestimento) {this.contaInvestimento = contaInvestimento;}
    public ContaInvestimento ChecaContaInvestimento() {
        if (contaInvestimento == null)
            setContaInvestimento(new ContaInvestimento(2, id, banco));
        return getContaInvestimento();
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", classificacao=" + classificacao +
                ", nome='" + nome + '\'' +
                ", dataDeCadastro=" + dataDeCadastro +
                ", status=" + status +
                ", banco=" + banco +
                ", contaCorrente=" + contaCorrente +
                ", contaInvestimento=" + contaInvestimento +
                '}';
    }
}