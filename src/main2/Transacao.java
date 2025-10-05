package main2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {
    private String tipo;
    private double valor;
    private LocalDateTime dataHora;
    private String idOrigem;
    private String idDestino;

    public Transacao(String tipo, double valor, String idOrigem, String idDestino) {
        this.tipo = tipo;
        this.valor = valor;
        this.dataHora = LocalDateTime.now();
        this.idOrigem = idOrigem;
        this.idDestino = idDestino;
    }

    public String getTipo() { return tipo; }
    public double getValor() { return valor; }
    public LocalDateTime getDataHora() { return dataHora; }
    public String getIdOrigem() { return idOrigem; }
    public String getIdDestino() { return idDestino; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String destino = idDestino != null ? idDestino : "-";
        return String.format("[%s] %s de R$%.2f | Origem: %s | Destino: %s",
                dataHora.format(formatter), tipo, valor, idOrigem, destino);
    }

    public String toFormattedString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String simbolo = "";
        
        simbolo = switch (tipo) {
            case "SAQUE" -> "📤";
            case "DEPOSITO" -> "📥";
            case "TRANSFERENCIA_ENVIADA" -> "➡️";
            case "TRANSFERENCIA_RECEBIDA" -> "⬅️";
            default -> "💳";
        };
        
        if (tipo.contains("TRANSFERENCIA")) {
            return String.format("%s %s | %s | R$ %.2f | Para: %s",
                    simbolo, dataHora.format(formatter), tipo.replace("_", " "), valor, idDestino);
        } else {
            return String.format("%s %s | %s | R$ %.2f",
                    simbolo, dataHora.format(formatter), tipo, valor);
        }
    }
}