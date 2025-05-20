import "./TopBar.css";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { Link } from "react-router-dom";

const TopBar = function () {
  return (
    <>
      <Navbar expand="lg" className="center-navbar mt-5">
        <Container>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="mx-auto">
              <Nav.Link as={Link} to="/">
                Home
              </Nav.Link>
              <Nav.Link as={Link} to="/blog">
                Blog
              </Nav.Link>
              <Nav.Link as={Link} to="/about">
                About
              </Nav.Link>
              <Navbar.Brand as={Link} to="/" className="mx-5">
                Bastone Studio
              </Navbar.Brand>
              <Nav.Link as={Link} to="/packages">
                Packages
              </Nav.Link>
              <Nav.Link as={Link} to="/portfolio">
                Portfolio
              </Nav.Link>
              <Nav.Link as={Link} to="/contact">
                Contact
              </Nav.Link>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>

      <div className="text-center mb-5">
        <Link to="/login" className="fw-bold">
          <img src="../public/bs-logo.svg" alt="Logo Bastone Studio" className="logo" />
        </Link>
      </div>
    </>
  );
};

export default TopBar;
