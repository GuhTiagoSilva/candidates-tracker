package com.stonks.candidatestracker.dto;

import com.stonks.candidatestracker.dto.responses.SkillGetResponseDto;
import com.stonks.candidatestracker.models.TestModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String title;

    private SkillGetResponseDto skill = new SkillGetResponseDto();

    public TestDto(TestModel test) {
        id = test.getId();
        title = test.getTitle();
        skill = new SkillGetResponseDto(test.getSkill());
    }
}
