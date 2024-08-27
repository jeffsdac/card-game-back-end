package br.com.cardgame.jeff.dtos;

public record UserEntityUpdateDto(String newUsername, String password, String oldUsername) {
}
