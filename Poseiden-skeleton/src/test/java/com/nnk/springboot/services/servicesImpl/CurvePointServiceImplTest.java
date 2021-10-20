package com.nnk.springboot.services.servicesImpl;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurvePointServiceImplTest {
    @Mock
    CurvePointRepository curvePointRepositoryMock;

    CurvePointService curvePointServiceUnderTest;

    @BeforeEach
    void setUp() {
        curvePointServiceUnderTest = new CurvePointServiceImpl(curvePointRepositoryMock);
    }

    @Test
    void findAllCurvePoint() {
        //given
        CurvePoint curvePoint = new CurvePoint(10, 10d, 10d);
        List<CurvePoint> curvePointList = new ArrayList<>();
        curvePointList.add(curvePoint);
        when(curvePointRepositoryMock.findAll()).thenReturn(curvePointList);
        //when
        List<CurvePoint> result = curvePointServiceUnderTest.findAllCurvePoint();
        //then
        assertThat(result.size()).isGreaterThan(0);
    }


}