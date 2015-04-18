package coursamanager.domain.service;

import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import coursamanager.domain.model.event.CourseEvent;

public interface EventStore {

    /**
     * Enregistre une liste d'évênement sur une course
     */
    void recordEvents(UUID courseId, List<CourseEvent> events, int eventOffset) throws IOException,
            ConcurrentModificationException;

    /**
     * Récupère les évênements d'une course, à partir d'un numéro d'évênement (numéroté à partir de 0, pour le premier).
     */
    List<CourseEvent> readEventsFrom(UUID courseId, final int eventOffset) throws IOException;

    /**
     * Récupère les évênements de toute les courses, à partir d'un numéro d'évênement (numéroté à partir de 0, pour le premier).
     *
     * @param eventOffset
     * @return liste de (Course UUID, Course Event)
     */
    List<Entry<UUID, CourseEvent>> readAllEventsFrom(int eventOffset);

}
