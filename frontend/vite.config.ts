import { defineConfig } from "vite"
import react from "@vitejs/plugin-react-swc"
import tsconfigPaths from "vite-tsconfig-paths"

// https://vite.dev/config/
export default defineConfig({
  // path 지원
  plugins: [react(), tsconfigPaths()],
  // `@` 절대경로 변경
  resolve: {
    alias: {
      find: "@",
      replacement: "/src",
    },
  },
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
