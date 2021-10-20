package com.nnk.springboot.services.servicesImpl;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    /**
     * add new Curve Point
     *
     * @param curvePoint
     * @return
     */
    @Override
    public CurvePoint add(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    /**
     * find CurvePoint by Id
     *
     * @param id
     * @return
     */
    @Override
    public CurvePoint findById(Integer id) {
        Optional<CurvePoint> curvePoint = curvePointRepository.findById(id);
        if (curvePoint.isPresent()) {
            log.debug("Curve Point found id=[{}]", id);
            return curvePoint.get();
        } else {
            log.debug("Curve Point can not found id=[{}]", id);
            return null;
        }
    }

    /**
     * update curve point
     *
     * @param curvePoint
     * @return
     */
    @Override
    public CurvePoint update(CurvePoint curvePoint) {
        CurvePoint curvePointAfterUpdate = curvePointRepository.save(curvePoint);
        log.debug("Curve Point updated id=[{}]", curvePointAfterUpdate.getCurveId());
        return curvePointAfterUpdate;
    }

    /**
     * delete curve point by id
     *
     * @param id
     * @return
     */
    @Override
    public void delete(Integer id) {
        curvePointRepository.deleteById(id);
        log.debug("curve point deleted id=[{}]", id);
    }
}
