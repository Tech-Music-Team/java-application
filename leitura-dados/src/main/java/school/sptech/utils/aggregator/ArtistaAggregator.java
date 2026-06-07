package school.sptech.utils.aggregator;

import school.sptech.entities.Artista;
import school.sptech.entities.Logger;

public class ArtistaAggregator extends AggregadorBase<Artista> {

    public void adicionarOuAtualizar(Artista artista, Long views, Long likes) {
        if (artista == null || artista.getNome() == null) {
            return;
        }

        String nome = artista.getNome();

        if (itens.containsKey(nome)) {
            // Artista já existe, agregamos os valores
            Artista artistaExistente = itens.get(nome);
            artistaExistente.setViews(artistaExistente.getViews() + views);
            artistaExistente.setLikes(artistaExistente.getLikes() + likes);

            Logger.debug(ArtistaAggregator.class.getPackageName(),
                    ArtistaAggregator.class.getName(),
                    "Artista existente atualizado: " + nome);
        } else {
            // Novo artista
            itens.put(nome, artista);

            Logger.debug(ArtistaAggregator.class.getPackageName(),
                    ArtistaAggregator.class.getName(),
                    "Novo artista adicionado: " + nome);
        }
    }
}
