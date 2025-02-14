import { useForm } from "react-hook-form"
import * as yup from "yup"
import { yupResolver } from "@hookform/resolvers/yup"
import { useAuthStore } from "../model/authStore"
import { KeyboardEvent, useState } from "react"
import { LoginFormData } from "../types"
import { isApiResponseError } from "@/shared/api/http"

const schema = yup.object({
  username: yup.string().required("아이디를 입력해주세요."),
  password: yup.string().required("패스워드를 입력해주세요."),
})

interface LoginFormProps {
  onLoginSuccess: () => void // 로그인 성공 후 실행할 콜백 함수
}

const LoginForm = ({ onLoginSuccess }: LoginFormProps) => {
  const { login } = useAuthStore()
  const [serverError, setServerError] = useState<string | null>(null)
  const [showPassword, setShowPassword] = useState<boolean>(false)
  const {
    register,
    handleSubmit,
    setFocus,
    trigger,
    formState: { errors, isValid, isSubmitting },
  } = useForm<LoginFormData>({
    resolver: yupResolver(schema),
  })

  const handleUsernameKeyDown = async (e: KeyboardEvent) => {
    if (e.key === "Enter") {
      const isValidForm = await trigger()
      if (isValidForm) {
        handleSubmit(submit)()
      } else {
        const firstErrorField = Object.keys(errors)[0] // 첫 번째 오류 필드
        setFocus(firstErrorField as "username" | "password") // 오류 필드로 포커스 이동
      }
    }
  }

  const submit = async (formData: LoginFormData) => {
    try {
      setServerError("")
      await login(formData)
      onLoginSuccess()
    } catch (error) {
      if (isApiResponseError(error)) {
        if (error.status === 400) {
          setServerError("아이디 또는 비밀번호가 올바르지 않습니다.")
        } else {
          setServerError(error.body.message)
        }
      } else {
        setServerError("" + error)
      }
    }
  }

  return (
    <form onSubmit={handleSubmit(submit)}>
      <div>
        <input type="text" placeholder="아이디" {...register("username")} onKeyDown={handleUsernameKeyDown} />
      </div>
      <div>
        <input
          type={showPassword ? "text" : "password"}
          placeholder="패스워드"
          {...register("password")}
          onKeyDown={handleUsernameKeyDown}
        />
        <button type="button" tabIndex={-1} onClick={() => setShowPassword((prev) => !prev)}>
          {showPassword ? "숨기기" : "보이기"}
        </button>
      </div>
      {serverError && <p>{serverError}</p>}
      <button type="submit" disabled={!isValid || isSubmitting}>
        {isSubmitting ? "로그인 중..." : "로그인"}
      </button>
    </form>
  )
}

export default LoginForm
