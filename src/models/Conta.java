package models;
import acoes.*;
import enums.Classificacao;
import enums.Status;
import enums.TipoConta;
import enums.TipoAcao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Conta {
    private long id;
    private String idUsuario;
    private Banco banco;
    private LocalDateTime dataDeAtualizacao;
    private double saldo = 0;
    private List<Registro> registroDeAcao = new ArrayList<>();
    private Status status = Status.ATIVO;
    private TipoConta tipoConta;
    private Classificacao tipoPessoa;

    public Conta(long id, String idUsuario, Banco banco) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.banco = banco;
        this.dataDeAtualizacao = LocalDateTime.now();
        setRegistro(new Registro(TipoAcao.CONSULTA_SALDO, 0, 0, idUsuario, idUsuario, "Abertura da Conta"));
    }

    public void setRegistro(Registro registro) { this.registroDeAcao.add(registro); }
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo   = saldo; }
    public List<Registro> getRegistroDeAcao() { return registroDeAcao; }
    public void setRegistroDeAcao(List<Registro> registroDeAcao) { this.registroDeAcao = registroDeAcao; }
    public LocalDateTime getDataDeAtualizacao() { return dataDeAtualizacao; }
    public void setDataDeAtualizacao(LocalDateTime dataDeAtualizacao) { this.dataDeAtualizacao = dataDeAtualizacao; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status  = status; }
    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }
    public Banco getBanco() { return banco; }
    public void setBanco(Banco banco) { this.banco = banco; }
    public TipoConta getTipoConta() {return tipoConta; }
    public void setTipoConta(TipoConta tipoConta) {this.tipoConta = tipoConta; }
    public Classificacao getTipoPessoa() {
        if (tipoPessoa == null)
            setTipoPessoa(banco.getUsuario(getIdUsuario()).getClassificacao());
        return tipoPessoa;
    }
    public void setTipoPessoa(Classificacao tipoPessoa) { this.tipoPessoa = tipoPessoa; }

    ////// métodos /////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void consultarSaldo() { new ConsultaSaldo().realizar(saldo, this); }
    public void depositar(double valor) { new Deposito().realizar(valor, this); }
    public void sacar(double valor) { new Saque().realizar(valor, this); }
    public void transferir(double valor, String terceiro) {
        new Transferencia().realizar(valor, this, getBanco().getUsuario(terceiro).getContaCorrente());
    }
}