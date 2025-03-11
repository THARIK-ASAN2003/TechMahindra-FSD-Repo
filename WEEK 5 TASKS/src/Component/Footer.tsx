import { Box, Text, Flex, IconButton } from "@chakra-ui/react";
import { FaFacebook, FaTwitter, FaInstagram, FaShareAlt } from "react-icons/fa";

function Footer() {
  return (
    <Box bg="blue.600" color="white" py={5} textAlign="center" mt={10}>
      <Text fontSize="lg" fontWeight="bold">
        Kanguvaa Games © 2025
      </Text>
      <Text fontSize="sm">Your one-stop shop for the best games!</Text>

      {/* Social Media Icons */}
      <Flex justify="center" mt={3}>
        <IconButton aria-label="Facebook" icon={<FaFacebook />} mx={2} />
        <IconButton aria-label="Twitter" icon={<FaTwitter />} mx={2} />
        <IconButton aria-label="Instagram" icon={<FaInstagram />} mx={2} />
        <IconButton aria-label="Share" icon={<FaShareAlt />} mx={2} />
      </Flex>
    </Box>
  );
}

export default Footer;
