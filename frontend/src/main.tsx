import { StrictMode } from "react"
import { createRoot } from "react-dom/client"
import "./index.css"
import Router from "./app/router/index.tsx"

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <Router />
  </StrictMode>,
)
