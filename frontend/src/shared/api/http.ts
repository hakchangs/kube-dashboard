export const http = async <T>(url: string, options?: RequestInit): Promise<T> => {
  const response = await fetch(`/api${url}`, {
    ...options,
    headers: {
      ...options?.headers,
    },
    credentials: "include", // 쿠키 기반 인증을 사용할 경우 필요
  })

  if (!response.ok) {
    const errorBody: ApiResponseBody<any> = await response.json()
    if (!errorBody || typeof errorBody.code !== "string") {
      throw new Error(`서버에러가 발생했습니다. ${response.status}`)
    }
    throw { status: response.status, body: errorBody } as ApiResponseError
  }

  return response.json()
}

export interface ApiResponseBody<T> {
  code: string
  message: string
  data: T
}

export interface ApiResponseError {
  status: number
  body: ApiResponseBody<any>
}

export const isApiResponseError = (error: any): error is ApiResponseError => {
  return error && typeof error.status === "number" && error.body && typeof error.body.code === "string"
}
