package biblio_boot.dto.response;

import biblio_boot.model.Utilisateur;
import org.springframework.beans.BeanUtils;

public class UserResponse {
    private int id;
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static UserResponse convert(Utilisateur utilisateur) {
        UserResponse resp = new UserResponse();

        BeanUtils.copyProperties(utilisateur, resp);

        return resp;
    }
}
