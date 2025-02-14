import { useEffect } from "react"
import { useNavigate } from "react-router"
import { useAuthStore } from "@/features/auth"
import { useMeStore } from "@/entities/account"

const MePage = () => {
  const { token, logout } = useAuthStore()
  const { me, loading, fetchMe } = useMeStore()
  const navigate = useNavigate()

  useEffect(() => {
    if (!token) {
      navigate("/auth/login")
      return
    }
    fetchMe()
  }, [token, navigate])

  if (loading) {
    return <div>로딩 중...</div>
  }

  return (
    <div>
      <h2>내 정보</h2>
      {me ? (
        <div>
          <p>
            <strong>아이디:</strong> {me.username}
          </p>
          <p>
            <strong>UID:</strong> {me.uid}
          </p>
          <p>
            <strong>생성일:</strong> {me.createdAt.toLocaleString("ko-KR")}
          </p>
          <p>
            <strong>수정일:</strong> {me.modifiedAt.toLocaleString("ko-KR")}
          </p>
          <p>
            <strong>로그인:</strong> {me.lastLoginedAt.toLocaleString("ko-KR")}
          </p>
          <button onClick={logout}>로그아웃</button>
        </div>
      ) : (
        <p>사용자 정보가 없습니다.</p>
      )}
    </div>
  )
}
export default MePage
