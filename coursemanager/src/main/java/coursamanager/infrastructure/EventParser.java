package coursamanager.infrastructure;

import java.io.IOException;
import java.io.InputStream;

import coursamanager.domain.model.event.CourseEvent;

public interface EventParser {

    CourseEvent read(InputStream is) throws IOException;

}
