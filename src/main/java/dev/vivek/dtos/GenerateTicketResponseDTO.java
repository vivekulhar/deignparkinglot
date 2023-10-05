package dev.vivek.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateTicketResponseDTO {
    private Long generatedTicketId;
    private String message;
    private ResponseStatus status;

}
