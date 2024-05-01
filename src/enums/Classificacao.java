package enums;

public enum Classificacao {
    PF(0.01, 1.0),
    PJ(0.02, 1.005);

    private final double txRendimentoMensal;
    private final double txSacarTransferir;

    Classificacao(double txRendimentoMensal, double txSacarTransferir) {
        this.txRendimentoMensal = txRendimentoMensal;
        this.txSacarTransferir = txSacarTransferir;
    }

    public double calcularRendimento(double valor) {
        return txRendimentoMensal * valor;
    }
    public double calcularRetirada(double valor, TipoConta tipoConta) {
        return (tipoConta.equals(TipoConta.CORRENTE) ? txSacarTransferir : 1) * valor;
    }
}