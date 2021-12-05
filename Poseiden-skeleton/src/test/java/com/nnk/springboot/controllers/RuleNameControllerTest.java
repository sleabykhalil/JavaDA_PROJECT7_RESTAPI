package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "user"
        , password = "123456")
class RuleNameControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    RuleNameRepository ruleNameRepository;

    @Test
    void getRuleNameList() throws Exception {
        mockMvc.perform(get("/ruleName/list"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void addRuleForm() throws Exception {
        mockMvc.perform(get("/ruleName/add"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void validate() throws Exception {
        RuleName ruleName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        mockMvc.perform(post("/ruleName/validate")
                        .flashAttr("ruleName", ruleName))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }

    @Test
    void showUpdateForm() throws Exception {
        RuleName ruleName = ruleNameRepository.save(new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part"));
        mockMvc.perform(get("/ruleName/update/{id}", ruleName.getId().toString()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateRuleName() throws Exception {
        RuleName ruleName = ruleNameRepository.save(new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part"));
        RuleName newRuleName = new RuleName("New Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        mockMvc.perform(post("/ruleName/update/{id}", ruleName.getId().toString())
                        .flashAttr("ruleName", newRuleName))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }

    @Test
    void deleteRuleName() throws Exception {
        RuleName ruleName = ruleNameRepository.save(new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part"));
        mockMvc.perform(get("/ruleName/delete/{id}", ruleName.getId().toString()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
        Optional<RuleName> result = ruleNameRepository.findById(ruleName.getId());
        Assertions.assertThat(result.isEmpty()).isTrue();
    }

}