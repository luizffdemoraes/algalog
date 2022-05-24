package com.algaworks.algalog.domain.model.input;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.math.BigDecimal;

@Getter
@Setter
public class EntregaInput {

    @Valid
    @NotNull
    private ClienteIdInput cliente;

    @NotNull
    private BigDecimal taxa;

    @Valid
    @NotNull
    private DestinatarioInput destinatario;
}
