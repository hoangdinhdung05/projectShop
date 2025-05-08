package hoangdung.vn.shop.models.responses.users;

public class UserResponse {
    private long id;
    private String email;
    private String name;

    public UserResponse(long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
