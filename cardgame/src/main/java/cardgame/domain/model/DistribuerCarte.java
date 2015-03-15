package cardgame.domain.model;

import java.util.List;
import java.util.function.Function;

import common.P2;

public interface DistribuerCarte extends Function<List<Carte>, P2<List<Carte>, Carte>> {
}
