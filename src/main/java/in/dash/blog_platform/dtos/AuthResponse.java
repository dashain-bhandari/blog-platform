package in.dash.blog_platform.dtos;

public class AuthResponse {

    private String token;

    public long getExpiredIn() {
        return expiredIn;
    }

    public AuthResponse(String token, long expiredIn) {
        this.token = token;
        this.expiredIn = expiredIn;
    }

    public void setExpiredIn(long expiredIn) {
        this.expiredIn = expiredIn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private long expiredIn;


}
