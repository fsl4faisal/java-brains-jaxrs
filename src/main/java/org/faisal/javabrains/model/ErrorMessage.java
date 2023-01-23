package org.faisal.javabrains.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class ErrorMessage {
    private String errorMessage;
    private int errorCode;
    private String documentation;
}
