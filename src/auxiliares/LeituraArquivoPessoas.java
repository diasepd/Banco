package auxiliares;
import models.Banco;
import models.Usuario;
import models.UsuarioPF;
import models.UsuarioPJ;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import static java.nio.file.Files.lines;

public class LeituraArquivoPessoas {
    static String PESSOA_JURIDICA = "2";
    private String path;
    private Banco banco;
    private double valorDeposito;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public LeituraArquivoPessoas(Banco banco, double valorDeposito, String path) {
        this.path = path;
        this.banco = banco;
        this.valorDeposito = valorDeposito;
        this.ler();
    }

    private void ler() {
        try {
            List<String> importado = new ArrayList<>(
                    lines(Path.of(path + "pessoas.csv")).skip(1)
                    .map(pessoa -> pessoa.split(","))
                    .filter(this::pessoaJuridicaOuFisicaMaiorDeIdade)
                    .map(this::criarUsuarioConta)
                    .map(this::depositar)
                    .map(this::montarLinha)
                    .toList()
            );
            importado.add(0,"nome_do_cliente;documento;pf_pj;numero_da_conta;saldo_em_conta");

            Files.write(Path.of(path + "importados.csv"), importado);
        } catch (IOException e) { throw new RuntimeException(e); }
    }

    private boolean pessoaJuridicaOuFisicaMaiorDeIdade(String[] pessoa) {
        if (PESSOA_JURIDICA.equals(pessoa[3])) return true;
        return ChronoUnit.YEARS.between(LocalDate.parse(pessoa[1], dateTimeFormatter), LocalDate.now()) > 17;
    }

    private Usuario criarUsuarioConta(String[] pessoa) {
        if (PESSOA_JURIDICA.equals(pessoa[3])) return new UsuarioPJ(pessoa[2]/* ID */, pessoa[0]/* Nome */, banco);
        return new UsuarioPF(pessoa[2], pessoa[0], banco);
    }

    private Usuario depositar(Usuario usuario) {
        usuario.getContaCorrente().depositar(valorDeposito);
        return usuario;
    }

    private String montarLinha(Usuario usuario) {
        return usuario.getNome() + ";" + usuario.getId() + ";" + usuario.getClassificacao() + ";" +
               usuario.getContaCorrente().getId() + ";" + usuario.getContaCorrente().getSaldo();
    }
}