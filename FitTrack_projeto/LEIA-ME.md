# FitTrack — Projeto completo (Java, Android Studio)

Este pacote contém **todos** os arquivos do app, cobrindo os 12 Requisitos
Funcionais da documentação (RF01 a RF12) e as 5 Regras de Negócio (RN001 a RN005).

## Como usar

1. Abra seu projeto FitTrack no Android Studio (o mesmo que você já vinha criando).
2. **Copie e substitua** todos os arquivos `.java` de `app/src/main/java/com/example/fittrack/`
   deste pacote para a mesma pasta do seu projeto.
3. **Copie e substitua** todos os arquivos `.xml` de `app/src/main/res/layout/`
   deste pacote para a mesma pasta do seu projeto.
4. **Substitua** o seu `app/src/main/AndroidManifest.xml` pelo deste pacote
   (ele já registra a nova tela `ExecutarTreinoActivity`).
5. Adicione a dependência do RecyclerView no `build.gradle.kts` — veja o
   arquivo `dependencias_build_gradle.txt` deste pacote.
6. Sincronize o Gradle (Sync Now) e rode o app (▶).

## O que tem em cada tela

| Tela | Arquivo(s) | Requisitos atendidos |
|---|---|---|
| Home | MainActivity | navegação geral |
| Exercícios | ExerciciosActivity, ExercicioAdapter, item_exercicio.xml | RF01 (cadastrar), RF02 (excluir via toque longo) |
| Treinos (criar/listar) | TreinosActivity, TreinoAdapter, ExercicioSelecionavelAdapter | RF03 (criar), RF04 (excluir), RN001, RN002 |
| Executar treino | ExecutarTreinoActivity, RegistroExecucaoAdapter | RF06 (séries/repetições), RF07 (carga), RF08 (concluir), RN004 |
| Histórico | HistoricoActivity, HistoricoAdapter | RF09 (histórico), RF10 (evolução de carga), RF11 (consulta), RN005 |
| Perfil | PerfilActivity | RF12 (gerenciar dados do usuário) |

## Como testar o fluxo completo

1. Abra **Exercícios** e cadastre 3-4 exercícios (ex: "Supino Reto" / "Peito", "Agachamento" / "Pernas").
2. Volte à Home e abra **Treinos**. Dê um nome ao treino, marque os exercícios
   desejados nas caixinhas e toque em "Salvar Treino".
3. Na lista de treinos criados (embaixo da mesma tela), **toque** no treino
   que você acabou de criar — isso abre a tela de execução.
4. Preencha séries, repetições e carga de cada exercício, e marque a
   caixinha "Exercício concluído" em todos.
5. Toque em "Finalizar Treino" (só funciona se **todos** os exercícios
   estiverem marcados como concluídos — é a regra RN004).
6. Volte à Home e abra **Histórico** — o treino que você acabou de finalizar
   deve aparecer lá, com a data/hora e os detalhes de séries/repetições/carga
   de cada exercício.
7. Abra **Perfil** e salve seu nome.

## O que NÃO está incluso (e por quê)

- **Persistência real (Room/SQLite):** os dados ficam guardados na classe
  `AppData` (em memória) e são perdidos quando o app é fechado. Isso foi uma
  escolha para focar no aprendizado das telas primeiro — o próximo passo
  natural do curso é trocar `AppData` por um banco Room de verdade, sem
  precisar reescrever as telas (só a forma de ler/gravar os dados muda).
- **Edição de exercícios/treinos já criados:** hoje só dá para criar e
  excluir (RF02/RF04 parcialmente atendidos). Editar um item existente é um
  bom próximo desafio para você tentar sozinho, reaproveitando o padrão de
  diálogo (`AlertDialog`) que já usamos para excluir.
- **Reordenação visual dos exercícios dentro do treino (RF05):** o método
  `Treino.moverExercicio()` já existe e funciona, mas ainda não tem um botão
  na interface chamando ele — outro bom exercício para você tentar.
