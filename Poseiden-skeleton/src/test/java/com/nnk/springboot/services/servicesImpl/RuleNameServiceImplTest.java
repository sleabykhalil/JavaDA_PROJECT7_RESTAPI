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
import java.util.Optional;

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

    @Test
    void add() {
        //given
        RuleName ruleName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        when(ruleNameRepositoryMock.save(ruleName)).thenReturn(ruleName);
        //when
        RuleName result = ruleNameServiceUnderTest.add(ruleName);
        //then
        assertThat(result.getName()).isEqualTo(result.getName());
    }

    @Test
    void findById_whenFound_ReturnCurvePoint() {
        //given
        RuleName ruleName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        ruleName.setId(1);
        when(ruleNameRepositoryMock.findById(1)).thenReturn(Optional.of(ruleName));
        //when
        RuleName result = ruleNameServiceUnderTest.findById(1);
        //then
        assertThat(result.getId()).isEqualTo(ruleName.getId());
    }

    @Test
    void findById_whenNotFound_ReturnNull() {
        //given
        when(ruleNameRepositoryMock.findById(1)).thenReturn(Optional.empty());
        //when
        RuleName result = ruleNameServiceUnderTest.findById(1);
        //then
        assertThat(result).isNull();
    }

    @Test
    void update() {
        //given
        RuleName ruleName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        ruleName.setId(1);
        when(ruleNameRepositoryMock.save(ruleName)).thenReturn(ruleName);
        //when
        RuleName result = ruleNameServiceUnderTest.update(ruleName);
        //then
        assertThat(result.getId()).isEqualTo(ruleName.getId());
    }
}