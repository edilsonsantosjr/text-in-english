package com.api.textenglish.textenglish.dtos;

import com.api.textenglish.textenglish.models.UserRole;

public record RegisterDto(String login, String password, UserRole role) {
}
