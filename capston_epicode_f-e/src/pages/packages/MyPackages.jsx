import { Card, Carousel, Col, Container, Row } from "react-bootstrap";
import { Link } from "react-router-dom";

const MyPackages = function () {
  return (
    <>
      <Container fluid>
        <img
          src="https://images.unsplash.com/photo-1542036813441-fc9a620d539d?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
          alt="Mani di coppia"
          className="sfondoAlbum"
        />
        <h2 className="titolo-sopra-sfondo">PACCHETTI</h2>
      </Container>
      <Container className="pb-4">
        <Row className=" my-3">
          <Col xs={12} md={6} className="p-5 mb-4 mb-md-0">
            <h3>UN INVESTIMENTO CHE DURA TUTTA LA VITA</h3>
            <p>
              Catturare la vostra storia d'amore è importantissimo per me e non lo prendo alla leggera. Quando investite
              in foto, investite in ricordi che tramandano la vostra storia d'amore alle generazioni future. In questo
              modo, potrete ripensare al vostro giorno speciale non solo ricordando quanto fosse bello, ma anche
              provando esattamente le stesse emozioni provate in quel momento. QUESTO sì che è un investimento che
              significa tantissimo. I vostri ricordi e la vostra storia sono il mondo per me e sono così felice di poter
              contribuire a raccontarli!{" "}
            </p>
            <div>
              <Link to="/contact">
                <button className="button-info px-3 my-2">CREIAMO ARTE INSIEME</button>
              </Link>
            </div>
          </Col>
          <Col xs={12} md={6}>
            <img
              src="https://images.unsplash.com/photo-1543932927-a9def13a0e7c?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
              alt="Coppia"
              className="home-bio-image"
            />
          </Col>
        </Row>
      </Container>

      <Container fluid className="py-1 text-center dark my-3">
        <span className="px-3 h4">PRENOTA ORA 2025 - 2026</span>
        <Link to="/contact">
          <button className="button-info px-3 ">CONTATTO</button>
        </Link>
      </Container>

      <Container className="py-5">
        <Row>
          <Col xs={12} md={6} className="mb-4 mb-md-0">
            {" "}
            <Carousel controls={false} indicators={false} fade>
              <Carousel.Item>
                <img
                  src="https://images.unsplash.com/photo-1510635874686-2761923552fe?q=80&w=1976&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                  alt="Model"
                  className="carousel-image"
                />
              </Carousel.Item>
              <Carousel.Item>
                <img
                  src="https://images.unsplash.com/photo-1542514616-c0cd93ca9563?q=80&w=1976&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                  alt="Coppia"
                  className="carousel-image"
                />
              </Carousel.Item>
              <Carousel.Item>
                <img
                  src="https://images.unsplash.com/photo-1519220407669-06f9912b4aca?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                  alt="Corridoio"
                  className="carousel-image"
                />
              </Carousel.Item>
            </Carousel>
          </Col>
          <Col xs={12} md={6}>
            <h3 className="h1">
              <u>
                I pacchetti <br /> <span className="px-2">includono:</span>
              </u>
            </h3>
            <ul>
              <li>immagini modificate ad alta risoluzione </li>
              <li>foto in anteprima entro 48 ore</li>
              <li>diritti di stampa personali </li>
              <li>tutte belle foto</li>
              <li>idee di posizione </li>
              <li>ritratti creativi </li>
              <li>galleria online personalizzata </li>
              <li>cronologia personalizzata </li>
            </ul>
            <div>
              <Link to="/contact">
                <button className="button-info px-3 my-2">CONTATTAMI</button>
              </Link>
            </div>
          </Col>
        </Row>
      </Container>
      <hr />
      {/*    CARD SINISTRA - FUGHE D'AMORE   */}
      <Container className="py-5">
        <Row className="g-4 justify-content-center">
          <Col xs={12} md={4} lg={4}>
            <Card className="custom-card border-0">
              <div className="card-content">
                <Card.Title className="vertical-text">FUGHE D'AMORE</Card.Title>
                <Card.Img
                  variant="top"
                  src="https://images.unsplash.com/photo-1591969851586-adbbd4accf81?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                  className="card-image"
                />
              </div>
            </Card>
            <Link to="/contact" className="m-4">
              <button className="button-info px-2 mt-3">A PARTIRE DA $ 800</button>
            </Link>
          </Col>
          {/*    CARD CENTRALE - COPPIE   */}
          <Col xs={12} md={4} lg={4}>
            <Card className="custom-card border-0">
              <div className="card-content">
                <Card.Title className="vertical-text">COPPIE/FAMIGLIE</Card.Title>
                <Card.Img
                  variant="top"
                  src="https://images.unsplash.com/photo-1560745155-a978f7ef4d06?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                  className="card-image"
                />
              </div>
            </Card>
            <Link to="/contact" className="m-4">
              <button className="button-info px-2 mt-3">A PARTIRE DA $ 500</button>
            </Link>
          </Col>
          {/*    CARD DESTRA - MATRIMONI   */}
          <Col xs={12} md={4} lg={4}>
            <Card className="custom-card border-0">
              <div className="card-content">
                <Card.Title className="vertical-text">MATRIMONI</Card.Title>
                <Card.Img
                  variant="top"
                  src="https://plus.unsplash.com/premium_photo-1661340786790-f61c3456e40b?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                  className="card-image"
                />
              </div>
            </Card>
            <Link to="/contact" className="m-4">
              <button className="button-info px-2 mt-3">A PARTIRE DA $ 3.500</button>
            </Link>
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default MyPackages;
