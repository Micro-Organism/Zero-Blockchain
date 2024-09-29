package com.zero.block.chain.common.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Player {
    private String address;
    private BigDecimal ethers;
}
