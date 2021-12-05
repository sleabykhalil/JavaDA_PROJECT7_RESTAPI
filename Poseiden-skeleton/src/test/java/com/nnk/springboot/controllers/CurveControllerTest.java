package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
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
class CurveControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    CurvePointRepository curvePointRepository;

    @Test
    void getCurvePoint() throws Exception {
        mockMvc.perform(get("/curvePoint/list"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void addCurvePointForm() throws Exception {
        mockMvc.perform(get("/curvePoint/add"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void validateCurvePoint() throws Exception {
        CurvePoint curvePoint = curvePointRepository.save(new CurvePoint(10, 10d, 30d));
        mockMvc.perform(post("/curvePoint/validate")
                        .flashAttr("curvePoint", curvePoint))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    @Test
    void showCurvePointUpdateForm() throws Exception {
        CurvePoint curvePoint = curvePointRepository.save(new CurvePoint(10, 10d, 30d));
        mockMvc.perform(get("/curvePoint/update/{id}", curvePoint.getId().toString()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateCurvePoint() throws Exception {
        CurvePoint curvePoint = curvePointRepository.save(new CurvePoint(10, 10d, 30d));
        CurvePoint newCurvePoint = curvePointRepository.save(new CurvePoint(100, 10d, 30d));
        mockMvc.perform(post("/curvePoint/update/{id}", curvePoint.getCurveId().toString())
                        .flashAttr("curvePoint", newCurvePoint))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    @Test
    void deleteCurvePoint() throws Exception {
        CurvePoint curvePoint = curvePointRepository.save(new CurvePoint(10, 10d, 30d));
        mockMvc.perform(get("/curvePoint/delete/{id}", curvePoint.getCurveId().toString()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
        Optional<CurvePoint> result = curvePointRepository.findById(curvePoint.getCurveId());
        Assertions.assertThat(result.isEmpty()).isTrue();
    }
}