package coursamanager.domain.model;

import java.time.LocalDate;

public abstract class RunnerDetails {

    public abstract String nom();

    public abstract LocalDate birthDate();

    public abstract Genre genre();

}
