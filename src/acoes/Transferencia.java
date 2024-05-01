package acoes;
import auxiliares.Movimentacao;
import enums.TipoAcao;
import models.Registro;
import models.Conta;

public class Transferencia {
    public void realizar(double valor, String terceiro, Conta conta) {
        double valorSolicitado = valor;
        valor = conta.getTipoPessoa().calcularRetirada(valor, conta.getTipoConta());
        Conta conta3o = conta.getBanco().getUsuario(terceiro).getContaCorrente();
        Conta[] contas = {conta, conta.getBanco().getUsuario(terceiro).getContaCorrente()};
        if (new Movimentacao().movimentar(contas, valorSolicitado, valor)) {
            conta.addRegistro(new Registro(TipoAcao.TRANSFERENCIA, valorSolicitado, valor, conta.getIdUsuario(),
                    conta3o.getIdUsuario(), "Débito"));
            conta3o.addRegistro(new Registro(TipoAcao.TRANSFERENCIA, valorSolicitado, valor, conta.getIdUsuario(),
                    conta3o.getIdUsuario(), "Crédito"));
        }
    }
}