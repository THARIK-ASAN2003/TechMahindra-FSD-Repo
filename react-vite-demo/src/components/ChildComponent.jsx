import { Card, ListGroup } from 'react-bootstrap'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { 
  faUsers,
  faUserFriends,
  faCodeBranch,
  faExternalLinkAlt
} from '@fortawesome/free-solid-svg-icons'
import './ChildComponent.css'

const ChildComponent = ({ user }) => {
  return (
    <Card className="user-card animate__animated animate__fadeInUp">
      <div className="card-header-bg" />
      <Card.Img 
        variant="top" 
        src={user.avatar_url} 
        className="user-avatar"
        alt={`${user.login}'s avatar`}
      />
      <Card.Body className="text-center">
        <Card.Title className="mb-1 fw-bold user-name">
          {user.name || user.login}
        </Card.Title>
        <small className="text-muted user-handle">@{user.login}</small>
        <Card.Text className="mt-3 user-bio">
          {user.bio || 'No bio available'}
        </Card.Text>
        
        <div className="stats-container">
          <div className="stat-item">
            <FontAwesomeIcon icon={faUsers} className="stat-icon followers" />
            <div>
              <div className="stat-value">{user.followers}</div>
              <div className="stat-label">Followers</div>
            </div>
          </div>
          
          <div className="stat-item">
            <FontAwesomeIcon icon={faUserFriends} className="stat-icon following" />
            <div>
              <div className="stat-value">{user.following}</div>
              <div className="stat-label">Following</div>
            </div>
          </div>
          
          <div className="stat-item">
            <FontAwesomeIcon icon={faCodeBranch} className="stat-icon repos" />
            <div>
              <div className="stat-value">{user.public_repos}</div>
              <div className="stat-label">Repos</div>
            </div>
          </div>
        </div>

        <a
          href={user.html_url}
          className="btn btn-primary profile-link"
          target="_blank"
          rel="noopener noreferrer"
        >
          <FontAwesomeIcon icon={faExternalLinkAlt} className="me-2" />
          View Full Profile
        </a>
      </Card.Body>
    </Card>
  )
}

export default ChildComponent