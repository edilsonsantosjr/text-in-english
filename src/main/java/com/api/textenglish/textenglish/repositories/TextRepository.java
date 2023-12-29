package com.api.textenglish.textenglish.repositories;

import com.api.textenglish.textenglish.models.TextModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface TextRepository extends JpaRepository<TextModel, UUID> {
}
