package com.nnk.springboot.services.servicesImpl;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RuleNameServiceImpl implements RuleNameService {
    @Autowired
    RuleNameRepository ruleNameRepository;

    @Override
    public List<RuleName> findAllRuleName() {
        return ruleNameRepository.findAll();
    }

    @Override
    public RuleName add(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }
}
