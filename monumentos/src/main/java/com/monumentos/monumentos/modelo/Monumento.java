package com.monumentos.monumentos.modelo;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Monumento {

    @Id @GeneratedValue
    private Long id;
    private String codigoPais;
    private String pais;
    private String ciudad;
    private String localizacion;
    private String nombre;
    private String descripcion;
    private String url;

    @ManyToOne
    private Categoria categoria;
}
