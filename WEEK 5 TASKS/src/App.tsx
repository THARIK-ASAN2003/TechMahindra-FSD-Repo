import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { useEffect, useState } from "react";
import {
  Grid,
  GridItem,
  Text,
  Box,
  Spinner,
  Center,
  Button,
} from "@chakra-ui/react";
import NavBar from "./Component/NavBar";
import GameCard from "./GameCard";
import Sidebar from "./Component/Sidebar";
import Footer from "./Component/Footer";
import GameDetails from "./Component/GameDetails";

const API_KEY = "706ba3379c0f4e52a2edd66931a84657";
const API_URL = `https://api.rawg.io/api/games?key=${API_KEY}`;

function App() {
  interface Game {
    id: number;
    name: string;
    genres: { name: string }[];
    background_image: string;
    rating: number;
  }

  const [games, setGames] = useState<Game[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [selectedGenre, setSelectedGenre] = useState("");
  const [searchQuery, setSearchQuery] = useState("");
  const [page, setPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const gamesPerPage = 20; // Limit games per page

  useEffect(() => {
    setLoading(true);
    const genreFilter = selectedGenre
      ? `&genres=${selectedGenre.toLowerCase()}`
      : "";
    fetch(`${API_URL}${genreFilter}&page=${page}&page_size=${gamesPerPage}`)
      .then((response) => response.json())
      .then((data) => {
        setGames(data.results);
        setTotalPages(Math.ceil(data.count / gamesPerPage)); // Set total pages dynamically
        setLoading(false);
      })
      .catch(() => {
        setError("Failed to fetch games.");
        setLoading(false);
      });
  }, [selectedGenre, page]);

  // **Search Filter Implementation**
  const filteredGames = games.filter((game) =>
    game.name.toLowerCase().includes(searchQuery.toLowerCase())
  );

  return (
    <Router>
      <Box p={5}>
        <NavBar
          setSelectedGenre={setSelectedGenre}
          setSearchQuery={setSearchQuery}
        />
        <Grid templateAreas={`"nav nav" "main main"`} gap={4}>
          <GridItem area="nav">
            <Sidebar setSelectedGenre={setSelectedGenre} />
          </GridItem>

          <GridItem area="main">
            <Routes>
              <Route
                path="/"
                element={
                  <>
                    <Text fontSize="2xl" fontWeight="bold" mb={4}>
                      {selectedGenre
                        ? `${selectedGenre} Games`
                        : "Popular Games"}
                    </Text>

                    {loading && (
                      <Center>
                        <Spinner size="xl" color="blue.500" />
                      </Center>
                    )}

                    {error && (
                      <Text color="red.500" fontWeight="bold">
                        {error}
                      </Text>
                    )}

                    <Grid
                      templateColumns="repeat(auto-fit, minmax(250px, 1fr))"
                      gap={6}
                    >
                      {!loading &&
                        !error &&
                        filteredGames.map((game) => (
                          <GameCard key={game.id} game={game} />
                        ))}
                    </Grid>

                    {/* Pagination Controls */}
                    <Center mt={4}>
                      <Button
                        onClick={() => setPage((prev) => Math.max(prev - 1, 1))}
                        isDisabled={page === 1}
                        colorScheme="blue"
                        mr={2}
                      >
                        Previous
                      </Button>
                      <Text mx={2} fontWeight="bold">
                        Page {page} of {totalPages}{" "}
                        {/* ✅ Display total pages dynamically */}
                      </Text>
                      <Button
                        onClick={() =>
                          setPage((prev) =>
                            prev < totalPages ? prev + 1 : prev
                          )
                        }
                        isDisabled={page >= totalPages}
                        colorScheme="blue"
                      >
                        Next
                      </Button>
                    </Center>
                  </>
                }
              />
              <Route path="/game/:id" element={<GameDetails />} />
            </Routes>
          </GridItem>
        </Grid>
        <Footer />
      </Box>
    </Router>
  );
}

export default App;
