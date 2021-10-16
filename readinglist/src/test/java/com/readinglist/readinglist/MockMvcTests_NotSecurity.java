package com.readinglist.readinglist;

import com.denglitong.readinglist.ReadinglistApplication;
import com.denglitong.readinglist.entity.BookEntity;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/10/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadinglistApplication.class)
@WebAppConfiguration
public class MockMvcTests_NotSecurity {

    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext)
                .build();
    }

    @Test
    public void homePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/readingList/dlt"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("readingList"))
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("books"));
    }

    @Test
    public void postBook() throws Exception {
        String reader = "dlt";
        String requestPath = "/readingList/" + reader;
        String title = "狂人日记";
        String author = "鲁迅";
        String isbn = "1234567890";
        String description = "仁义道德？吃人！";

        mockMvc.perform(MockMvcRequestBuilders.post(requestPath)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", title)
                        .param("author", author)
                        .param("isbn", isbn)
                        .param("description", description))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.header().string("Location", requestPath));

        BookEntity expectedBook = new BookEntity();
        expectedBook.setId(1L);
        expectedBook.setReader(reader);
        expectedBook.setTitle(title);
        expectedBook.setAuthor(author);
        expectedBook.setIsbn(isbn);
        expectedBook.setDescription(description);

        mockMvc.perform(MockMvcRequestBuilders.get(requestPath))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("readingList"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("books"))
                .andExpect(MockMvcResultMatchers.model().attribute("books",
                        Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.model().attribute("books",
                        Matchers.contains(Matchers.samePropertyValuesAs(expectedBook))));
    }

}
