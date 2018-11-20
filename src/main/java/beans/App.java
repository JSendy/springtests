package beans;

import loggers.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class App {

    @Autowired
    private Client client;
    @Autowired
    @Qualifier("defaultLogger")
    private EventLogger defaultLogger;
    @Value("#{loggerMap}")
    private Map<EventType, EventLogger> loggers;

    public void logEvent(EventType type, Event event, String msg){
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        EventLogger logger = loggers.get(type);
        if(logger == null){
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }
}
