package com.denglitong.readinglist.repository;

import com.denglitong.readinglist.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/10/12
 */
public interface ReadingListRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findByReader(String reader);
}
