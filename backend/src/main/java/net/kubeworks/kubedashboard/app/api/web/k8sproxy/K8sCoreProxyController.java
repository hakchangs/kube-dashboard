package net.kubeworks.kubedashboard.app.api.web.k8sproxy;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.kubeworks.kubedashboard.feature.k8sproxy.service.K8sProxyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@Tag(name = "k8s-proxy", description = "kube-apiserver 요청전달")
@RequestMapping("/k/api")
public class K8sCoreProxyController {

    private final K8sProxyService k8sProxyService;

    public K8sCoreProxyController(K8sProxyService k8sProxyService) {
        this.k8sProxyService = k8sProxyService;
    }

    @GetMapping("/{apiVersion}/namespaces/{namespace}/{resourcePlural}")
    @Operation(summary = "apiGroup=core 리스트 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공. resource 유형에 맞는 object 가 반환된다.", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "권한없음", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "리소스 없음", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(schema = @Schema())),
    })
    public Map<?, ?> getList(@PathVariable String apiVersion, @PathVariable String namespace, @PathVariable String resourcePlural) {
        return k8sProxyService.getList("", apiVersion, namespace, resourcePlural);
    }

    @GetMapping("/{apiVersion}/namespaces/{namespace}/{resourcePlural}/{resourceName}")
    @Operation(summary = "apiGroup=core 상세 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공. resource 유형에 맞는 object 가 반환된다.", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "권한없음", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "리소스 없음", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(schema = @Schema())),
    })
    public Map<?, ?> getDetails(@PathVariable String apiVersion, @PathVariable String namespace, @PathVariable String resourcePlural, @PathVariable String resourceName) {
        return k8sProxyService.getDetails("", apiVersion, namespace, resourcePlural, resourceName);
    }
}
