package net.kubeworks.kubedashboard.feature.k8sproxy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.util.generic.KubernetesApiResponse;
import io.kubernetes.client.util.generic.dynamic.DynamicKubernetesApi;
import io.kubernetes.client.util.generic.dynamic.DynamicKubernetesListObject;
import io.kubernetes.client.util.generic.dynamic.DynamicKubernetesObject;
import io.kubernetes.client.util.generic.options.ListOptions;
import lombok.extern.slf4j.Slf4j;
import net.kubeworks.kubedashboard.shared.exception.model.BusinessException;
import net.kubeworks.kubedashboard.shared.exception.model.CommonErrorCode;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <a href="https://github.com/kubernetes-client/java">kubernetes-client 공식 GitHub</a>
 * <pre>
 * 리소스 확인
 * <code>kubectl api-resources | grep tekton</code>
 *
 * kube-apiserver 조회 테스트. uri 올바른지 확인
 * <code>kubectl get --raw "/apis/{apiGroup}/{apiVersion}/namespaces/{namespace}/{resource}" | jq</code>
 * <code>kubectl get --raw "/apis/tekton.dev/v1/namespaces/build/pipelines" | jq</code>
 * </pre>
 */
@Slf4j
@Service
public class K8sProxyService {

    private final ApiClient kubeApiClient;
    private final ObjectMapper objectMapper;

    public K8sProxyService(ApiClient kubeApiClient, ObjectMapper objectMapper) {
        this.kubeApiClient = kubeApiClient;
        this.objectMapper = objectMapper;
    }

    public Map<?,?> getList(String apiGroup, String apiVersion, String namespace, String resourcePlural) {
        DynamicKubernetesApi api = new DynamicKubernetesApi(apiGroup, apiVersion, resourcePlural, kubeApiClient);
        KubernetesApiResponse<DynamicKubernetesListObject> response = api.list(namespace, new ListOptions());
        handleResponseError(response);
        JsonObject result = response.getObject().getRaw();
        return parseResponse(result.toString());
    }

    public Map<?,?> getDetails(String apiGroup, String apiVersion, String namespace, String resourcePlural, String resourceName) {
        DynamicKubernetesApi api = new DynamicKubernetesApi(apiGroup, apiVersion, resourcePlural, kubeApiClient);
        KubernetesApiResponse<DynamicKubernetesObject> response = api.get(namespace, resourceName);
        handleResponseError(response);
        JsonObject result = response.getObject().getRaw();
        return parseResponse(result.toString());
    }

    private void handleResponseError(KubernetesApiResponse<?> response) {
        if (response.getHttpStatusCode() == 404) {
            log.warn("Failed to fetch: {}", response.getHttpStatusCode());
            throw new BusinessException(CommonErrorCode.NOT_FOUND, "not found");
        }
        if (!response.isSuccess()) {
            log.error("Failed to fetch: {}", response.getHttpStatusCode());
            throw new BusinessException(CommonErrorCode.INTERNAL_SERVER_ERROR, "fetch failed");
        }
    }

    private Map<?,?> parseResponse(String raw) {
        try {
            return objectMapper.readValue(raw, Map.class);
        } catch (JsonProcessingException e) {
            throw new BusinessException(CommonErrorCode.INTERNAL_SERVER_ERROR, "failed to parse json");
        }
    }
}
