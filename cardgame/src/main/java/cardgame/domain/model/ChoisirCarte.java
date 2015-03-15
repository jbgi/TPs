package cardgame.domain.model;

import java.util.List;
import java.util.function.BiFunction;

public interface ChoisirCarte extends BiFunction<List<Carte>, Carte, List<Carte>> {

}
