# install

## frontend

### 프로젝트 생성
> [vite](https://ko.vite.dev/guide/), [react](https://ko.react.dev/), [react legacy](https://ko.legacy.reactjs.org/docs/getting-started.html)
```bash
npm create vite@latest

# 선택메뉴
# - project name: frontend
# - framework: React
# - variant: Typescript + SWC

```

### router 추가
> [react-router](https://reactrouter.com/start/library/installation)
```bash
npm install react-router
```

### 절대경로(`@`) import 설정
필요 라이브러리 설치
```bash
npm install vite-tsconfig-paths --save-dev
```

vite 설정
```ts
# vite.config.ts
import tsconfigPaths from 'vite-tsconfig-paths';

export default defineConfig({
  // path 지원
  plugins: [react(), tsconfigPaths()],
  // `@` 절대경로 변경
  resolve: {
    alias: {
      find: '@',
      replacement: '/src',
    },
  },
})
```

vscode 자동완성 설정
```ts
# tsconfig.app.json
{
  "compilerOptions": {
    /* 절대경로 설정 */
    "baseUrl": ".",
    "paths": {
      "@/*": ["src/*"],
    },
  },
}
```

### Prettier 설정

필요 라이브러리 설치
```bash
npm install -D prettier eslint-config-prettier eslint-plugin-prettier
```

Prettier 설정파일 추가
```json
# .prettierrc
{
  "singleQuote": true,
  "trailingComma": "all",
  "tabWidth": 2,
  "semi": true,
  "printWidth": 100
}
```

vscode 플러그인 설치
- `esbenp.prettier-vscode` 로 검색하여 플러그인 설치

vscode 포매터 설정. (prettier 플러그인 연동)
```json
# .vscode/settings.json
{
  "editor.defaultFormatter": "esbenp.prettier-vscode",
  "editor.formatOnSave": true,
  "editor.codeActionsOnSave": {
    "source.fixAll.eslint": true
  }
}
```

### design system
> [storybook](https://storybook.js.org/docs/get-started/frameworks/react-vite?renderer=react)

설치
```bash
npx storybook@latest init
```

#### 디자인 관리
혼자 개발하므로 기능개발과 디자인을 분리해야 시간소모가 적을듯함.\
git branch 를 기능(feature)과 디자인(design)으로 분리하여 진행

> 디자인-기능 분리개발
> 
> 좋은 전략 같아! 기능 개발을 빠르게 진행하고, 디자인은 별도 브랜치에서 점진적으로 개선하면 개발 속도도 유지하면서 UI/UX도 다듬을 수 있을 거야.
> 
> 브랜치 전략을 좀 더 구체적으로 정하면 좋을 것 같은데:
> 
>     feature/* 브랜치에서 기능 개발
>     design/* 브랜치에서 스타일링 작업
>     main 또는 develop 브랜치에서 최종 병합


### style 관리
- 후보: css module, styled components, scss, tailwind

설치
> [styled components](https://styled-components.com/docs/basics#installation)
```bash
npm install styled-components
```

vscode 설정
- css 자동완성 설정
- Extensions: vscode-styled-components 검색 및 설치

utility 성격의 스타일이 필요하면 tailwind 조합 검토
- 간격, 크기 등 포멀한 내용은 미리 정의된게 간편할수도 있음

### component - page 조합규칙
> [FSD](https://feature-sliced.design/)
- 도메인 중심
- shared > entities > feature > widgets > pages > app

#### FSD 도입 방향
처음부터 공통사용을 지나치게 고려하하면 시간소모가 많을듯함.\
page, feature 를 우선순위로 구현하고 필요한 상황이 발생한 경우 entities, shared 로 분리&이동

필요한 상황 > 리팩토링
- 비슷한 기능이 반복 (재사용)


### 상태관리(Store)
> [zustand](https://zustand.docs.pmnd.rs/getting-started/introduction)\
> [zustand 핵심정리](https://www.heropy.dev/p/n74Tgc)
- context api, zustand, redux, recoil
- zustand middleware 사용시 localStorage 지원가능

```bash
npm install zustand
```

### proxy 서버
- 백엔드 서버로 넘기는 설정
- http, websocket 모두 지원
```ts
// vite.config.ts
export default defineConfig({
  server: {
    proxy: {
      "/api": {
        target: "http://localhost:8080",
        changeOrigin: true,
        secure: false,
        rewrite: (path) => path.replace(/^\/api/, ""),
        ws: true, //웹소켓 활성화
      },
    },
  },
})
```

### api 연계 구조
- 선/후처리 유틸화
- target 서비스 분리 및 공통처리. 인증, 비인증서비스 분리
- 리소스별 URI, 요청/응답 타입관리.
- react query, SWR 사용검토//필요시 사용검토

## backend

### sqlite 설정
> https://github.com/xerial/sqlite-jdbc

라이브러리 추가
```
# build.gradle
dependencies {
  # jdbc driver
  implementation 'org.xerial:sqlite-jdbc:3.49.0.0'
  # dialect
  implementation 'org.hibernate.orm:hibernate-community-dialects'
}
```

접속설정
```yaml
# application.yaml
spring:
  datasource:
    driver-class-name: org.sqlite.JDBC
    url: jdbc:sqlite:backend.db
    username: admin
    password: admin
```

### springdoc (Swagger/OpenAPI)
> [springdoc](https://springdoc.org/)

라이브러리 추가
```
# build.gradle
dependencies {
    implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.4'
}
```

UI 접속확인
- http://localhost:8080/swagger-ui/index.html


### kubernetes-client/java 설치
> [kubernetes-client/java](https://github.com/kubernetes-client/java)

라이브러리 추가
```gradle
implementation 'io.kubernetes:client-java:22.0.1'
```

kubeconfig 설정
```shell
# k8s 마스터 서버에서 kubeconfig 파일을 가져온다.
scp <k8s-server>:/root/.kube/config <project-path>/.kube/config

# kubeconfig 파일의 접속정보를 변경한다.
vi <project-path>/.kube/config
---
clusters:
- cluster:
    server: <k8s 마스터 서버 접속 HOST>
---

# 개발환경에서 `KUBECONFIG` 환경변수를 설정해준다.
export KUBECONFIG=<project-path>/.kube/config
```

### kube-apiserver proxy 만들기

resource 유형 조회.
```shell
kubectl api-resources | grep tekton
```
- resource 이름, apiVersion, apiGroup, kind 확인

kube-apiserver 로 정상호출하는지 확인
```shell
kubectl get --raw "/apis/{apiGroup}/{apiVersion}/namespaces/{namespace}/{resource}" | jq

# tekton 사용시 예제
kubectl get --raw "/apis/tekton.dev/v1/namespaces/build/pipelines" | jq
```



