package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RuleNameService {
    List<RuleName> findAllRuleName();

    RuleName add(RuleName ruleName);

    RuleName findById(Integer id);

    RuleName update(RuleName ruleName);
}
