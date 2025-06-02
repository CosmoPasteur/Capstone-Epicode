import "./MyHome.css";
import { Button, Card, Col, Container, Row } from "react-bootstrap";
import Carousel from "react-bootstrap/Carousel";
import { Link } from "react-router-dom";

const MyHome = function () {
  return (
    <main>
      {/* CAROSELLO */}
      <Container fluid className="px-0 mb-5">
        <Carousel interval={3000} controls={false} indicators={false} pause="hover" fade>
          <Carousel.Item>
            <img
              src="https://images.unsplash.com/photo-1513279922550-250c2129b13a?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
              alt="L'Amore tra due persone"
              className="carousel-image"
            />
          </Carousel.Item>
          <Carousel.Item>
            <img
              src="https://images.unsplash.com/photo-1744360820043-59b729149f7d?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
              alt="4x4"
              className="carousel-image"
            />
          </Carousel.Item>
          <Carousel.Item>
            <img
              src="https://images.unsplash.com/photo-1745810187217-4d9e1ccfd9d5?q=80&w=2128&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
              alt="Grand Canyon National Park, Arizona, USA"
              className="carousel-image"
            />
          </Carousel.Item>
          <Carousel.Item>
            <img
              src="https://images.unsplash.com/photo-1743309411498-a0f4f4b96b65?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
              alt="Bellezze naturali"
              className="carousel-image"
            />
          </Carousel.Item>
        </Carousel>
      </Container>

      <hr />

      {/* ACCENNO BIO  */}
      <Container fluid className="p-5 my-5">
        <Row>
          <Col xs={12} md={6}>
            <span className="h2 ehila">Ehilà,</span>
            <div className="container-dark mb-3">
              <div className="dark p-3 ">
                <p>
                  <strong>Sono così felice che tu sia qui!</strong>
                </p>
                <p>
                  Sono Francesca, la ragazza dietro la macchina fotografica, e la mia missione è catturare la magia
                  della vostra storia d'amore attraverso il mio obiettivo. Il mio viaggio nella fotografia è iniziato
                  con la pellicola, dove ogni scatto racchiudeva un senso di magia e attesa. Col passare del tempo sono
                  passata al digitale, mantenendo viva nel mio lavoro quella nostalgica qualità cinematografica. Si
                  tratta di preservare quegli attimi senza tempo che ti trasportano indietro nel tempo, evocando
                  emozioni che risuonano per gli anni a venire ...
                </p>
              </div>
            </div>
            <Link to="/about">
              <button className="button-info m-3 px-4 mb-5">Per saperne di più</button>
            </Link>
          </Col>
          <Col md={6}>
            <div>
              <img
                src="https://images.unsplash.com/photo-1611093793031-46db535bde90?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                alt="Fotografa"
                className="home-bio-image"
              />
            </div>
          </Col>
        </Row>
      </Container>

      <hr />
      <Container fluid className="py-1 text-center dark">
        <span className="px-3 h4">PRENOTA ORA 2025 - 2026</span>
        <Link to="/contact">
          <button className="button-info px-3 ">CONTATTO</button>
        </Link>
      </Container>
      <hr />

      {/*    CARD SINISTRA - FUGHE D'AMORE   */}
      <Container className="py-5 my-2">
        <Row className="g-4 justify-content-center">
          <Col xs={12} sm={12} md={4} lg={4}>
            <Card className="custom-card border-0 shadow-sm h-100">
              <div className="card-content">
                <Card.Title className="vertical-text">FUGHE D'AMORE</Card.Title>
                <Card.Img
                  variant="top"
                  src="https://images.unsplash.com/photo-1591969851586-adbbd4accf81?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                  alt="Fotografia di fughe d'amore"
                  className="card-image"
                />
              </div>
            </Card>
          </Col>
          {/*    CARD CENTRALE - COPPIE   */}
          <Col xs={12} sm={12} md={4} lg={4}>
            <Card className="custom-card border-0 shadow-sm h-100">
              <div className="card-content">
                <Card.Title className="vertical-text">COPPIE</Card.Title>
                <Card.Img
                  variant="top"
                  src="https://images.unsplash.com/photo-1560745155-a978f7ef4d06?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                  alt="Fotografia di coppie"
                  className="card-image"
                />
              </div>
            </Card>
          </Col>
          {/*    CARD DESTRA - MATRIMONI   */}
          <Col xs={12} sm={12} md={4} lg={4}>
            <Card className="custom-card border-0 shadow-sm h-100">
              <div className="card-content">
                <Card.Title className="vertical-text">MATRIMONI</Card.Title>
                <Card.Img
                  variant="top"
                  src="https://plus.unsplash.com/premium_photo-1661340786790-f61c3456e40b?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                  alt="Fotografia di matrimoni"
                  className="card-image"
                />
              </div>
            </Card>
          </Col>
        </Row>
      </Container>
    </main>
  );
};
export default MyHome;
