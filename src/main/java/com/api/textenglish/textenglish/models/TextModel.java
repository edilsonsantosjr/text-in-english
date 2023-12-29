package com.api.textenglish.textenglish.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "texts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TextModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idText;
    private String title;

    @Column(columnDefinition="text")
    private String text;
    private String date;

}
