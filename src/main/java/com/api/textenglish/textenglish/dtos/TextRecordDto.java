package com.api.textenglish.textenglish.dtos;

import jakarta.validation.constraints.NotBlank;

public record TextRecordDto(@NotBlank String title,@NotBlank String text) {
}
