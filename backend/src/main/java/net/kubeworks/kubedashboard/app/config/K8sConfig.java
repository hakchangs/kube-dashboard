package net.kubeworks.kubedashboard.app.config;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.context.annotation.Bean;

import java.io.FileReader;
import java.io.IOException;

public class K8sConfig {

    @Bean
    public ApiClient kubeApiClient() throws IOException, ApiException {

        String kubeConfigPath = System.getenv("KUBECONFIG");
        ApiClient client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();
        OkHttpClient httpClient = client.getHttpClient().newBuilder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        client.setHttpClient(httpClient);
        Configuration.setDefaultApiClient(client);

        return client;
    }

}
