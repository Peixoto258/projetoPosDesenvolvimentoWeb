package org.com.contasapagar.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Entity
@Table
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate vencimento;

    @Column(nullable = false)
    private Long taxaDeJurosPorDiasDeAtraso;

    @Column
    private LocalDate dataPagamento;

    public Conta() {}

    public Conta(String cpf,
                 String titulo,
                 BigDecimal valor,
                 LocalDate vencimento,
                 Long taxaDeJurosPorDiasDeAtraso) {
        this.cpf = cpf;
        this.titulo = titulo;
        this.valor = valor;
        this.vencimento = vencimento;
        this.taxaDeJurosPorDiasDeAtraso = taxaDeJurosPorDiasDeAtraso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public Long getTaxaDeJurosPorDiasDeAtraso() {
        return taxaDeJurosPorDiasDeAtraso;
    }

    public void setTaxaDeJurosPorDiasDeAtraso(Long taxaDeJurosPorDiasDeAtraso) {
        this.taxaDeJurosPorDiasDeAtraso = taxaDeJurosPorDiasDeAtraso;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public boolean isContraAtrasada() {
        return LocalDate.now().isAfter(vencimento);
    }

    public BigDecimal getValorAtualizadoComJuros() {
        final var diaAtual = LocalDate.now();
        if (isContraAtrasada()) {
            final var quantidadeDiasEmAtraso = ChronoUnit.DAYS.between(vencimento, diaAtual);
            final double jurosTotalEmAtraso = (double) taxaDeJurosPorDiasDeAtraso * quantidadeDiasEmAtraso;
            final double taxaJurosTotal = (jurosTotalEmAtraso / 100);
            final var valorDoJuros = valor.multiply(BigDecimal.valueOf(taxaJurosTotal));
            return valor.add(valorDoJuros);
        }
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Conta conta)) return false;
        return Objects.equals(getId(), conta.getId()) && Objects.equals(getCpf(), conta.getCpf()) && Objects.equals(getTitulo(), conta.getTitulo()) && Objects.equals(getValor(), conta.getValor()) && Objects.equals(getVencimento(), conta.getVencimento()) && Objects.equals(getTaxaDeJurosPorDiasDeAtraso(), conta.getTaxaDeJurosPorDiasDeAtraso());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCpf(), getTitulo(), getValor(), getVencimento(), getTaxaDeJurosPorDiasDeAtraso(), isContraAtrasada());
    }

    @Override
    public String toString() {
        return "conta{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", titulo='" + titulo + '\'' +
                ", valor=" + valor +
                ", vencimento=" + vencimento +
                ", taxaDeJurosPorDiasDeAtraso=" + taxaDeJurosPorDiasDeAtraso +
                ", contraAtrasada=" + isContraAtrasada() +
                ", dataPagamento=" + dataPagamento +
                '}';
    }
}
