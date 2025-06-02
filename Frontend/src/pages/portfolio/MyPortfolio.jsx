import "./MyPortfolio.css";
import React, { useEffect, useState } from "react";
import { Card, Col, Container, Row } from "react-bootstrap";
import { Link } from "react-router-dom";

const MyPortfolio = () => {
  const [galleries, setGalleries] = useState([]);

  useEffect(() => {
    const fetchGalleries = async () => {
      try {
        const res = await fetch("/api/galleries");
        if (!res.ok) throw new Error("Errore nel recupero delle gallerie");

        const data = await res.json();
        if (!data.success) throw new Error(data.message || "Errore");

        setGalleries(data.data);
      } catch (err) {
        console.error(err);
        alert("Errore caricamento gallerie: " + err.message);
      }
    };

    fetchGalleries();
  }, []);

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
                    <Card.Img variant="top" src={`http://localhost:8080${g.coverImage}`} className="card-image" />
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
