package acoes;
import auxiliares.Credito;
import enums.TipoAcao;
import models.Conta;
import models.Registro;

public class Rendimento extends AcaoImpl {
    @Override
    public void realizar(double valor, Conta conta) {
        double renda = conta.getTipoPessoa().calcularRendimento(valor);
        new Credito().creditar(conta, renda);
        idUsuario = conta.getIdUsuario();
        conta.addRegistro(new Registro(TipoAcao.RENDIMENTO, renda, renda, idUsuario, idUsuario, "Renda"));
    }
}