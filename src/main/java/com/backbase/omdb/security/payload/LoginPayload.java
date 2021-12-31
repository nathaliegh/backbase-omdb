package com.backbase.omdb.security.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginPayload {

    @NotNull
    @NotEmpty
    @Email
    private String username;

    @NotNull
    @NotEmpty
    private String password;
}
