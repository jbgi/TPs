package coursamanager.domain.model;

import java.util.Optional;

import coursamanager.domain.model.AgeInterval;

public abstract class CategoryDefinition {

    public abstract AgeInterval ageInterval();

    public abstract Optional<Genre> genre();

}
