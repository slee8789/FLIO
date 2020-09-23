package com.fund.flio.data.model.body;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TestBody {
    private String loginType;
    private String email;
    private String password;
}