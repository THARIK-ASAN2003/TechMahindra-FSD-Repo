import {
  Image,
  Input,
  InputGroup,
  InputLeftElement,
  Flex,
  Box,
  Button,
  Text,
} from "@chakra-ui/react";
import { SearchIcon } from "@chakra-ui/icons";
import { useDisclosure } from "@chakra-ui/react";
import logo from "../assets/KanguvaGameLogo.png";
import DarkSwitch from "./DarkSwitch";
import Sidebar from "./Sidebar";

interface NavBarProps {
  setSelectedGenre: (genre: string) => void;
  setSearchQuery: (query: string) => void;
}

function NavBar({ setSelectedGenre, setSearchQuery }: NavBarProps) {
  const { isOpen, onOpen, onClose } = useDisclosure();

  return (
    <>
      <Box bg="blue.600" py={3} px={5} boxShadow="md">
        <Flex justify="space-between" align="center" maxW="1200px" mx="auto">
          {/* Sidebar Button */}
          <Button onClick={onOpen} colorScheme="whiteAlpha" mr={3}>
            ☰ Menu
          </Button>

          {/* Logo & Title */}
          <Flex align="center">
            <Image src={logo} alt="Kanguvaa Game Logo" boxSize="50px" />
            <Text ml={3} fontSize="xl" fontWeight="bold" color="white">
              Kanguvaa Games
            </Text>
          </Flex>

          {/* Search Bar */}
          <InputGroup w="30%">
            <InputLeftElement pointerEvents="none">
              <SearchIcon color="gray.500" />
            </InputLeftElement>
            <Input
              type="text"
              placeholder="Search games..."
              bg="white"
              color="black"
              borderRadius="md"
              _placeholder={{ color: "gray.600" }}
              onChange={(e) => setSearchQuery(e.target.value.trim())} // ✅ Trim to prevent unnecessary spaces
            />
          </InputGroup>

          {/* Dark Mode Switch */}
          <DarkSwitch />
        </Flex>
      </Box>

      {/* Sidebar Component */}
      <Sidebar isOpen={isOpen} onClose={onClose} setSelectedGenre={setSelectedGenre} />
    </>
  );
}

export default NavBar;
