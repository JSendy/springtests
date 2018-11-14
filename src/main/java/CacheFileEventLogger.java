import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {

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
