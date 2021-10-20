package com.nnk.springboot.services.servicesImpl;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CurvePointServiceImpl implements CurvePointService {
    @Autowired
    CurvePointRepository curvePointRepository;

    @Override
    public List<CurvePoint> findAllCurvePoint() {
        return curvePointRepository.findAll();
    }
}
