package coursamanager.infrastructure.json;

import java.util.Optional;

import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParsingException;

import coursamanager.domain.model.AgeInterval;
import coursamanager.domain.model.AgeIntervalCases;

public class AgeIntervalJson implements JsonConverter<AgeInterval> {

    @Override
    public JsonGenerator writeObject(final Optional<String> optionalName, final AgeInterval ageInterval, final JsonGenerator jg) {
        JsonConverter.startComplexObject(optionalName, jg);
        return ageInterval.match(new AgeIntervalCases<JsonGenerator>() {
            @Override
            public JsonGenerator lessThan(final int ageMaxExcluded) {
                return jg.write("lessThan", ageMaxExcluded);
            }

            @Override
            public JsonGenerator atLeast(final int ageMinIncluded) {
                return jg.write("atLeast", ageMinIncluded);
            }

            @Override
            public JsonGenerator between(final int ageMinIncluded, final int ageMaxExcluded) {
                return jg.writeStartArray("between").write(ageMinIncluded).write(ageMaxExcluded).writeEnd();
            }
        }).writeEnd();
    }

    @Override
    public AgeInterval readObject(final JsonParser jp) throws JsonParsingException {
        final AgeInterval ageInterval;
        jp.next();
        jp.next();
        final String keyName = jp.getString();
        jp.next();
        switch (keyName) {
            case "lessThan":
                ageInterval = AgeInterval.lessThan(jp.getInt());
                break;
            case "atLeast":
                ageInterval = AgeInterval.atLeast(jp.getInt());
                break;
            case "between":
                jp.next();
                final int ageMinIncluded = jp.getInt();
                jp.next();
                ageInterval = AgeInterval.between(ageMinIncluded, jp.getInt());
                jp.next();
                break;
            default:
                throw new JsonParsingException("Unkonw key for AgeInterval: " + keyName, jp.getLocation());
        }
        jp.next();
        return ageInterval;
    }

}
