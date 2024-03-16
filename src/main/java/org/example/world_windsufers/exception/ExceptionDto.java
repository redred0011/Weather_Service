package org.example.world_windsufers.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ExceptionDto {

    @JsonFormat
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final String message;
}