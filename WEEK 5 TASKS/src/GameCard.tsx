import { Box, Image, Text, Badge, Button, useColorMode } from "@chakra-ui/react";
import { Link } from "react-router-dom";

interface GameCardProps {
  game: {
    id: number;
    name: string;
    genres: { name: string }[];
    background_image: string;
    rating: number;
  };
}

function GameCard({ game }: GameCardProps) {
  const { colorMode } = useColorMode(); // Check theme mode

  return (
    <Box
      bg={colorMode === "light" ? "gray.100" : "gray.900"} // Light mode fix
      p={4}
      borderRadius="md"
      boxShadow="md"
    >
      <Image src={game.background_image} alt={game.name} borderRadius="md" />
      <Text
        fontWeight="bold"
        mt={2}
        color={colorMode === "light" ? "gray.800" : "white"} // Ensuring visibility in both modes
      >
        {game.name}
      </Text>
      <Badge colorScheme="yellow" fontSize="sm" mt={1}>{game.rating} ⭐</Badge>
      <Text
        fontSize="sm"
        mt={1}
        color={colorMode === "light" ? "gray.600" : "gray.400"} // Light mode fix
      >
        {game.genres.map((genre) => genre.name).join(", ")}
      </Text>
      <Link to={`/game/${game.id}`}>
        <Button colorScheme="blue" size="sm" mt={2}>More Details</Button>
      </Link>
    </Box>
  );
}

export default GameCard;
