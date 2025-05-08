package hoangdung.vn.shop.models.responses.auth;

import hoangdung.vn.shop.models.responses.users.UserResponse;

public class LoginResponse {
    
    private String token;
    private UserResponse user;

    public LoginResponse(String token, UserResponse user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public UserResponse getUser() {
        return user;
    }

}
