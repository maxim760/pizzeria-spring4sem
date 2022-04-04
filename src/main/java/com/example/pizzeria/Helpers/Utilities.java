package com.example.pizzeria.Helpers;

import org.springframework.stereotype.Component;

@Component
public class Utilities {
    static public int ceil(int a) {
        return (int)Math.ceil((double)a);
    }
}
