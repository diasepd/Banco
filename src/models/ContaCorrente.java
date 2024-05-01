package models;
import enums.TipoConta;

public class ContaCorrente extends Conta {
    public ContaCorrente(long id, String idUsuario, Banco banco) {
        super(id, idUsuario, banco);
        setTipoConta(TipoConta.CORRENTE);
    }
}