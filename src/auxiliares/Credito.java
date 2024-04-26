package auxiliares;
import models.Conta;
import java.time.LocalDateTime;

public class Credito {
    public void creditar(Conta conta, double valor) {
        conta.setSaldo(conta.getSaldo() + valor);
        conta.setDataDeAtualizacao(LocalDateTime.now());
    }
}