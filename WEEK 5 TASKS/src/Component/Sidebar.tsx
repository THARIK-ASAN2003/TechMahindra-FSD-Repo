import {
    Drawer,
    DrawerBody,
    DrawerHeader,
    DrawerOverlay,
    DrawerContent,
    DrawerCloseButton,
    Button,
    VStack,
    useColorModeValue,
  } from "@chakra-ui/react";
  
  interface SidebarProps {
    isOpen: boolean;
    onClose: () => void;
    setSelectedGenre: (genre: string) => void;
  }
  
  function Sidebar({ isOpen, onClose, setSelectedGenre }: SidebarProps) {
    const genres = [
      "Action",
      "Adventure",
      "RPG",
      "Shooter",
      "Puzzle",
      "Racing",
      "Sports",
      "Strategy",
    ];
  
    // ✅ Dynamically change background & text color based on the theme mode
    const bgColor = useColorModeValue("white", "gray.800"); // Light Mode: White | Dark Mode: Gray
    const textColor = useColorModeValue("black", "white"); // Light Mode: Black | Dark Mode: White
    const hoverBgColor = useColorModeValue("gray.200", "blue.600"); // Light Mode: Light Gray | Dark Mode: Blue
  
    return (
      <Drawer isOpen={isOpen} placement="left" onClose={onClose}>
        <DrawerOverlay />
        <DrawerContent bg={bgColor} color={textColor}>
          <DrawerCloseButton />
          <DrawerHeader borderBottomWidth="1px" fontSize="xl">
            Select Genre
          </DrawerHeader>
          <DrawerBody>
            <VStack align="start" spacing={3}>
              {genres.map((genre) => (
                <Button
                  key={genre}
                  variant="ghost"
                  width="100%"
                  justifyContent="flex-start"
                  fontSize="lg"
                  color={textColor} // ✅ Text color dynamically changes
                  bg={bgColor} // ✅ Background adapts to theme
                  _hover={{ bg: hoverBgColor }} // ✅ Hover color changes based on theme
                  _active={{ bg: hoverBgColor }}
                  onClick={() => {
                    setSelectedGenre(genre);
                    onClose();
                  }}
                >
                  {genre}
                </Button>
              ))}
            </VStack>
          </DrawerBody>
        </DrawerContent>
      </Drawer>
    );
  }
  
  export default Sidebar;
  