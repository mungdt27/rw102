package org.example.backend.controller;


import org.example.backend.service.IPositionService;
import org.example.backend.service.impl.PositonServiceImpl;
import org.example.entity.Position;

import java.util.List;

public class PositionController {
    private IPositionService service;

    public PositionController() {
        this.service = new PositonServiceImpl();
    }

    public List<Position> findAll() {
        return service.findAll();
    }
}