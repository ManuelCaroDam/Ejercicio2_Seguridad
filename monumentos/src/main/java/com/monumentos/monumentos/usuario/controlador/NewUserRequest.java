package com.monumentos.monumentos.usuario.controlador;

import lombok.Value;

@Value
public class NewUserRequest {

    private String username;
    private String password;
    private String verifyPassword;
}
