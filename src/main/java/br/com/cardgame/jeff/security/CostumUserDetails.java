package br.com.cardgame.jeff.security;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.cardgame.jeff.model.UserEntityCard;
import br.com.cardgame.jeff.repository.UserEntityRepository;

@Service
public class CostumUserDetails implements UserDetailsService{

    @Autowired
    private UserEntityRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntityCard userDb = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("could not found any user with this username"));
        var listAutho = new ArrayList<SimpleGrantedAuthority>();

        var autho = new SimpleGrantedAuthority("USER");

        listAutho.add(autho);

        return new User (userDb.getUsername(), userDb.getPassword(), listAutho);
    }
    
}
