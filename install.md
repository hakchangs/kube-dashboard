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


## ToDo

api 연계 구조
- 선/후처리 유틸화
- target 서비스 분리 및 공통처리. 인증, 비인증서비스 분리
- 리소스별 URI, 요청/응답 타입관리.
- react query, SWR 사용검토//필요시 사용검토

