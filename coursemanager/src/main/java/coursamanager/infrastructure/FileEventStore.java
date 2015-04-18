package coursamanager.infrastructure;

import java.io.File;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.UUID;

import coursamanager.domain.model.event.CourseEvent;
import coursamanager.domain.service.EventStore;

public class FileEventStore implements EventStore {

    private final File rootRepo;
    private final EventParser parser;
    private final EventSerializer serializer;

    FileEventStore(final File rootRepo, final EventParser parser, final EventSerializer serializer) {
        this.rootRepo = rootRepo;
        this.parser = parser;
        this.serializer = serializer;
    }

    @Override
    public void recordEvents(final UUID courseId, final List<CourseEvent> events, final int eventOffset) throws IOException,
            ConcurrentModificationException {
        final File courseRepo = new File(rootRepo, courseId.toString());
        if (!courseRepo.exists()) {
            if (!courseRepo.mkdirs()) {
                throw new IOException("Pas pu créer le répertoire: " + courseRepo.getPath());
            }
        }

        // à chaque courseId on associe un sous-répertoire de rootRepo
        // à chaque CourseEvent on associe un fichier numéroté par ordre d'arrivé, <num>-event.txt
        // Il faut vérifier que l'event numéro 'eventOffset' n'exsite pas, sinon lever une ConcurrentModificationException.
        // Ensuite on peut enregister les évênements à partir de ce numéro.

        // TODO

    }

    @Override
    public List<CourseEvent> readEventsFrom(final UUID courseId, final int eventOffset)
            throws IOException {
        // TODO récupérer depuis le disque tous les évênements numéroté >= eventOffset (si eventOffset = 0, renvoyé tous les
        // évênements)
        throw new UnsupportedOperationException("");
    }

    public static void main(final String[] args) {
        System.out.println(UUID.randomUUID().toString());
    }

}
