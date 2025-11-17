package biblio_boot.config;

import biblio_boot.dao.IDAOUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailService implements UserDetailsService {

    @Autowired
    private IDAOUtilisateur daoUtilisateur;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        return this.daoUtilisateur.findByUsername(username)
                .map(user -> User
                        .withUsername(username)
                        .password(user.getPassword())
                        .roles("USER")
                        .build()
                ).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

}
