package com.automation.core.config;

import lombok.Data;

@Data
public class SwipeConfig {
    private int distance = 75;
    private int maxSwipeUntilFound = 5;
}