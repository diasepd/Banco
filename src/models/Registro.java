package models;
import enums.TipoAcao;
import java.time.LocalDateTime;

public class Registro {
    private LocalDateTime data;
    private TipoAcao acao;
    private double valorPretendido;
    private double valorReal;
    private String idUsuarioOrigem;
    private String idUsuarioDestino;
    private String observacao;

    public Registro(TipoAcao acao, double valorPretendido, double valorReal, String idUsuarioOrigem,
                    String idUsuarioDestino, String observacao) {
        this.data = LocalDateTime.now();
        this.acao = acao;
        this.valorPretendido = valorPretendido;
        this.valorReal = valorReal;
        this.idUsuarioOrigem = idUsuarioOrigem;
        this.idUsuarioDestino = idUsuarioDestino;
        this.observacao = observacao;
    }

//    public LocalDateTime getData() {return data;}
//    public TipoAcao getAcao() {return acao;}
//    public double getValorPretendido() {return valorPretendido;}
//    public double getValorReal() {return valorReal;}
//    public String getIdUsuarioOrigem() {return idUsuarioOrigem;}
//    public String getIdUsuarioDestino() {return idUsuarioDestino;}
//    public String getObservacao() {return observacao;}

    @Override
    public String toString() {
        return "HistoricoAcao{data="+data+", tipo="+acao+", valorPretendido="+valorPretendido+", valorReal="+valorReal+
                ", idUsuarioOrigem='"+idUsuarioOrigem+'\''+", idUsuarioDestino='"+idUsuarioDestino +'\''+
                ", observacao='" + observacao + '\'' +'}';
    }
}