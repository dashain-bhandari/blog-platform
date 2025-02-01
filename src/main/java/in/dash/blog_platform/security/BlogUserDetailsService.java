package in.dash.blog_platform.security;

import in.dash.blog_platform.entities.User;
import in.dash.blog_platform.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class BlogUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    public BlogUserDetailsService blogUserDetailsService(UserRepository repo){
        this.userRepository=repo;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user= this.userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User with email not found"));

        return new BlogUserDetails(user);
    }
}
