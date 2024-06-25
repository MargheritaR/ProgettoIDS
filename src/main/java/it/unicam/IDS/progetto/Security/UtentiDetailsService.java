package it.unicam.IDS.progetto.Security;

import it.unicam.IDS.progetto.Entita.Utente;
import it.unicam.IDS.progetto.Repository.UtenteListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UtentiDetailsService implements UserDetailsService {

    private final UtenteListRepository utenteRepository;

    @Autowired
    public UtentiDetailsService(UtenteListRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utente utente = utenteRepository.findByUsername(username);
        if (utente == null) {
            throw new UsernameNotFoundException("User not found with username");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(utente.getRuolo().name()));
        return new User(utente.getUsername(), utente.getPassword(), authorities);
    }
}
