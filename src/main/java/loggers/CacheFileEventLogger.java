package loggers;

import beans.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CacheFileEventLogger extends FileEventLogger {

    // Use system property cache.size or 5 if property is not set
    @Value("${cache.size:5}")
    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String filename, int cacheSize){
        super(filename);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<Event>(cacheSize);
    }
    public CacheFileEventLogger(String filename) {
        super(filename);
    }
    public CacheFileEventLogger(){

    }
    public void writeEventFromCache(){
        cache.forEach(super::logEvent);
    }
    @Override
    public void logEvent(Event event) {
        cache.add(event);
        if(cache.size() == cacheSize){
            writeEventFromCache();
            cache.clear();
        }
    }
    public void destroy(){
        if( !cache.isEmpty() ){
            writeEventFromCache();
        }
    }
}
