package com.gdutelc.recruit.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author TUFSolareyes
 * @date 22/09/17
 */
@Data
public class SignInDTO {

    @Getter
    @Setter
    private String stuName;

    @Getter
    @Setter
    private String stuId;

    public SignInDTO() {
    }

    public SignInDTO(String stuName, String stuId) {
        this.stuName = stuName;
        this.stuId = stuId;
    }

}
