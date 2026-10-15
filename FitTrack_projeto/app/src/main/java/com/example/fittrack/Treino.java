package com.example.fittrack;

import java.util.ArrayList;
import java.util.List;

public class Treino {

    private String nome;
    private List<Exercicio> exercicios;
    private List<RegistroExecucao> registros;
    private boolean concluido;
    private long dataExecucao;

    public Treino(String nome) {
        this.nome = nome;
        this.exercicios = new ArrayList<>();
        this.registros = new ArrayList<>();
        this.concluido = false;
        this.dataExecucao = 0;
    }

    public String getNome() {
        return nome;
    }

    public List<Exercicio> getExercicios() {
        return exercicios;
    }

    /**
     * Garante que existe um RegistroExecucao para cada exercício do treino
     * antes de devolver a lista (evita ficar fora de sincronia caso o
     * treino seja editado depois de criado).
     */
    public List<RegistroExecucao> getRegistros() {
        if (registros.size() != exercicios.size()) {
            registros.clear();
            for (Exercicio e : exercicios) {
                registros.add(new RegistroExecucao(e));
            }
        }
        return registros;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    public long getDataExecucao() {
        return dataExecucao;
    }

    public void setDataExecucao(long dataExecucao) {
        this.dataExecucao = dataExecucao;
    }

    /**
     * RF05 - Organização/reordenação dos exercícios dentro do treino.
     */
    public void moverExercicio(int posicaoAtual, int novaPosicao) {
        if (novaPosicao < 0 || novaPosicao >= exercicios.size()) return;
        Exercicio e = exercicios.remove(posicaoAtual);
        exercicios.add(novaPosicao, e);
    }
}
