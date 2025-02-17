import { type ApiResponseBody, http } from "../http"

type MeResponse = {
  uid: string
  username: string
  createdAt: string
  modifiedAt: string
  lastLoginedAt: string
}

export const meApi = {
  me: async (): Promise<MeResponse> => {
    const body = await http<MeResponse>("/me", { method: "GET" })
    return body
  },
}
