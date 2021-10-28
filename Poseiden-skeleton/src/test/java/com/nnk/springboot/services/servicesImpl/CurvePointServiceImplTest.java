package com.nnk.springboot.services.servicesImpl;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.BidListService;
import com.nnk.springboot.services.CurvePointService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

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


    @Test
    void add() {
        //given
        CurvePoint curvePoint = new CurvePoint(10, 10d, 10d);
        when(curvePointRepositoryMock.save(curvePoint)).thenReturn(curvePoint);
        //when
        CurvePoint result = curvePointServiceUnderTest.add(curvePoint);
        //then
        assertThat(result.getCurveId()).isEqualTo(curvePoint.getCurveId());
    }

    @Test
    void findById_whenFound_ReturnCurvePoint() {
        //given
        CurvePoint curvePoint = new CurvePoint(10, 10d, 10d);
        curvePoint.setId(1);
        when(curvePointRepositoryMock.findById(1)).thenReturn(Optional.of(curvePoint));
        //when
        CurvePoint result = curvePointServiceUnderTest.findById(1);
        //then
        assertThat(result.getCurveId()).isEqualTo(curvePoint.getCurveId());
    }

    @Test
    void findById_whenNotFound_ReturnNull() {
        //given
        when(curvePointRepositoryMock.findById(1)).thenReturn(Optional.empty());
        //when
        CurvePoint result = curvePointServiceUnderTest.findById(1);
        //then
        assertThat(result).isNull();
    }

    @Test
    void update() {
        //given
        CurvePoint curvePoint = new CurvePoint(10, 10d, 10d);
        curvePoint.setId(1);
        when(curvePointRepositoryMock.save(curvePoint)).thenReturn(curvePoint);
        //when
        CurvePoint result = curvePointServiceUnderTest.update(curvePoint);
        //then
        assertThat(result.getCurveId()).isEqualTo(curvePoint.getCurveId());
    }

    @Test
    void delete() {
        //given
        CurvePoint curvePoint = new CurvePoint(10, 10d, 10d);
        curvePoint.setId(1);
        when(curvePointRepositoryMock.findById(1)).thenReturn(Optional.of(curvePoint));
        doNothing().when(curvePointRepositoryMock).deleteById(1);
        //when
        curvePointServiceUnderTest.delete(1);
        //then
        verify(curvePointRepositoryMock, times(1)).deleteById(1);
    }
}