package ru.irk.usef.vds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class VdsHelper {
    private static final Logger logger = LoggerFactory.getLogger(VdsHelper.class);
    /**
     * enabling proxy
     */
    @Bean
    public static RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 3138));
        requestFactory.setProxy(proxy);
        return new RestTemplate(requestFactory);
    }

    public static Map<String, Balance> getSpares() {
        Map<String, Balance> balances;
        try {
            balances = restTemplate().exchange(Urls.SPARES.getUrl(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<HashMap<String, Balance>>() {}).getBody();
            logger.info("Got " + Urls.SPARES.getUrl());
        }
        catch(RestClientException e) {
            logger.error("Couldn't get " + Urls.SPARES.getUrl());
            logger.error(e.getMessage(), e);
            balances = new HashMap<>(0);
        }
        return balances;
    }



    public static Map<String, Balance> getSpareBalances() {
        Map<String, Balance> balances = getSpares();
        Map<String, Map<String, List<String>>> alternatives;
        try {
            alternatives = restTemplate().exchange(Urls.ALTERNATIVES.getUrl(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Map<String, Map<String, List<String>>>>() {
                    }).getBody();
            logger.info("Got " + Urls.ALTERNATIVES.getUrl());
        }
        catch (RestClientException e) {
            logger.error("Couldn't get " + Urls.ALTERNATIVES.getUrl());
            logger.error(e.getMessage(), e);
            alternatives = new HashMap<>(0);
        }

        Map<String, Balance> newBalances = new HashMap<>(balances.size());
        if (balances.size() == 0) return newBalances;

        Balance spareBalance;
        Balance newBalance;
        if (alternatives != null) {
            for (Map.Entry<String, List<String>> entry : alternatives.getOrDefault("alternatives",
                    new HashMap<>()).entrySet()) {
                newBalance = new Balance();
                for (String spareName : entry.getValue()) {
                    spareBalance = balances.getOrDefault(spareName,
                            new Balance());
                    newBalance.setCount(newBalance.getCount() + spareBalance.getCount());
                    newBalance.setMustbe(Math.max(newBalance.getMustbe(), spareBalance.getMustbe()));
                    newBalance.setArrive(newBalance.getArrive() + spareBalance.getArrive());
                    balances.remove(spareName);
                }
                newBalances.put(entry.getKey(), newBalance);
            }
        }
        newBalances.putAll(balances);
        newBalances.forEach((k, v) -> v.setAlert(v.getMustbe() > v.getArrive() + v.getCount()));
        return newBalances;
    }
}
