import "./ContactForm.css";
import { useState } from "react";
import { Button, Col, Container, Form, Row } from "react-bootstrap";

const ContactForm = function () {
  const [formData, setFormData] = useState({
    nome: "",
    cognome: "",
    email: "",
    telefono: "",
    dataEvento: "",
    tipologia: "",
    comeConosciuto: "",
  });

  const [submitted, setSubmitted] = useState(false);
  const [showSuccess, setShowSuccess] = useState(false);

  const handleChange = (e) => {
    const { id, value } = e.target;
    setFormData({ ...formData, [id]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/api/contact", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        console.log("Richiesta inviata con successo");
        setSubmitted(true);
        setShowSuccess(true);
        setFormData({
          nome: "",
          cognome: "",
          email: "",
          telefono: "",
          dataEvento: "",
          tipologia: "",
          messaggio: "",
        });

        setTimeout(() => {
          setShowSuccess(false);
        }, 5000);
      } else {
        const errorData = await response.json();
        console.error("Errore nella richiesta:", errorData);
        alert("Errore durante l'invio. Controlla i dati inseriti.");
      }
    } catch (error) {
      console.error("Errore di rete:", error);
      alert("Errore di connessione al server.");
    }
  };

  return (
    <>
      <Container fluid className="boxContact py-5 my-3">
        <Row className="justify-content-center px-md-5 mx-md-5">
          <Col xs={12} md={6} className="d-flex align-items-center justify-content-center mb-4 mb-md-0">
            <img
              src="https://images.unsplash.com/photo-1591969851586-adbbd4accf81?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
              alt="Coppia"
              className="imgContact shadow-sm"
            />
          </Col>

          <Col xs={12} md={6}>
            <h2 className="textFormContact p-4 shadow-sm mb-4">Richiesta servizio fotografico</h2>
            <Form onSubmit={handleSubmit}>
              <Row className="mb-3">
                <Form.Group controlId="nome" className="mb-3">
                  <Form.Label className="textFormContact">Nome</Form.Label>
                  <Form.Control
                    type="text"
                    placeholder="Inserisci il tuo nome"
                    value={formData.nome}
                    onChange={handleChange}
                    required
                  />
                </Form.Group>
                <Form.Group controlId="cognome" className="mb-3">
                  <Form.Label className="textFormContact">Cognome</Form.Label>
                  <Form.Control
                    type="text"
                    placeholder="Inserisci il tuo cognome"
                    value={formData.cognome}
                    onChange={handleChange}
                    required
                  />
                </Form.Group>
                <Form.Group controlId="email" className="mb-3">
                  <Form.Label className="textFormContact">Email</Form.Label>
                  <Form.Control
                    type="email"
                    placeholder="nome@email.com"
                    value={formData.email}
                    onChange={handleChange}
                    required
                  />
                </Form.Group>
                <Form.Group controlId="telefono" className="mb-3">
                  <Form.Label className="textFormContact">Numero di Telefono</Form.Label>
                  <Form.Control
                    type="tel"
                    placeholder="+39 123 456 7890"
                    value={formData.telefono}
                    onChange={handleChange}
                    required
                  />
                </Form.Group>
                <Form.Group controlId="dataEvento" className="mb-3">
                  <Form.Label className="textFormContact">Data dell'Evento</Form.Label>
                  <Form.Control type="date" value={formData.dataEvento} onChange={handleChange} required />
                </Form.Group>
                <Form.Group controlId="tipologia" className="mb-3">
                  <Form.Label className="textFormContact">Tipologia di Evento/Pacchetto</Form.Label>
                  <Form.Select name="tipologia" value={formData.tipologia} onChange={handleChange} required>
                    <option value="">Seleziona una tipologia</option>
                    <option value="MATRIMONIO">Matrimonio</option>
                    <option value="COMPLEANNO">Compleanno</option>
                    <option value="COPPIA">Coppia</option>
                    <option value="FAMIGLIA">Famiglia</option>
                    <option value="ALTRO">Altro</option>
                  </Form.Select>
                </Form.Group>
                <Form.Group controlId="messaggio" className="mb-4">
                  <Form.Label className="textFormContact">Dimmi di più qui sotto!</Form.Label>
                  <Form.Control
                    as="textarea"
                    rows={3}
                    placeholder="Scrivi qui..."
                    value={formData.messaggio}
                    onChange={handleChange}
                    required
                  />
                </Form.Group>
                <Button variant="light" type="submit" className="button-info btnContact">
                  Invia
                </Button>
              </Row>
            </Form>
            {submitted && showSuccess && <p className="text-success mt-3">Messaggio inviato con successo!</p>}
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default ContactForm;
