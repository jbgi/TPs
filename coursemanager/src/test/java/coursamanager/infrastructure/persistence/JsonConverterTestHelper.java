/* Copyright (c) 2015, Jean-Baptiste Giraudeau <jb@giraudeau.info>
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution.
 * Neither the name of the copyright holder nor the names of its contributors
   may be used to endorse or promote products derived from this software
   without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */
package coursamanager.infrastructure.persistence;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Optional;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;

import coursamanager.infrastructure.json.JsonConverter;


/**
 * @author jbgi
 *
 */
public class JsonConverterTestHelper {

    public static <T> void  testRoundTrip(final JsonConverter<T> converter, final T t) {
        final StringWriter arrayWriter = new StringWriter();
        final JsonGenerator arrayJsonGenerator = Json.createGenerator(arrayWriter);
        arrayJsonGenerator.writeStartArray();
        converter.writeObject(Optional.<String> empty(), t, arrayJsonGenerator);
        converter.writeObject(Optional.<String> empty(), t, arrayJsonGenerator);
        arrayJsonGenerator.writeEnd();
        arrayJsonGenerator.close();
        final String jsonArray = arrayWriter.toString();
        System.out.println(jsonArray);
        final JsonParser arrayJsonParser = Json.createParser(new StringReader(jsonArray));
        arrayJsonParser.next();
        System.out.println(arrayJsonParser.getLocation());
        final T roundTripedTFromArray1 = converter.readObject(arrayJsonParser);
        assertEquals(t, roundTripedTFromArray1);
        System.out.println(arrayJsonParser.getLocation());
        final T roundTripedTFromArray2 = converter.readObject(arrayJsonParser);
        assertEquals(t, roundTripedTFromArray2);

        final StringWriter objectWriter = new StringWriter();
        final JsonGenerator objectJsonGenerator = Json.createGenerator(objectWriter);
        objectJsonGenerator.writeStartObject();
        converter.writeObject(Optional.of("key1"), t, objectJsonGenerator);
        converter.writeObject(Optional.of("key2"), t, objectJsonGenerator);
        objectJsonGenerator.writeEnd();
        objectJsonGenerator.close();
        final String jsonObject = objectWriter.toString();
        System.out.println(jsonObject);
        final JsonParser objectJsonParser = Json.createParser(new StringReader(jsonObject));
        objectJsonParser.next();
        objectJsonParser.next();
        final T roundTripedTFromObject1 = converter.readObject(objectJsonParser);
        assertEquals(t, roundTripedTFromObject1);
        objectJsonParser.next();
        final T roundTripedTFromObject2 = converter.readObject(objectJsonParser);
        assertEquals(t, roundTripedTFromObject2);

    }

}
