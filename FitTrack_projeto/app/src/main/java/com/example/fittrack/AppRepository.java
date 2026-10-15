package com.example.fittrack;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppRepository {

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public interface OnDataLoadedListener {
        void onLoaded();
    }

    public static void carregarTudo(Context context, OnDataLoadedListener listener) {
        PerfilPreferences.carregar(context);

        executor.execute(() -> {
            AppDatabase db = AppDatabase.getInstance(context);

            // 1. Carrega Exercícios
            List<ExercicioEntity> eEntities = db.exercicioDao().getAll();
            List<Exercicio> exerciciosList = new ArrayList<>();
            for (ExercicioEntity ee : eEntities) {
                exerciciosList.add(new Exercicio(ee.nome, ee.grupoMuscular));
            }

            // 2. Carrega Treinos e Registros de Execução
            List<TreinoEntity> tEntities = db.treinoDao().getAll();
            List<Treino> treinosList = new ArrayList<>();

            for (TreinoEntity te : tEntities) {
                Treino t = new Treino(te.nome);
                t.setConcluido(te.concluido);
                t.setDataExecucao(te.dataExecucao);

                List<RegistroExecucaoEntity> rEntities = db.registroExecucaoDao().getForTreino(te.id);
                for (RegistroExecucaoEntity re : rEntities) {
                    Exercicio ex = new Exercicio(re.exercicioNome, re.exercicioGrupo);
                    boolean exJaExiste = false;
                    for (Exercicio e : t.getExercicios()) {
                        if (e.getNome().equalsIgnoreCase(ex.getNome())) {
                            exJaExiste = true;
                            break;
                        }
                    }
                    if (!exJaExiste) {
                        t.getExercicios().add(ex);
                    }

                    RegistroExecucao registro = new RegistroExecucao(ex);
                    registro.setSeries(re.series);
                    registro.setRepeticoes(re.repeticoes);
                    registro.setCarga(re.carga);
                    registro.setConcluido(re.concluido);

                    t.getRegistros().add(registro);
                }
                treinosList.add(t);
            }

            // Atualiza AppData
            AppData.exercicios.clear();
            AppData.exercicios.addAll(exerciciosList);

            AppData.treinos.clear();
            AppData.treinos.addAll(treinosList);

            if (listener != null) {
                listener.onLoaded();
            }
        });
    }

    public static void salvarExercicio(Context context, Exercicio exercicio) {
        executor.execute(() -> {
            AppDatabase db = AppDatabase.getInstance(context);
            db.exercicioDao().insert(new ExercicioEntity(exercicio.getNome(), exercicio.getGrupoMuscular()));
        });
    }

    public static void excluirExercicio(Context context, String nomeExercicio) {
        executor.execute(() -> {
            AppDatabase db = AppDatabase.getInstance(context);
            db.exercicioDao().deleteByNome(nomeExercicio);
        });
    }

    public static void salvarTreino(Context context, Treino treino) {
        executor.execute(() -> {
            AppDatabase db = AppDatabase.getInstance(context);
            TreinoEntity te = new TreinoEntity(treino.getNome(), treino.isConcluido(), treino.getDataExecucao());
            long id = db.treinoDao().insert(te);

            List<RegistroExecucaoEntity> rEntities = new ArrayList<>();
            for (RegistroExecucao r : treino.getRegistros()) {
                rEntities.add(new RegistroExecucaoEntity((int) id, r.getExercicio().getNome(),
                        r.getExercicio().getGrupoMuscular(), r.getSeries(), r.getRepeticoes(),
                        r.getCarga(), r.isConcluido()));
            }
            db.registroExecucaoDao().insertAll(rEntities);
        });
    }

    public static void atualizarTreino(Context context, Treino treino) {
        executor.execute(() -> {
            AppDatabase db = AppDatabase.getInstance(context);
            List<TreinoEntity> tEntities = db.treinoDao().getAll();
            for (TreinoEntity te : tEntities) {
                if (te.nome.equalsIgnoreCase(treino.getNome())) {
                    te.concluido = treino.isConcluido();
                    te.dataExecucao = treino.getDataExecucao();
                    db.treinoDao().update(te);

                    db.registroExecucaoDao().deleteForTreino(te.id);
                    List<RegistroExecucaoEntity> rEntities = new ArrayList<>();
                    for (RegistroExecucao r : treino.getRegistros()) {
                        rEntities.add(new RegistroExecucaoEntity(te.id, r.getExercicio().getNome(),
                                r.getExercicio().getGrupoMuscular(), r.getSeries(), r.getRepeticoes(),
                                r.getCarga(), r.isConcluido()));
                    }
                    db.registroExecucaoDao().insertAll(rEntities);
                    break;
                }
            }
        });
    }

    public static void excluirTreino(Context context, String nomeTreino) {
        executor.execute(() -> {
            AppDatabase db = AppDatabase.getInstance(context);
            List<TreinoEntity> tEntities = db.treinoDao().getAll();
            for (TreinoEntity te : tEntities) {
                if (te.nome.equalsIgnoreCase(nomeTreino)) {
                    db.registroExecucaoDao().deleteForTreino(te.id);
                    db.treinoDao().deleteById(te.id);
                    break;
                }
            }
        });
    }
}
