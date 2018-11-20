import beans.App;
import beans.Event;
import beans.EventType;
import config.AppConfig;
import config.LoggerConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan("*");
        ctx.register(AppConfig.class, LoggerConfig.class);
        ctx.refresh();
        App app = (App) ctx.getBean("app");

        app.logEvent(EventType.INFO, ctx.getBean(Event.class),"Some  event for 1");
        Thread.sleep(1000);
        app.logEvent(EventType.ERROR, ctx.getBean(Event.class),"Some event for 2");
        Thread.sleep(1000);
        app.logEvent(null, ctx.getBean(Event.class),"Some event for 2");

        ctx.close();
    }
}
