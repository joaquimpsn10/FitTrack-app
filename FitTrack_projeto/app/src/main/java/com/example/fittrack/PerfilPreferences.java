package com.example.fittrack;

import android.content.Context;
import android.content.SharedPreferences;

public class PerfilPreferences {

    private static final String PREF_NAME = "fittrack_perfil_prefs";
    private static final String KEY_NOME = "nome_usuario";
    private static final String KEY_PESO = "peso_usuario";
    private static final String KEY_ALTURA = "altura_usuario";
    private static final String KEY_META = "meta_semanal";

    public static void salvar(Context context, String nome, String peso, String altura, String meta) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_NOME, nome);
        editor.putString(KEY_PESO, peso);
        editor.putString(KEY_ALTURA, altura);
        editor.putString(KEY_META, meta);
        editor.apply();

        // Atualiza AppData
        AppData.nomeUsuario = nome;
        AppData.peso = peso;
        AppData.altura = altura;
        AppData.metaSemanal = meta;
    }

    public static void carregar(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        AppData.nomeUsuario = pref.getString(KEY_NOME, "");
        AppData.peso = pref.getString(KEY_PESO, "");
        AppData.altura = pref.getString(KEY_ALTURA, "");
        AppData.metaSemanal = pref.getString(KEY_META, "5");
    }
}
