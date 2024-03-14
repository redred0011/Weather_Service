package org.example.world_windsufers.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.world_windsufers.common.Destination;


@Setter
@Getter
@RequiredArgsConstructor
public class Weather {
    private final Destination destination;
}
