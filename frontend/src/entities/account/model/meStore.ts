import { create } from "zustand"
import { meApi } from "@/shared/api"
import { Me } from "../types"

interface MeState {
  me: Me | null
  loading: boolean
  fetchMe: () => Promise<void>
  reset: () => void
}

export const useMeStore = create<MeState>((set) => ({
  me: null,
  loading: true,
  fetchMe: async () => {
    set({ loading: true })
    try {
      const meResponse = await meApi.me()
      const me: Me = {
        uid: meResponse.uid,
        username: meResponse.username,
        createdAt: new Date(meResponse.createdAt),
        modifiedAt: new Date(meResponse.modifiedAt),
        lastLoginedAt: new Date(meResponse.lastLoginedAt),
      }
      set({ me, loading: false })
    } catch (error) {
      console.warn("내정보 호출실패: ", error)
      set({ loading: false, me: null })
    }
  },
  reset: () => set({ me: null, loading: true }),
}))
