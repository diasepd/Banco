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
    String path;
    Banco banco;
    double valorDeposito;
    List<String> importado = new ArrayList<>();

    public LeituraArquivoPessoas(Banco banco, double valorDeposito, String path) {
        this.path = path;
        this.banco = banco;
        this.valorDeposito = valorDeposito;
        importado.add("nome_do_cliente;documento;pf_pj;numero_da_conta;saldo_em_conta");
        this.ler();
    }

    private void ler() {
        try {lines(Path.of(path + "pessoas.csv")).skip(1).map(pessoa -> pessoa.split(","))
                .filter(this::pessoaJuridicaEFisicaMaiorDeIdade)
                .forEach(this::criarDepositarAdicionar);
            Files.write(Path.of(path + "importados.csv"), importado);
        } catch (IOException e) { throw new RuntimeException(e); }
    }

    private boolean pessoaJuridicaEFisicaMaiorDeIdade(String[] pessoa) {
        if (pessoaJuridica(pessoa[3])) return true;
        LocalDate dataNascimento = LocalDate.parse(pessoa[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return ChronoUnit.YEARS.between(dataNascimento, LocalDate.now()) < 18;
    }

    private boolean pessoaJuridica(String tipoPessoa) {return tipoPessoa.equals("2");}

    private void criarDepositarAdicionar(String[] pessoa) {
        Usuario usuario; // Criar
        String tipoPessoa = pessoaJuridica(pessoa[3]) ? "PJ":"PF";
        if (tipoPessoa.equals("PJ")) usuario = new UsuarioPJ(pessoa[2]/* ID */, pessoa[0]/* Nome */, banco);
        else                         usuario = new UsuarioPF(pessoa[2], pessoa[0], banco);
        usuario.getContaCorrente().depositar(valorDeposito); // Depositar
        importado.add(pessoa[0] + ";" + pessoa[2] + ";" + tipoPessoa + ";" + // Adicionar
                      usuario.getContaCorrente().getId() + ";" + usuario.getContaCorrente().getSaldo());
    }
}