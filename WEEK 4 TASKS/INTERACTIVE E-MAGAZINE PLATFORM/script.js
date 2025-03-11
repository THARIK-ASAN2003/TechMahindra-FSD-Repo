// Sample Data with Multimedia
const articles = [
  {
    id: 1,
    title: "AI Takes Over the World",
    category: "technology",
    content: "Artificial Intelligence is revolutionizing industries...",
    image: "Images/ai.jpg",
    video: "Multimedia/ai-video.mp4",
    audio: "Multimedia/ai-podcast.mp3",
    gallery: ["Images/ai1.jpg", "Images/ai2.jpg", "Images/ai3.jpg"],
    comments: []
  },
  {
    id: 2,
    title: "World Cup Finals",
    category: "sports",
    content: "The final match was an epic showdown...",
    image: "Images/world-cup.jpg",
    video: "Multimedia/world-cup-video.mp4",
    audio: "Multimedia/world-cup-podcast.mp3",
    gallery: ["Images/world-cup1.jpg", "Images/world-cup2.jpg"],
    comments: []
  },
  {
    id: 3,
    title: "Global Summit on Climate Change",
    category: "politics",
    content: "Leaders from around the world gather to discuss solutions...",
    image: "Images/climate.jpg",
    video: "Multimedia/climate-video.mp4",
    audio: "Multimedia/climate-podcast.mp3",
    gallery: ["Images/climate1.jpg", "Images/climate2.jpg"],
    comments: []
  },
  {
    id: 4,
    title: "New Movie Releases",
    category: "entertainment",
    content: "Check out the latest blockbusters hitting theaters...",
    image: "Images/movies.jpg",
    video: "Multimedia/movie-trailer.mp4",
    audio: "Multimedia/movie-podcast.mp3",
    gallery: ["Images/movie1.jpg", "Images/movie2.jpg"],
    comments: []
  }
];

// Display Articles Dynamically
function displayArticles(filteredArticles) {
  const container = document.getElementById('articles-container');
  container.innerHTML = ''; // Clear existing content

  filteredArticles.forEach(article => {
    const card = document.createElement('div');
    card.classList.add('news-card');
    card.dataset.category = article.category;

    // Build Gallery HTML
    const galleryHTML = article.gallery
      ? `
        <div class="gallery">
          ${article.gallery.map(img => `<img src="${img}" alt="Gallery Image">`).join('')}
        </div>
      `
      : '';

    card.innerHTML = `
      <img src="${article.image}" alt="${article.title}">
      <h4>${article.title}</h4>
      <p>${article.content.slice(0, 100)}...</p>
      <video controls width="100%">
        <source src="${article.video}" type="video/mp4">
        Your browser does not support the video tag.
      </video>
      <audio controls>
        <source src="${article.audio}" type="audio/mp3">
        Your browser does not support the audio element.
      </audio>
      ${galleryHTML}
      <div class="social-sharing">
        <a href="https://twitter.com/share?url=YOUR_URL&text=${encodeURIComponent(article.title)}" target="_blank">
          <i class="fab fa-twitter"></i>Twitter
        </a>
        <a href="https://www.facebook.com/sharer/sharer.php?u=YOUR_URL" target="_blank">
          <i class="fab fa-facebook"></i>Facebook
        </a>
        <a href="https://www.linkedin.com/shareArticle?url=YOUR_URL&title=${encodeURIComponent(article.title)}" target="_blank">
          <i class="fab fa-linkedin"></i>LinkedIn
        </a>
        <a href="https://pinterest.com/pin/create/button/?url=YOUR_URL&description=${encodeURIComponent(article.title)}" target="_blank">
          <i class="fab fa-pinterest"></i>Pinterest
        </a>
      </div>
      <a href="#" onclick="openModal(${article.id})" class="read-more">Read More</a>
    `;

    container.appendChild(card);
  });
}

