package com.api.textenglish.textenglish.services;

import com.api.textenglish.textenglish.dtos.TextRecordDto;
import com.api.textenglish.textenglish.exceptions.TextException;
import com.api.textenglish.textenglish.models.TextModel;
import com.api.textenglish.textenglish.repositories.TextRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.*;

@Service
public class TextServiceImpl implements TextService {

    @Autowired
    private TextRepository textRepository;

    public TextModel saveText(TextRecordDto textRecordDto){
        var textModel = new TextModel();
        BeanUtils.copyProperties(textRecordDto, textModel);

        //adding data to text
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
        String dateTextModel = dateFormat.format(date);
        textModel.setDate(dateTextModel);

        textRepository.save(textModel);
        return textModel;
    }

    public List<TextModel> getAllTexts(){
        return textRepository.findAll();
    }

    public TextModel getOneText(UUID id){
        Optional<TextModel> textModel = textRepository.findById(id);
        if(textModel.isEmpty()){
            throw new TextException("Text not found.");
        }
        return textModel.get();
    }

    public TextModel updateText(UUID id ,TextRecordDto textRecordDto){
        Optional<TextModel> textModel = textRepository.findById(id);
        if(textModel.isEmpty()){
            throw new TextException("Text not found.");
        }
        var textModelObj = textModel.get();
        BeanUtils.copyProperties(textRecordDto, textModelObj);
        return textModelObj;
    }

    public void deleteText(UUID id){
        Optional<TextModel> textModel = textRepository.findById(id);
        if(textModel.isEmpty()){
            throw new TextException("Text not found.");
        }
        textRepository.delete(textModel.get());
    }

}
