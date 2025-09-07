package com.example.interfaces;

public interface Electrico {
    void recargar(int minutos);
    int nivelBateria(); // 0 - 100
}
