package com.denglitong.readinglist.repository;

import com.denglitong.readinglist.entity.ReaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/10/13
 */
public interface ReaderRepository extends JpaRepository<ReaderEntity, String> {

}
