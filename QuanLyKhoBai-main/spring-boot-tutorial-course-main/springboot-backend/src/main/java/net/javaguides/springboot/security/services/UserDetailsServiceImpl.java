package net.javaguides.springboot.security.services;

import net.javaguides.springboot.entity.Accounts;
import net.javaguides.springboot.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IAccountRepository iAccountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Accounts account = iAccountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found With User email" + username));
        return UserDetailsImpl.build(account);
    }
}
