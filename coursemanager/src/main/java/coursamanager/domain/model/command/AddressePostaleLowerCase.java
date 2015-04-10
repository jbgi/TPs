package coursamanager.domain.model.command;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class AddressePostaleLowerCase {

    public abstract String adresseLine1();

    public abstract String adresseLine2();

    public abstract String adresseLine3();

    public static AddressePostaleLowerCase addressePostale(final String adresseLine1, final String adresseLine2,
            final String adresseLine3) {
        return new AutoValue_AddressePostaleLowerCase(adresseLine1.toLowerCase(), adresseLine2.toLowerCase(),
                adresseLine3.toLowerCase());
    }
}
