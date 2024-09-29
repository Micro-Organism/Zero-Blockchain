package com.zero.block.chain.service.impl;

import com.zero.block.chain.common.model.Lottery;
import com.zero.block.chain.common.model.Player;
import com.zero.block.chain.common.properties.LotteryProperties;
import com.zero.block.chain.service.LotteryService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

//@Service
//public class LotteryServiceImpl implements LotteryService {
//
//    private String contractAddress;
//    private Web3j web3j;
//    private LotteryProperties config;
//
//    @Override
//    @PostConstruct
//    public void init(String contractAddress, Web3j web3j, LotteryProperties config){
//        this.contractAddress = contractAddress;
//        this.web3j = web3j;
//        this.config = config;
//    }
//
//    @Override
//    public BigInteger getBalance() throws IOException {
//        return web3j.ethGetBalance(contractAddress, DefaultBlockParameterName.LATEST).send().getBalance();
//    }
//
//    @Override
//    public void join(Player player) throws Exception {
//        Lottery lottery = loadContract(player.getAddress());
//        lottery.enter(Convert.toWei(player.getEthers(), Unit.ETHER).toBigInteger()).send();
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public List<String> getPlayers(String ownerAddress) throws Exception {
//        Lottery lottery = loadContract(ownerAddress);
//        return lottery.getPlayers().send();
//    }
//
//    @Override
//    public void pickWinner(String ownerAddress) throws Exception {
//        Lottery lottery = loadContract(ownerAddress);
//        lottery.pickWinner().send();
//    }
//
//    private Lottery loadContract(String accountAddress) {
//        return Lottery.load(contractAddress, web3j, txManager(accountAddress), config.gas());
//    }
//
//    private TransactionManager txManager(String accountAddress) {
//        return new ClientTransactionManager(web3j, accountAddress);
//    }
//}
