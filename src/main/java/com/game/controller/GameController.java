package com.game.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.game.model.GameState;
import com.game.service.GameService;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private GameState state = new GameState();
    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @PostMapping("/move")
    public GameState move(
            @RequestParam int index,
            @RequestParam String difficulty
    ) {
        return service.play(state, index, difficulty);
    }

    @GetMapping("/reset")
    public GameState reset() {
        state = new GameState();
        return state;
    }
}
