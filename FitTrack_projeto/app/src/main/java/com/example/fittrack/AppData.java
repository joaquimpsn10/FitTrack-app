package com.example.fittrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Armazenamento provisório em memória, compartilhado entre todas as telas
 * do app.
 */
public class AppData {

    public static List<Exercicio> exercicios = new ArrayList<>();
    public static List<Treino> treinos = new ArrayList<>();
    public static String nomeUsuario = "";
    public static String peso = "";
    public static String altura = "";
    public static String metaSemanal = "5";
}
