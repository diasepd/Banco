package acoes;
import enums.TipoAcao;
import models.Registro;
import models.Conta;

public class ConsultaSaldo {
    public void realizar(Conta conta) {
        String idUsuario = conta.getIdUsuario();
        double saldo = conta.getSaldo();
        conta.addRegistro(new Registro(TipoAcao.CONSULTA_SALDO, saldo, saldo, idUsuario, idUsuario, "Consulta"));
    }
}