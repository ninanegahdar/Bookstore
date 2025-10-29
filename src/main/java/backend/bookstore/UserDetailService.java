package backend.bookstore;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import backend.bookstore.domain.User;
import backend.bookstore.domain.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User currUser = repository.findByUsername(username);
        if (currUser == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
        currUser.getUsername(),
        currUser.getPassword(),
        AuthorityUtils.createAuthorityList("ROLE_" + currUser.getRole())
);

    }
}
