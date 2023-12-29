package com.api.textenglish.textenglish.services;

import com.api.textenglish.textenglish.dtos.TextRecordDto;
import com.api.textenglish.textenglish.models.TextModel;

import java.util.List;
import java.util.UUID;

public interface TextService {

    TextModel saveText(TextRecordDto textRecordDto);
    List<TextModel> getAllTexts();
    TextModel getOneText(UUID id);
    TextModel updateText(UUID id ,TextRecordDto textRecordDto);
    void deleteText(UUID id);
}
