package com.stonks.candidatestracker.dto.responses;

import com.stonks.candidatestracker.models.SkillModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkillGetResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String skillName;

    public SkillGetResponseDto(SkillModel skill) {
        id = skill.getId();
        skillName = skill.getSkillName();
    }

}
