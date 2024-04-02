package org.com.contasapagar.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String cpf;

    @Column
    private String titulo;

    @Column
    private Double valor;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDateTime vencimento;

    @Column
    private Long taxaDeJurosPorDiasDeAtraso;

    @Column
    private char contraAtrasada;

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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDateTime vencimento) {
        this.vencimento = vencimento;
    }

    public Long getTaxaDeJurosPorDiasDeAtraso() {
        return taxaDeJurosPorDiasDeAtraso;
    }

    public void setTaxaDeJurosPorDiasDeAtraso(Long taxaDeJurosPorDiasDeAtraso) {
        this.taxaDeJurosPorDiasDeAtraso = taxaDeJurosPorDiasDeAtraso;
    }

    public char getContraAtrasada() {
        return contraAtrasada;
    }

    public void setContraAtrasada(char contraAtrasada) {
        this.contraAtrasada = contraAtrasada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Conta conta)) return false;
        return getContraAtrasada() == conta.getContraAtrasada() && Objects.equals(getId(), conta.getId()) && Objects.equals(getCpf(), conta.getCpf()) && Objects.equals(getTitulo(), conta.getTitulo()) && Objects.equals(getValor(), conta.getValor()) && Objects.equals(getVencimento(), conta.getVencimento()) && Objects.equals(getTaxaDeJurosPorDiasDeAtraso(), conta.getTaxaDeJurosPorDiasDeAtraso());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCpf(), getTitulo(), getValor(), getVencimento(), getTaxaDeJurosPorDiasDeAtraso(), getContraAtrasada());
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
                ", contraAtrasada=" + contraAtrasada +
                '}';
    }
}
