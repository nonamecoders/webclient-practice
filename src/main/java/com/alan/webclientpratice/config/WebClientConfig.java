package com.alan.webclientpratice.config;

import com.alan.webclientpratice.filter.RequestLoggingFilterFunction;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.*;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


@Configuration
@Slf4j
public class WebClientConfig {

    @Value("${api.token}")
    private String token;

    @Bean
    public WebClient webClient() {

        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofMillis(5000))
                .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));
        ClientHttpConnector conn = new ReactorClientHttpConnector(httpClient);

        ExchangeStrategies exchangeStrategies = ExchangeStrategies
                .builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
                .build();

        return WebClient.builder()
//                .baseUrl(baseUrl)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add("X-Riot-Token",token);
                })
                .clientConnector(conn)
                .filter(
                        (req, next) -> next.exchange(
                                ClientRequest.from(req).header("from", "webclient").build()
                        )
                )
                .filter(
                        new RequestLoggingFilterFunction()
                )
                .exchangeStrategies(exchangeStrategies)
                .defaultHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.3")
                .defaultCookie("httpclient-type", "webclient")
                .build();
    }

    /**
     * WebClient Filter 예제
     * WebClient.builder().filter()
     * >> RequestLoggingFilterFunction 으로 새로 생성했음
    public ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor((clientRequest) -> {
//            if (log.isDebugEnabled()) {
            StringBuilder sb = new StringBuilder("logRequest(): \n");
            //append clientRequest method and url
            sb.append(clientRequest.url()).append("\n");
            clientRequest
                    .headers()
                    .forEach((name, values) -> values.forEach(value -> sb.append(name).append(" : ").append(value).append("\n")));
            log.info(sb.toString());
//            }
            return Mono.just(clientRequest);
        });
    }

    public ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
//            if (log.isDebugEnabled()) {
            StringBuilder sb = new StringBuilder("logResponse(): \n");
            //append clientRequest method and url
            clientResponse
                    .headers()
                    .asHttpHeaders()
                    .forEach((name, values) -> values.forEach(value -> sb.append(name).append(" : ").append(value).append("\n")));
            log.info(sb.toString());
//            }
            return Mono.just(clientResponse);
        });
    }
*/

}

