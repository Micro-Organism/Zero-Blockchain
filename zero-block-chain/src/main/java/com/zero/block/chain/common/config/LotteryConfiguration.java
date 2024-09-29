package com.zero.block.chain.common.config;

import com.zero.block.chain.common.model.Lottery;
import com.zero.block.chain.common.properties.LotteryProperties;
import com.zero.block.chain.service.LotteryService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;

@Slf4j
@Configuration
public class LotteryConfiguration {

    @Value("${lottery.contract.owner-address}")
    private String ownerAddress;

    @Value("${web3j.client-address}")
    private String clientAddress;

    @Autowired
    private LotteryProperties config;

    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService(clientAddress, new OkHttpClient.Builder().build()));
    }

    @Bean
    public LotteryService contract(Web3j web3j, @Value("${lottery.contract.address:}") String contractAddress) throws Exception {
        if (StringUtils.isEmpty(contractAddress)) {
            Lottery lottery = deployContract(web3j);
            return initLotteryService(lottery.getContractAddress(), web3j);
        }
        return initLotteryService(contractAddress, web3j);
    }

    private LotteryService initLotteryService(String contractAddress, Web3j web3j) {

//        LotteryService lotteryService = new LotteryServiceImpl();
//        lotteryService.init(contractAddress, web3j, config);
//        return lotteryService;

        return new LotteryService(contractAddress, web3j, config);
    }

    private Lottery deployContract(Web3j web3j) throws Exception {
        log.info("About to deploy new contract...");
        Lottery contract = Lottery.deploy(web3j, txManager(web3j), config.gas()).send();
        log.info("Deployed new contract with address '{}'", contract.getContractAddress());
        return contract;
    }

    private TransactionManager txManager(Web3j web3j) {
        return new ClientTransactionManager(web3j, ownerAddress);
    }

}
