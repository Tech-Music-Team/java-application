package school.sptech.utils.aggregator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class AggregadorBase<T> {

    protected Map<String, T> itens = new HashMap<>();

    public T obter(String chave) {
        return itens.get(chave);
    }

    public boolean existe(String chave) {
        return itens.containsKey(chave);
    }

    public List<T> obterTodos() {
        return new ArrayList<>(itens.values());
    }

    public int obterQuantidade() {
        return itens.size();
    }
}
