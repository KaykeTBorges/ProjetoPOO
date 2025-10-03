package main2;

import java.time.LocalDateTime;

public class Transacao {
    private String tipo; // Saque, Depósito, Transferência
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

    @Override
    public String toString() {
        return String.format("[%s] %s de R$%.2f | Origem: %s | Destino: %s",
                dataHora, tipo, valor, idOrigem, (idDestino != null ? idDestino : "-"));
    }
}
