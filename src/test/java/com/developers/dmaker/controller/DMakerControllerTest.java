package com.developers.dmaker.controller;

import com.developers.dmaker.dto.DeveloperDto;
import com.developers.dmaker.dto.DeveloperDtoBuilder;
import com.developers.dmaker.entity.DeveloperSkillType;
import com.developers.dmaker.service.DMakerService;
import com.developers.dmaker.type.DeveloperLevel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DMakerController.class)
class DMakerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DMakerService dMakerService;

    protected final MediaType contentType =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    StandardCharsets.UTF_8);

    @Test
    void getAllDevelopers() throws Exception {
        DeveloperDto juniorDeveloperDto = DeveloperDtoBuilder.builder()
                .developerSkillType(DeveloperSkillType.BACK_END)
                .developerLevel(DeveloperLevel.JUNIOR)
                .memberId("memberId1").build();

        DeveloperDto seniorDeveloperDto = DeveloperDtoBuilder.builder()
                .developerSkillType(DeveloperSkillType.FRONT_END)
                .developerLevel(DeveloperLevel.SENIOR)
                .memberId("memberId2").build();

        given(dMakerService.getAllDevelopers())
                .willReturn(Arrays.asList(juniorDeveloperDto, seniorDeveloperDto));

        mockMvc.perform(get("/developers").contentType(contentType))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(
                        jsonPath("$.[0].developerSkillType",
                                is(DeveloperSkillType.BACK_END.name()))
                ).andExpect(
                        jsonPath("$.[0].developerLevel",
                                is(DeveloperLevel.JUNIOR.name())))
                .andExpect(
                        jsonPath("$.[1].developerSkillType",
                                is(DeveloperSkillType.FRONT_END.name())))
                .andExpect(
                        jsonPath("$.[1].developerLevel",
                                is(DeveloperLevel.SENIOR.name()))
                );
    }
}