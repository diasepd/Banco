package acoes;
import auxiliares.Credito;
import enums.TipoAcao;
import models.Registro;
import models.Conta;

public class Deposito extends AcaoImpl {
    @Override
    public void realizar(double valor, Conta conta) {
        new Credito().creditar(conta, valor);
        idUsuario = conta.getIdUsuario();
        conta.addRegistro(new Registro(TipoAcao.DEPOSITO, valor, valor, idUsuario, idUsuario, "Deposito"));
    }
}