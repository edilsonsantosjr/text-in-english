package com.api.textenglish.textenglish.controllers;

import com.api.textenglish.textenglish.dtos.TextRecordDto;
import com.api.textenglish.textenglish.exceptions.TextException;
import com.api.textenglish.textenglish.models.TextModel;
import com.api.textenglish.textenglish.repositories.TextRepository;
import com.api.textenglish.textenglish.services.TextServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("texts")
public class TextController {

    @Autowired
    private TextRepository textRepository;

    @Autowired
    private TextServiceImpl textServiceImpl;

    @PostMapping
    public ResponseEntity<Object> saveText(@RequestBody @Valid TextRecordDto textRecordDto){
        TextModel textModelObj;
        try {
            textModelObj = textServiceImpl.saveText(textRecordDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(textModelObj);
    }

    @GetMapping
    public ResponseEntity<List<TextModel>> getAllTexts(){
        return ResponseEntity.status(HttpStatus.OK).body(textServiceImpl.getAllTexts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneText(@PathVariable(value ="id") UUID id){
        TextModel textModelObj;
        try{
            textModelObj = textServiceImpl.getOneText(id);
        } catch (TextException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(textModelObj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateText(@PathVariable(value="id") UUID id,
                                             @RequestBody @Valid TextRecordDto textRecordDto){
        TextModel textModelObj;
        try{
            textModelObj = textServiceImpl.updateText(id,textRecordDto);
        }catch (TextException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(textModelObj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteText(@PathVariable(value ="id") UUID id){
        try{
            textServiceImpl.deleteText(id);
        }catch (TextException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body("Text deleted successfully");
    }
}