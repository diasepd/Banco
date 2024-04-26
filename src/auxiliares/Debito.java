package auxiliares;
import models.Conta;
import java.time.LocalDateTime;

public class Debito {
    public boolean debitar(Conta conta, double valor) {
        if (conta.getSaldo() < valor)
            return false;
        conta.setSaldo(conta.getSaldo() - valor);
        conta.setDataDeAtualizacao(LocalDateTime.now());
        return true;
    }
}