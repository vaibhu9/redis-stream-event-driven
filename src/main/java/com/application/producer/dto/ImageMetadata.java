package com.application.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageMetadata {
    private int albumId;
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;
}
