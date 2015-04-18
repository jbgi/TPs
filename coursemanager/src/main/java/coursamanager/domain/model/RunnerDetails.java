package coursamanager.domain.model;

import java.time.LocalDate;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class RunnerDetails {

    public abstract String nom();

    public abstract LocalDate birthDate();

    public abstract Genre genre();

    public static RunnerDetails runnerDetails(final String nom, final LocalDate birthDate, final Genre genre) {
        return new AutoValue_RunnerDetails(nom, birthDate, genre);
    }

}
