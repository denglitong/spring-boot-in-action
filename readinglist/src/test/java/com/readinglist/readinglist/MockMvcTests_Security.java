package com.readinglist.readinglist;

import com.denglitong.readinglist.ReadinglistApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
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
@SpringBootTest(classes = ReadinglistApplication.class)
@WebAppConfiguration
public class MockMvcTests_Security {

    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext)
                // 配置Spring Security的配置器
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void homePage_unauthenticatedUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/readingList"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.header().string("Location",
                        "http://localhost/login"));
    }

    @Test
    // @WithMockUser(username = "denglitong", password = "123456", roles = {"READER"})
    @WithUserDetails(value = "denglitong", userDetailsServiceBeanName = "readerDetailsService")
    public void homePage_authenticatedUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/readingList/dlt"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("readingList"));
    }
}
