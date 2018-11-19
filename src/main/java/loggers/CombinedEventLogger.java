package loggers;

import beans.Event;

import java.util.Collection;

public class CombinedEventLogger implements EventLogger {
    private final Collection<EventLogger> loggers;

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        loggers.forEach(v -> v.logEvent(event));
    }
}
