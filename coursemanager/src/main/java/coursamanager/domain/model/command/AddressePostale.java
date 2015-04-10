package coursamanager.domain.model.command;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class AddressePostale {

    public abstract String adresseLine1();

    public abstract String adresseLine2();

    public abstract String adresseLine3();

    public final AddressePostaleLowerCase toLowerCase() {
        return AddressePostaleLowerCase.addressePostale(adresseLine1(), adresseLine2(), adresseLine3());
    }

    public static AddressePostale addressePostale(final String adresseLine1, final String adresseLine2,
            final String adresseLine3) {
        return new AutoValue_AddressePostale(adresseLine1, adresseLine2, adresseLine3);
    }
}
