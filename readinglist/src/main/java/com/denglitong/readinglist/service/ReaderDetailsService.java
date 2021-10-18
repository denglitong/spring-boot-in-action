package com.denglitong.readinglist.service;

import com.denglitong.readinglist.entity.ReaderEntity;
import com.denglitong.readinglist.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/10/16
 */
@Service
public class ReaderDetailsService implements UserDetailsService {

    private ReaderRepository readerRepository;

    @Autowired
    public void setReaderRepository(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @PostConstruct
    public void mockInitUser() {
        ReaderEntity admin = new ReaderEntity();
        admin.setUsername("denglitong");
        admin.setPassword("123456");
        readerRepository.save(admin);

        ReaderEntity reader = new ReaderEntity();
        reader.setUsername("user");
        reader.setPassword("user");
        readerRepository.save(reader);
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        System.out.println("username: " + username);
        Optional<ReaderEntity> reader = readerRepository.findById(username);
        if (reader.isPresent()) {
            ReaderEntity readerEntity = reader.get();
            System.out.println("user roles: " + readerEntity.getAuthorities());
        }
        return readerRepository.findById(username).orElseGet(null);
    }

}
