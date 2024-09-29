package com.zero.block.chain.controller;

import com.zero.block.chain.common.model.Balance;
import com.zero.block.chain.common.model.Player;
import com.zero.block.chain.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/lottery")
public class LotteryController {

    LotteryService lotteryService;

    @Autowired
    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @GetMapping("balance")
    public Balance getLotteryBalance() throws IOException {
        return new Balance(lotteryService.getBalance());
    }

    @PostMapping("join")
    public void joinLottery(@RequestBody Player player) throws Exception {
        lotteryService.join(player);
    }
}
