import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    private String fileName;
    private File file;
    @Override
    public void logEvent(Event event) {
        try{
            FileUtils.writeStringToFile(file, event.toString() + "\n", true);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public FileEventLogger(String filename){
        this.fileName = filename;
    }

    public void init() throws IOException{
        this.file = new File(fileName);
        if(file.exists() && !file.canWrite()){
            throw new IllegalArgumentException("Can't write to file " + fileName);
        }else if(!file.exists()){
            file.createNewFile();
        }
    }
}
