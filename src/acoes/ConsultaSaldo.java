package acoes;
import enums.TipoAcao;
import models.Registro;
import models.Conta;

public class ConsultaSaldo extends AcaoImpl {
    @Override
    public void realizar(double valor, Conta... conta) {
        idUsuario = conta[0].getIdUsuario();
        double saldo = conta[0].getSaldo();
        conta[0].addRegistro(new Registro(TipoAcao.CONSULTA_SALDO, saldo, saldo, idUsuario, idUsuario, "Consulta"));
    }
}