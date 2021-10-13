package com.denglitong.readinglist.controller;

import com.denglitong.readinglist.entity.BookEntity;
import com.denglitong.readinglist.repository.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/10/12
 */
@Controller(value = "/")
public class ReadingListController {

    private ReadingListRepository readingListRepository;

    @Autowired
    public void setReadingListRepository(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }

    @RequestMapping(value = "/{reader}", method = GET)
    public String readersBooks(@PathVariable("reader") String reader, Model model) {
        List<BookEntity> readingList = readingListRepository.findByReader(reader);
        if (!CollectionUtils.isEmpty(readingList)) {
            model.addAttribute("books", readingList);
        }
        return "readingList";
    }

    @RequestMapping(value = "/{reader}", method = POST)
    public String addToReadingList(@PathVariable("reader") String reader, BookEntity book) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/{reader}";
    }
}
