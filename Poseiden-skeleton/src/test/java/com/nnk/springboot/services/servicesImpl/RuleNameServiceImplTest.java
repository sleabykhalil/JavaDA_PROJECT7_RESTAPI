package com.nnk.springboot.services.servicesImpl;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RuleNameServiceImplTest {
    @Mock
    RuleNameRepository ruleNameRepositoryMock;

    RuleNameService ruleNameServiceUnderTest;

    @BeforeEach
    void setUp() {
        ruleNameServiceUnderTest = new RuleNameServiceImpl(ruleNameRepositoryMock);
    }

    @Test
    void findAllRuleName() {
        //given
        RuleName ruleName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        List<RuleName> ruleNameList = new ArrayList<>();
        ruleNameList.add(ruleName);
        when(ruleNameRepositoryMock.findAll()).thenReturn(ruleNameList);
        //when
        List<RuleName> result = ruleNameServiceUnderTest.findAllRuleName();
        //then
        assertThat(result.size()).isGreaterThan(0);
    }
}