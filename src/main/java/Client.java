public class Client {
    String id;
    String fullName;
    String greeting;

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public Client(String id, String fullName){
        this.id = id;
        this.fullName = fullName;
    }
    public Client(){

    }

    public String getFullName() {
        return fullName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
