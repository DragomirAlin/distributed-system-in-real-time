package com.dragomiralin.smartdata.device.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder @AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Document
public class Topic {

    private String id;
    private String topic;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date lastModifiedAt;
}
