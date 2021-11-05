package com.javaninjas.blackjack.service;

import java.util.LinkedHashMap;
import java.util.Map;

public class Table {
    private Map<Integer, Player> playerMap;

    public Table(){
        playerMap = new LinkedHashMap<>();
    }

    public void addPlayer(Integer id, Player player){
        playerMap.put(id, player);
    }

}