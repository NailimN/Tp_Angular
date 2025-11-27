package fr.biblio.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record AuthRequest(@NotEmpty String username, @NotEmpty String password) {

}