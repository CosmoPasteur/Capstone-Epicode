import React from "react";
import { Container, Row, Col, Card, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const Dashboard = () => {
  const navigate = useNavigate();

  // Verifica se l'utente è loggato (mock: controlla localStorage)
  const isLoggedIn = localStorage.getItem("isLoggedIn") === "true";

  if (!isLoggedIn) {
    navigate("/login"); // Reindirizza al login se non autenticato
    return null;
  }

  return (
    <Container className="mt-5">
      <h2 className="text-center mb-4">Pannello Amministrativo</h2>
      <Row className="justify-content-center">
        <Col md={8}>
          <Card className="shadow-sm p-4 text-center">
            <h4>Benvenuto nell'Area Admin</h4>
            <p className="mb-4">Da qui potrai gestire contenuti, foto e richieste.</p>

            <div className="d-grid gap-3">
              <Button variant="primary" disabled>
                Carica Nuove Immagini
              </Button>
              <Button variant="secondary" disabled>
                Gestisci Gallerie
              </Button>
              <Button variant="info" disabled>
                Messaggi Ricevuti
              </Button>
              <hr />
              <Button
                variant="danger"
                onClick={() => {
                  localStorage.removeItem("isLoggedIn");
                  navigate("/login");
                }}
              >
                Esci
              </Button>
            </div>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default Dashboard;
