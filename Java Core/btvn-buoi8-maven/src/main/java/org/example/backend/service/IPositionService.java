package org.example.backend.service;


import org.example.entity.Position;

import java.util.List;

public interface IPositionService {
    List<Position> findAll();
}