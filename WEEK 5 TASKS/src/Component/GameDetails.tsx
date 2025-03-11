import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Box, Image, Text, Spinner, Center, useColorMode } from "@chakra-ui/react";

const API_KEY = "706ba3379c0f4e52a2edd66931a84657";
const API_URL = `https://api.rawg.io/api/games`;

function GameDetails() {
  const { id } = useParams<{ id: string }>();
  const [game, setGame] = useState<any>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const { colorMode } = useColorMode(); // Dark/Light mode support

  useEffect(() => {
    fetch(`${API_URL}/${id}?key=${API_KEY}`)
      .then((response) => response.json())
      .then((data) => {
        setGame(data);
        setLoading(false);
      })
      .catch(() => {
        setError("Failed to fetch game details.");
        setLoading(false);
      });
  }, [id]);

  if (loading)
    return (
      <Center>
        <Spinner size="xl" color="blue.500" />
      </Center>
    );

  if (error)
    return (
      <Text color="red.500" fontWeight="bold">
        {error}
      </Text>
    );

  return (
    <Box
      p={5}
      borderRadius="md"
      boxShadow="md"
      bg={colorMode === "light" ? "gray.100" : "gray.900"} // ✅ Fix background
      color={colorMode === "light" ? "black" : "white"} // ✅ Fix text visibility
    >
      <Image src={game.background_image} alt={game.name} borderRadius="md" />
      <Text fontWeight="bold" fontSize="2xl" mt={3}>
        {game.name}
      </Text>
      <Text mt={2}>{game.description_raw}</Text>
    </Box>
  );
}

export default GameDetails;
