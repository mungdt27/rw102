package org.example.backend.controller;


import org.example.backend.service.IPositionService;
import org.example.backend.service.impl.PositionServiceImpl;
import org.example.entity.Position;

import java.util.List;

public class PositionController {
    private IPositionService service;

    public PositionController() {
        this.service = new PositionServiceImpl();
    }

    public List<Position> findAll() {
        return service.findAll();
    }
}