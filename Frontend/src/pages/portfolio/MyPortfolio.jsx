import "./MyPortfolio.css";
import { Card, Col, Container, Row } from "react-bootstrap";
import { Link } from "react-router-dom";
import galleries from "../../components/data/galleries.json";

const MyPortfolio = function () {
  return (
    <>
      <hr />
      <hr />
      <h2 className="text-center my-5 h1 fw-bold">LE STORIE CHE RACCONTO</h2>
      <hr />
      <hr />
      <Container className="my-5">
        <Row className="g-4">
          {galleries.map((g) => (
            <Col xs={12} sm={6} md={6} lg={6} key={g.slug}>
              <Card className="custom-card-portafolio border-0 ">
                <Link to={`/portfolio/${g.slug}`} style={{ color: "inherit", textDecoration: "none" }}>
                  <div className="card-content">
                    <Card.Title className="vertical-text">{g.title}</Card.Title>
                    <Card.Img variant="top" src={g.coverImage} className="card-image" />
                  </div>
                </Link>
              </Card>
            </Col>
          ))}
        </Row>
      </Container>
      <Container fluid className="py-2 text-center dark my-4">
        <span className="px-3 h4">PRENOTA ORA 2025 - 2026</span>
        <Link to="/contact">
          <button className="button-info px-3">CONTATTO</button>
        </Link>
      </Container>
    </>
  );
};

export default MyPortfolio;
