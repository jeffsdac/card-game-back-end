package br.com.cardgame.jeff.dtos;

public record CardReturnRegisterDto(
int mana, 
String description, 
int attack, 
int healthPoints, 
String lore ,
String tittle
){}
