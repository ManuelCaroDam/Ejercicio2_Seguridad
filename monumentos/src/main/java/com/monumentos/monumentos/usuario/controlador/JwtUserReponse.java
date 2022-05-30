package com.monumentos.monumentos.usuario.controlador;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class JwtUserReponse {

    private Long id;
    private String username;
    private String token;

}
