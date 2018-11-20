package config;


import beans.Event;
import beans.EventType;
import loggers.CacheFileEventLogger;
import loggers.EventLogger;
import loggers.FileEventLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.annotation.Resource;
import java.util.*;

@Configuration
public class LoggerConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Resource(name = "consoleEventLogger")
    private EventLogger consoleEventLogger;

    @Resource(name = "fileEventLogger")
    public FileEventLogger fileEventLogger;

    @Resource(name = "cacheFileEventLogger")
    public CacheFileEventLogger cacheFileEventLogger;

    @Resource(name = "combinedEventLogger")
    private EventLogger combinedEventLogger;

    @Bean
    public Collection<EventLogger> combinedEventLoggers(){
        ArrayList<EventLogger> eventLoggers = new ArrayList<>(2);
        eventLoggers.add(consoleEventLogger);
        eventLoggers.add(fileEventLogger);
        return eventLoggers;
    }

    @Bean
    public EventLogger defaultLogger() {
        return consoleEventLogger;
    }

    @Bean
    public Map<EventType,EventLogger> loggerMap(){
        Map<EventType, EventLogger> map = new EnumMap<>(EventType.class);
        map.put(EventType.INFO,consoleEventLogger);
        map.put(EventType.ERROR,combinedEventLogger);
        return map;
    }

}
