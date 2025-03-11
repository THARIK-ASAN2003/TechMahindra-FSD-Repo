import { HStack, Switch, useColorMode, Text } from "@chakra-ui/react";

function DarkSwitch() {
  const { colorMode, toggleColorMode } = useColorMode();

  return (
    <HStack>
      <Text fontSize="sm" color="white">
        {colorMode === "light" ? "Light Mode" : "Dark Mode"}
      </Text>
      <Switch colorScheme="red" isChecked={colorMode === "dark"} onChange={toggleColorMode} />
    </HStack>
  );
}

export default DarkSwitch;
