package com.api.BlogApplication.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private int user_id;
    private String name;
    private String email;
    private String about;

}
