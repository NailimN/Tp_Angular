package biblio_boot.rest;

import biblio_boot.dao.IDAOUtilisateur;
import biblio_boot.dto.request.AuthUserRequest;
import biblio_boot.dto.response.UserResponse;
import biblio_boot.model.Utilisateur;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/utilisateur")
public class UtilisateurRestController {

    @Autowired
    private IDAOUtilisateur daoUtilisateur;
    

    @GetMapping
    public List<UserResponse> findAll() {
        return this.daoUtilisateur.findAll().stream()
                .map(UserResponse::convert).toList();
    }

    /*
    @PostMapping
    public int subscribe(@RequestBody SubscribeUserRequest request) {
        Utilisateur utilisateur = new Utilisateur();
        String encodedPassword = this.passwordEncoder.encode(request.getPassword());

        BeanUtils.copyProperties(request, utilisateur);

        // utilisateur.setNom(request.getNom());
        // utilisateur.setUsername(request.getUsername());
        utilisateur.setPassword(encodedPassword);

        this.dao.save(utilisateur);

        return utilisateur.getId();
    }
    */

}