// Open Article Modal
function openModal(id) {
  const modal = document.getElementById('article-modal');
  const article = articles.find(a => a.id === id);

  if (article) {
    modal.dataset.articleId = article.id; // Store article ID for comment submission
    document.getElementById('modal-title').textContent = article.title;
    document.getElementById('modal-image').src = article.image;
    document.getElementById('modal-content').textContent = article.content;
    document.getElementById('modal-video').querySelector('source').src = article.video;
    document.getElementById('modal-video').load();
    document.getElementById('modal-audio').querySelector('source').src = article.audio;
    document.getElementById('modal-audio').load();

    const galleryContainer = document.getElementById('modal-gallery');
    galleryContainer.innerHTML = '';
    if (article.gallery) {
      article.gallery.forEach(img => {
        const imgElement = document.createElement('img');
        imgElement.src = img;
        imgElement.alt = 'Gallery Image';
        galleryContainer.appendChild(imgElement);
      });
    }

    // Update Social Sharing Links
    document.getElementById('share-twitter').href = `https://twitter.com/share?url=YOUR_URL&text=${encodeURIComponent(article.title)}`;
    document.getElementById('share-facebook').href = `https://www.facebook.com/sharer/sharer.php?u=YOUR_URL`;
    document.getElementById('share-linkedin').href = `https://www.linkedin.com/shareArticle?url=YOUR_URL&title=${encodeURIComponent(article.title)}`;
    document.getElementById('share-pinterest').href = `https://pinterest.com/pin/create/button/?url=YOUR_URL&description=${encodeURIComponent(article.title)}`;

    // Load Comments
    loadComments(article);

    modal.style.display = 'block';
  }
}

// Load Comments
function loadComments(article) {
  const commentsList = document.getElementById('comments-list');
  commentsList.innerHTML = ''; // Clear existing comments

  article.comments.forEach(comment => {
    const commentDiv = document.createElement('div');
    commentDiv.classList.add('comment');
    commentDiv.innerHTML = `
      <p>${comment.text}</p>
      <small>Posted by ${comment.author} on ${comment.date}</small>
    `;
    commentsList.appendChild(commentDiv);
  });
}

// Add Comment
document.getElementById('comment-form')?.addEventListener('submit', (event) => {
  event.preventDefault();

  const modal = document.getElementById('article-modal');
  const articleId = parseInt(modal.dataset.articleId);
  const article = articles.find(a => a.id === articleId);

  if (article) {
    const commentText = document.getElementById('comment-text').value;
    const authorName = document.getElementById('comment-author').value || 'Anonymous';

    if (commentText.trim()) {
      const newComment = {
        text: commentText,
        author: authorName,
        date: new Date().toLocaleDateString()
      };

      article.comments.push(newComment);
      loadComments(article); // Reload comments
      document.getElementById('comment-form').reset(); // Reset form
    }
  }
});

// Close Article Modal
document.querySelector('.close').addEventListener('click', () => {
  const modal = document.getElementById('article-modal');
  modal.style.display = 'none';
});

window.addEventListener('click', (event) => {
  const modal = document.getElementById('article-modal');
  if (event.target === modal) {
    modal.style.display = 'none';
  }
});

// Filter Articles by Category
document.querySelectorAll('.tab').forEach(tab => {
  tab.addEventListener('click', () => {
    document.querySelector('.active-tab')?.classList.remove('active-tab');
    tab.classList.add('active-tab');

    const category = tab.dataset.category || 'all';
    filterArticles(category);
  });
});

function filterArticles(category) {
  const filtered = category === 'all'
    ? articles
    : articles.filter(article => article.category === category);

  displayArticles(filtered);
}

// Search Articles
const searchInput = document.getElementById('search-input');
if (searchInput) {
  searchInput.addEventListener('input', () => {
    const query = searchInput.value.toLowerCase();
    const filtered = articles.filter(article => {
      const title = article.title.toLowerCase();
      const content = article.content.toLowerCase();
      return title.includes(query) || content.includes(query);
    });

    displayArticles(filtered);
  });
}

// Initialize
displayArticles(articles);

// Hamburger Menu Functionality
const menuIcon = document.querySelector('.menu-icon');
const navLinks = document.querySelector('.nav-links');

menuIcon.addEventListener('click', () => {
  navLinks.classList.toggle('active');
});

// Slider Functionality
const slides = document.querySelectorAll('.slide');
const prevButton = document.querySelector('.prev');
const nextButton = document.querySelector('.next');

let currentSlide = 0;

function showSlide(index) {
  slides.forEach((slide, i) => {
    slide.classList.remove('active');
    if (i === index) slide.classList.add('active');
  });
}

function nextSlide() {
  currentSlide = (currentSlide + 1) % slides.length;
  showSlide(currentSlide);
}

function prevSlide() {
  currentSlide = (currentSlide - 1 + slides.length) % slides.length;
  showSlide(currentSlide);
}

prevButton.addEventListener('click', prevSlide);
nextButton.addEventListener('click', nextSlide);

setInterval(nextSlide, 5000);
