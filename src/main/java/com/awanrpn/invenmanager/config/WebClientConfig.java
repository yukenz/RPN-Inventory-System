package com.awanrpn.invenmanager.config;

import com.awanrpn.invenmanager.webclient.ElasticSearchClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Bean
    ElasticSearchClient elasticSearchClient() {

        WebClient webClient = WebClient.builder().
                baseUrl("http://localhost:9200")
                .build();

        HttpServiceProxyFactory hspf = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .build();

        return hspf.createClient(ElasticSearchClient.class);
    }

}
