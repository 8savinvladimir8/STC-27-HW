package ru.savin.homework08;

import java.util.Collection;
import java.util.Map;

/**
 * Класс {@code CleanerChooser} реализует интерфейс Cleaner и служит для обработки разных типов объектов.
 * @author Savin Vladimir
 */
public class CleanerChooser implements Cleaner {
    private final ObjectCleaner objectCleaner;
    private final CleanerForMap<String> mapCleaner;

    public CleanerChooser() {
        this.objectCleaner = new ObjectCleaner(new FieldCleanerDisplayer());
        this.mapCleaner = new MapCleaner();
    }

    @Override
    public void clean(Object object, Collection<String> fieldsToClean, Collection<String> fieldsToOutput) {
        if (!(object instanceof Map)) {
            objectCleaner.clean(object, fieldsToClean, fieldsToOutput);
        } else {
            mapCleaner.clean((Map<String, ?>) object, fieldsToClean, fieldsToOutput);
        }
    }

    @Override
    public boolean checkFields(Object object, Collection<String> fieldsToCheck) {
        if (!(object instanceof Map)) {
            return objectCleaner.checkFields(object, fieldsToCheck);
        } else {
            return mapCleaner.checkKeys((Map<String, ?>) object, fieldsToCheck);
        }
    }
}
