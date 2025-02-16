import App from "@/App"
import LoginPage from "@/pages/login"
import MePage from "@/pages/me"
import { BrowserRouter, Route, Routes } from "react-router"

const Router = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<App />} />
        <Route path="/vite" element={<App />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/me" element={<MePage />} />
      </Routes>
    </BrowserRouter>
  )
}
export default Router
