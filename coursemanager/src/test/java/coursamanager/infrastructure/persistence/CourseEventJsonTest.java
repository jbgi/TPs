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

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;

import coursamanager.domain.model.AgeInterval;
import coursamanager.domain.model.CategoryDefinition;
import coursamanager.domain.model.Genre;
import coursamanager.domain.model.RunnerDetails;
import coursamanager.domain.model.event.CourseEvent;
import coursamanager.infrastructure.json.CourseEventJson;


public class CourseEventJsonTest {

    private static final CourseEventJson converter = new CourseEventJson();

    @Test
    public void courseCreated() {
        JsonConverterTestHelper.testRoundTrip(converter, CourseEvent.courseCreated("Ma Super Course"));
    }

    @Test
    public void categoryDefined() {
        JsonConverterTestHelper.testRoundTrip(
                converter,
                CourseEvent.categoryDefined("Poids Légés",
                        CategoryDefinition.category(AgeInterval.lessThan(20), Optional.<Genre> empty())));
    }

    @Test
    public void runnerEnlisted() {
        JsonConverterTestHelper.testRoundTrip(
                converter,
                CourseEvent.runnerEnlisted(9, RunnerDetails.runnerDetails("Joe Black", LocalDate.now(), Genre.homme())));
    }

}
