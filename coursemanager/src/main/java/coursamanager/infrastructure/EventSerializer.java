package coursamanager.infrastructure;

import java.io.IOException;
import java.io.OutputStream;

import coursamanager.domain.model.event.CourseEvent;

public interface EventSerializer {

    void write(CourseEvent event, OutputStream os) throws IOException;

}
