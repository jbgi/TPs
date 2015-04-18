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
package coursamanager.infrastructure.json;

import java.util.Optional;

import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParsingException;

import coursamanager.domain.model.CategoryDefinition;
import coursamanager.domain.model.RunnerDetails;
import coursamanager.domain.model.event.CourseEvent;
import coursamanager.domain.model.event.CourseEventCases;

public class CourseEventJson implements JsonConverter<CourseEvent> {

    private static final CategoryDefinitionJson categoryDefinitionJson = new CategoryDefinitionJson();

    private static final RunnerDetailsJson runnerDetailsJson = new RunnerDetailsJson();

    @Override
    public JsonGenerator writeObject(final Optional<String> optionalName, final CourseEvent t, final JsonGenerator jg) {
        JsonConverter.startComplexObject(optionalName, jg);
        return t.match(new CourseEventCases<JsonGenerator>() {

            @Override
            public JsonGenerator courseCreated(final String nomCourse) {
                return jg.write("courseCreated", nomCourse);
            }

            @Override
            public JsonGenerator courseRenamed(final String nomCourse) {
                return jg.write("courseRenamed", nomCourse);
            }

            @Override
            public JsonGenerator categoryDefined(final String categoryName, final CategoryDefinition description) {
                jg.writeStartObject("categoryDefined");
                jg.write("name", categoryName);
                return categoryDefinitionJson.writeObject(Optional.of("description"), description, jg).writeEnd();
            }

            @Override
            public JsonGenerator categoryRemoved(final String categoryName) {
                jg.write("categoryRemoved", categoryName);
                return jg;
            }

            @Override
            public JsonGenerator runnerEnlisted(final int numeroDossard, final RunnerDetails runnerDetails) {
                jg.writeStartObject("runnerEnlisted");
                jg.write("number", numeroDossard);
                return runnerDetailsJson.writeObject(Optional.of("runnerDetails"), runnerDetails, jg).writeEnd();
            }

            @Override
            public JsonGenerator runnerUnlisted(final int numeroDossard) {
                return jg.write("runnerUnlisted", numeroDossard);
            }
        }).writeEnd();
    }

    @Override
    public CourseEvent readObject(final JsonParser jp) throws JsonParsingException {
        final CourseEvent courseEvent;
        jp.next();
        jp.next();
        final String eventName = jp.getString();
        jp.next();
        switch (eventName) {
            case "courseCreated":
                courseEvent = CourseEvent.courseCreated(jp.getString());
                break;
            case "courseRenamed":
                courseEvent = CourseEvent.courseRenamed(jp.getString());
                break;
            case "categoryDefined": {
                jp.next();
                jp.next();
                final String name = jp.getString();
                jp.next();
                final CategoryDefinition description = categoryDefinitionJson.readObject(jp);
                courseEvent = CourseEvent.categoryDefined(name, description);
                jp.next();
            }
                break;
            case "categoryRemoved":
                courseEvent = CourseEvent.categoryRemoved(jp.getString());
                break;
            case "runnerEnlisted": {
                jp.next();
                jp.next();
                final int number = jp.getInt();
                jp.next();
                final RunnerDetails runnerDetails = runnerDetailsJson.readObject(jp);
                courseEvent = CourseEvent.runnerEnlisted(number, runnerDetails);
                jp.next();
            }
                break;
            case "runnerUnlisted":
                courseEvent = CourseEvent.runnerUnlisted(jp.getInt());
                break;
            default:
                throw new JsonParsingException("Unknown event type : " + eventName, jp.getLocation());
        }
        jp.next();

        return courseEvent;
    }

}
