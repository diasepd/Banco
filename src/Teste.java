import acoes.*;
import auxiliares.LeituraArquivoPessoas;
import models.Banco;
import models.*;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.sql.Time;
import java.util.Scanner;
import java.util.Timer;

public class Teste {
    public static void main(String[] args) throws InterruptedException {
        Banco banco = new Banco();
        new LeituraArquivoPessoas(banco, 50, "C:/Users/diase/IdeaProjects/Banco/");
        for (Usuario usuario : banco.getListaDeUsuario()) {
            System.out.println(usuario.getContaCorrente().getSaldo() + " " + usuario.toString());
        }

        UsuarioPF usuario1 = new UsuarioPF("12345678911", "Eduardo Caldas Dias", banco);        //Conta corrente PF
        UsuarioPJ usuario2 = new UsuarioPJ("916962327910001", "Fábrica de brinquedos", banco);  // Conta corrente PJ
        banco.setUsuario(usuario1);
        usuario1.setContaPoupanca(new ContaPoupanca(3, "12345678911", banco));                  //Conta poupança PF

        System.out.println("\nSaldo após DEPOSITO e INVESTIMENTO");
        new Deposito().realizar(200, usuario1.getContaCorrente());
        Thread.sleep(2000);
        new Deposito().realizar(200, usuario2.getContaCorrente());
        new Deposito().realizar(100, usuario1.getContaPoupanca());
        new Investimento().realizar(100, usuario1.getContaCorrente());                           //100
        new Investimento().realizar(100, usuario2.getContaCorrente());                           //100
        saldos(usuario1, usuario2);

        System.out.println("Saldo após SAQUE");

        new Saque().realizar(10, usuario1.getContaCorrente());                              //90
        new Saque().realizar(10, usuario2.getContaCorrente());                              //89.95
        new Saque().realizar(10, usuario1.getContaPoupanca());                              //90
        new Saque().realizar(10, usuario1.getContaInvestimento());                          //90
        new Saque().realizar(10, usuario2.getContaInvestimento());                          //90
        saldos(usuario1, usuario2);

        System.out.println("Saldo após TRANSFERIR");
//        new Transferencia().realizar(valor, this, getBanco().getUsuario(terceiro).getContaCorrente())
        new Transferencia().realizar(10, "916962327910001", usuario1.getContaCorrente());      //100
        new Transferencia().realizar(10, "12345678911", usuario2.getContaCorrente());      //100
        new Transferencia().realizar(10, "916962327910001", usuario1.getContaPoupanca());      //100
        new Transferencia().realizar(10, "916962327910001", usuario1.getContaInvestimento());      //100
        new Transferencia().realizar(10, "12345678911", usuario2.getContaInvestimento());      //100
        saldos(usuario1, usuario2);

        System.out.println("Consulta saldo\n");
        new ConsultaSaldo().realizar(usuario1.getContaCorrente());                       //100
        new ConsultaSaldo().realizar(usuario2.getContaCorrente());                       //109
        new ConsultaSaldo().realizar(usuario1.getContaPoupanca());                       //80
        new ConsultaSaldo().realizar(usuario1.getContaInvestimento());                   //80
        new ConsultaSaldo().realizar(usuario2.getContaInvestimento());                   //80

        System.out.println("Saldo após Rendimento");
        usuario1.getContaInvestimento().render();              //80.8
        usuario2.getContaInvestimento().render();              //81.6
        saldos(usuario1, usuario2);
        System.out.println(usuario2.getContaInvestimento().getDataDeAtualizacao());

        System.out.println("Historico das Movimentações");
        historiar(usuario1.getContaCorrente());
        historiar(usuario2.getContaCorrente());
        historiar(usuario1.getContaPoupanca());
        historiar(usuario1.getContaInvestimento());
        historiar(usuario2.getContaInvestimento());
    }

    private static void historiar (Conta conta) {
        for (Registro registro : conta.getRegistroDeAcao()) {
            System.out.println(registro.toString());
        }
        System.out.println();
    }

    private static void saldos(UsuarioPF usuario1, UsuarioPJ usuario2) {
        System.out.println("PF corrente     " + usuario1.getContaCorrente().getSaldo());
        System.out.println("PJ corrente     " + usuario2.getContaCorrente().getSaldo());
        System.out.println("PF poupança     " + usuario1.getContaPoupanca().getSaldo());
        System.out.println("PF investimento " + usuario1.getContaInvestimento().getSaldo());
        System.out.println("PJ investimento " + usuario2.getContaInvestimento().getSaldo());
        System.out.println();
    }
}
//  EXPECTATIVAS PROJETO MÓDULO II
//  Melhoria na disposição de funcionalidades  ???
//  Organização do pacote
//  SOLID