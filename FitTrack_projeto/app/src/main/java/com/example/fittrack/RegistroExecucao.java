package com.example.fittrack;

/**
 * Representa o registro de UMA execução de UM exercício dentro de um treino:
 * quantas séries, repetições e carga (kg) foram usadas, e se já foi marcado
 * como concluído. Atende RF06, RF07 e RF08.
 */
public class RegistroExecucao {

    private Exercicio exercicio;
    private int series;
    private int repeticoes;
    private double carga;
    private boolean concluido;

    public RegistroExecucao(Exercicio exercicio) {
        this.exercicio = exercicio;
        this.series = 0;
        this.repeticoes = 0;
        this.carga = 0;
        this.concluido = false;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
    }

    public double getCarga() {
        return carga;
    }

    public void setCarga(double carga) {
        this.carga = carga;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }
}
