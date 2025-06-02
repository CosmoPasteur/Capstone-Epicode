import React, { useState } from "react";
import { Container, Row, Col, Card, Button, Modal } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

import CalendarioAppuntamenti from "./CalendarioAppuntamenti";
import GestisciGallerie from "./GestisciGallerie.jsx";
import GestisciMessaggi from "./GestisciMessaggi";

const Dashboard = () => {
  const navigate = useNavigate();
  const isLoggedIn = localStorage.getItem("isLoggedIn") === "true";

  const [modalOpen, setModalOpen] = useState(false);
  const [activeSection, setActiveSection] = useState(null);
  if (!isLoggedIn) {
    navigate("/login");
    return null;
  }

  const openSection = (section) => {
    setActiveSection(section);
    setModalOpen(true);
  };

  const closeModal = () => {
    setModalOpen(false);
    setActiveSection(null);
  };

  const renderModalContent = () => {
    switch (activeSection) {
      case "calendar":
        return <CalendarioAppuntamenti onClose={closeModal} />;
      case "galleries":
        return <GestisciGallerie onClose={closeModal} />;
      case "messages":
        return <GestisciMessaggi />;
      default:
        return null;
    }
  };

  return (
    <Container className="mt-5 py-5">
      <h2 className="text-center mb-4">Pannello Amministrativo</h2>
      <Row className="justify-content-center">
        <Col md={8}>
          <Card className="shadow-sm p-4 text-center ">
            <h4>Benvenuto nell'Area Admin</h4>
            <p className="mb-4">Da qui potrai gestire contenuti, foto e richieste.</p>

            <div className="d-grid gap-3 mb-4">
              <Button variant="light button-info border" onClick={() => openSection("calendar")}>
                Visualizza Appuntamenti
              </Button>
              <Button variant="secondary" onClick={() => openSection("galleries")}>
                Gestisci Gallerie
              </Button>
              <Button variant="info" onClick={() => openSection("messages")}>
                Messaggi Ricevuti
              </Button>
            </div>

            <hr className="my-4" />
            <Button
              variant="danger"
              onClick={() => {
                localStorage.removeItem("isLoggedIn");
                navigate("/login");
              }}
            >
              Esci
            </Button>
          </Card>
        </Col>
      </Row>

      <Modal show={modalOpen} onHide={closeModal} size="lg" centered>
        <Modal.Header closeButton>
          <Modal.Title>
            {activeSection === "calendar" && "Visualizza Appuntamenti"}
            {activeSection === "galleries" && "Gestisci Gallerie"}
            {activeSection === "messages" && "Messaggi Ricevuti"}
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>{renderModalContent()}</Modal.Body>
      </Modal>
    </Container>
  );
};

export default Dashboard;
