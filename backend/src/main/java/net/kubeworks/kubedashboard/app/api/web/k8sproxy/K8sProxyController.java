package net.kubeworks.kubedashboard.app.api.web.k8sproxy;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.kubeworks.kubedashboard.feature.k8sproxy.service.K8sProxyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Tag(name = "k8s-proxy", description = "kube-apiserver 요청전달")
@RequestMapping("/k/apis")
public class K8sProxyController {

    private final K8sProxyService k8sProxyService;

    public K8sProxyController(K8sProxyService k8sProxyService) {
        this.k8sProxyService = k8sProxyService;
    }

    @GetMapping("/{apiGroup}/{apiVersion}/namespaces/{namespace}/{resourcePlural}")
    @Operation(summary = "apiGroup=<other> (core 제외) 리스트 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공. resource 유형에 맞는 object 가 반환된다.", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "권한없음", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "리소스 없음", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(schema = @Schema())),
    })
    public Map<?, ?> getList(@PathVariable String apiGroup, @PathVariable String apiVersion,
                             @PathVariable String namespace, @PathVariable String resourcePlural) {
        return k8sProxyService.getList(apiGroup, apiVersion, namespace, resourcePlural);
    }

    @GetMapping("/{apiGroup}/{apiVersion}/namespaces/{namespace}/{resourcePlural}/{resourceName}")
    @Operation(summary = "apiGroup=<other> (core 제외) 상세 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공. resource 유형에 맞는 object 가 반환된다.", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "권한없음", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "리소스 없음", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(schema = @Schema())),
    })
    public Map<?, ?> getDetails(@PathVariable String apiGroup, @PathVariable String apiVersion,
                                @PathVariable String namespace, @PathVariable String resourcePlural,
                                @PathVariable String resourceName) {
        return k8sProxyService.getDetails(apiGroup, apiVersion, namespace, resourcePlural, resourceName);
    }
}
