import { create } from "zustand"
import { authApi } from "@/shared/api"
import { type LoginFormData } from "../types"

interface AuthState {
  token: string | null
  login: (formData: LoginFormData) => Promise<void>
  logout: () => void
}

export const useAuthStore = create<AuthState>((set) => ({
  token: localStorage.getItem("token") || null,

  login: async (formData: LoginFormData) => {
    try {
      console.table(formData)
      const { accessToken } = await authApi.login(formData)
      set({ token: accessToken })
      localStorage.setItem("token", accessToken) // 로컬 스토리지에 저장
    } catch (error) {
      console.error("로그인 실패:", error)
      throw error
    }
  },

  logout: () => {
    authApi.logout()
    set({ token: null })
    localStorage.removeItem("token")
  },
}))
