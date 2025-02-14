import { type ApiResponseBody, http } from "../http"

interface LoginRequest {
  username: string
  password: string
}

interface LoginResponse {
  accessToken: string
  expiresIn: number
}

export const authApi = {
  login: async (request: LoginRequest): Promise<LoginResponse> => {
    const body = await http<ApiResponseBody<LoginResponse>>("/auth/login", {
      method: "POST",
      body: JSON.stringify(request),
      headers: {
        "Content-Type": "application/json",
      },
    })
    return body.data
  },
  logout: async () => {
    http("/auth/logout", { method: "POST" })
  },
}
