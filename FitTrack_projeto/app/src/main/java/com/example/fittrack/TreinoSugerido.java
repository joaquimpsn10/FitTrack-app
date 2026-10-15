package com.example.fittrack;

import java.util.List;

public class TreinoSugerido {

    private String diaSemana;
    private String titulo;
    private List<Exercicio> exercicios;

    public TreinoSugerido(String diaSemana, String titulo, List<Exercicio> exercicios) {
        this.diaSemana = diaSemana;
        this.titulo = titulo;
        this.exercicios = exercicios;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Exercicio> getExercicios() {
        return exercicios;
    }

    public String getExerciciosFormatados() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < exercicios.size(); i++) {
            Exercicio e = exercicios.get(i);
            sb.append("• ").append(e.getNome()).append(" (").append(e.getGrupoMuscular()).append(")");
            if (i < exercicios.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
