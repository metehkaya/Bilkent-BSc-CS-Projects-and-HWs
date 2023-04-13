package CodeBase;

public class User {

    private String name;
    private String photo;

    public User( String name , String photo ) {
        this.name = name;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

}