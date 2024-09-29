package com.zero.block.chain.controller;

import com.zero.block.chain.common.model.Balance;
import com.zero.block.chain.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Value("${lottery.contract.owner-address}")
    private String ownerAddress;

    @Autowired
    private Web3j web3j;

    @Autowired
    private LotteryService lotteryService;

    @GetMapping("info")
    public String getAddress() {
        return ownerAddress;
    }

    @GetMapping("balance")
    public Balance getBalance() throws IOException {
        EthGetBalance wei = web3j.ethGetBalance(ownerAddress, DefaultBlockParameterName.LATEST).send();

        return new Balance(wei.getBalance());
    }

    @GetMapping("lottery/players")
    public List<String> getPlayers() throws Exception {
        return lotteryService.getPlayers(ownerAddress);
    }

    @GetMapping("lottery/pickWinner")
    public void pickWinner() throws Exception {
        lotteryService.pickWinner(ownerAddress);
    }
}
