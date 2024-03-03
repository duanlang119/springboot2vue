package com.limu.mockito.bean.req;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class UserAddReq {
    @NotBlank
    private String username;
    @NotBlank
    private String phone;
    /**
     * 特征列表
     */
    @NotEmpty
    private List<String> features;
}
