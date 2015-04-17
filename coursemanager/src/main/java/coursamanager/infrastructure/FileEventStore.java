package coursamanager.infrastructure;

import java.io.File;
import java.io.IOException;
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
    public void recordEvents(final UUID courseId, final List<CourseEvent> events) throws IOException {
        final File courseRepo = new File(rootRepo, courseId.toString());
        if (!courseRepo.exists()) {
            if (!courseRepo.mkdirs()) {
                throw new IOException("Pas pu créer le répertoire: " + courseRepo.getPath());
            }
        }

        // à chaque courseId on associe un sous-répertoire de rootRepo
        // à chaque CourseEvent on associe un fichier numéroté par ordre d'arrivé, <num>-event.txt
        // Il faut parcourir le répertoire pour trouver le numéro du dernier évênement enregistré et enregister les suivant à
        // partir de ce numéro + 1.
        final int lastEventNumber = -1;
        for (final File file : courseRepo.listFiles()) {
            // TODO
        }
        // TODO

    }

    @Override
    public List<CourseEvent> readEventsFrom(final UUID courseId, final int firstEventToRead) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("");
    }

    public static void main(final String[] args) {
        System.out.println(UUID.randomUUID().toString());
    }

}
