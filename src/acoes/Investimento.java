package acoes;
import auxiliares.Movimentacao;
import enums.TipoAcao;
import models.Registro;
import models.Conta;

public class Investimento {
    public void realizar(double valor, Conta conta) {
        Conta contaInvest = conta.getBanco().getUsuario(conta.getIdUsuario()).ChecaContaInvestimento();
        Conta[] contas = {conta, contaInvest};
        if (new Movimentacao().movimentar(contas, valor, valor)) {
            String idUsuario = conta.getIdUsuario();
            conta.addRegistro(new Registro(TipoAcao.INVESTIMENTO, valor, valor, idUsuario, idUsuario, "Débito"));
            contaInvest.addRegistro(new Registro(TipoAcao.INVESTIMENTO, valor, valor, idUsuario, idUsuario, "Crédito"));
        }
    }
}