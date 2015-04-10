package coursamanager.domain.model;

import java.util.Optional;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class CategoryDefinition {

    public abstract AgeInterval ageInterval();

    public abstract Optional<Genre> genre();

    public static final CategoryDefinition category(final AgeInterval ageInterval, final Optional<Genre> genre) {
        return new AutoValue_CategoryDefinition(ageInterval, genre);
    }

}
