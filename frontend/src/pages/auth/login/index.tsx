import { LoginForm } from "@/features/auth"
import { useNavigate } from "react-router"

const LoginPage = () => {
  const navigate = useNavigate()

  const handleLoginSuccess = () => {
    navigate("/me")
  }

  return (
    <div>
      <h2>로그인</h2>
      <LoginForm onLoginSuccess={handleLoginSuccess} />
    </div>
  )
}
export default LoginPage
