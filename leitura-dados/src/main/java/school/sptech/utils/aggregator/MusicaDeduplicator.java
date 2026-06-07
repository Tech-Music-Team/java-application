package school.sptech.utils.aggregator;

import school.sptech.entities.Musica;
import school.sptech.entities.Logger;

public class MusicaDeduplicator extends AggregadorBase<Musica> {

    public void adicionar(Musica musica) {
        if (musica == null || musica.getIdTrack() == null) {
            return;
        }

        String trackId = musica.getIdTrack();

        if (!itens.containsKey(trackId)) {
            itens.put(trackId, musica);
            Logger.debug(MusicaDeduplicator.class.getPackageName(),
                    MusicaDeduplicator.class.getName(),
                    "Música adicionada: " + trackId);
        } else {
            Logger.debug(MusicaDeduplicator.class.getPackageName(),
                    MusicaDeduplicator.class.getName(),
                    "Música já existe, duplicata ignorada: " + trackId);
        }
    }
}
