package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CurvePointService {
    List<CurvePoint> findAllCurvePoint();

    CurvePoint add(CurvePoint curvePoint);

    CurvePoint findById(Integer id);

    CurvePoint update(CurvePoint curvePoint);

}
