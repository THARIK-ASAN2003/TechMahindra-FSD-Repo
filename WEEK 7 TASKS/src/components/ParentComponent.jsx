import { useState } from 'react'
import axios from 'axios'
import ChildComponent from './ChildComponent.jsx'
import 'bootstrap/dist/css/bootstrap.min.css'
import './ParentComponent.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faSearch } from '@fortawesome/free-solid-svg-icons'

const ParentComponent = () => {
  const [userData, setUserData] = useState(null)
  const [username, setUsername] = useState('')
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(false)

  const handleSubmit = async (e) => {
    e.preventDefault()
    if (!username.trim()) return
    
    setLoading(true)
    try {
      const response = await axios.get(`https://api.github.com/users/${username}`)
      setUserData(response.data)
      setError('')
    } catch (err) {
      setError('User not found - please try another username')
      setUserData(null)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="container main-container">
      <div className="header-card">
        <h1 className="display-5 gradient-text">GitHub Profile Explorer</h1>
        <p className="lead">Discover developer profiles and contributions</p>
      </div>

      <form onSubmit={handleSubmit} className="search-box">
        <div className="input-group stylish-input-group">
          <input
            type="text"
            className="form-control search-input"
            placeholder="Enter GitHub username..."
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            disabled={loading}
          />
          <button 
            className="btn btn-primary search-button"
            type="submit"
            disabled={loading}
          >
            {loading ? (
              <div className="spinner-border spinner-border-sm" role="status">
                <span className="visually-hidden">Loading...</span>
              </div>
            ) : (
              <>
                <FontAwesomeIcon icon={faSearch} className="me-2" />
                Search
              </>
            )}
          </button>
        </div>
      </form>

      {error && (
        <div className="error-alert animate__animated animate__headShake">
          <i className="fas fa-exclamation-triangle me-2" />
          {error}
        </div>
      )}

      {userData && <ChildComponent user={userData} />}
    </div>
  )
}

export default ParentComponent