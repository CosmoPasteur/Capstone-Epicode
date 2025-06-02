import React, { useState, useEffect } from "react";
import { Card, Button, Spinner, Alert } from "react-bootstrap";

const GestisciMessaggi = () => {
  const [messages, setMessages] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [deletingId, setDeletingId] = useState(null);
  const [showSuccess, setShowSuccess] = useState(false);

  useEffect(() => {
    const fetchMessages = async () => {
      try {
        const res = await fetch("/api/contact");
        if (!res.ok) throw new Error("Errore nel recupero dei messaggi");
        const data = await res.json();
        setMessages(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchMessages();
  }, []);

  const handleDelete = async (id) => {
    if (!window.confirm("Sei sicuro di voler eliminare questo messaggio?")) return;

    setDeletingId(id);

    try {
      const res = await fetch(`/api/contact/${id}`, {
        method: "DELETE",
      });

      if (!res.ok) throw new Error("Errore durante l'eliminazione del messaggio");

      // Rimuovi il messaggio cancellato dallo stato locale
      setMessages(messages.filter((msg) => msg.id !== id));

      // Mostra la notifica di successo
      setShowSuccess(true);

      // Nascondila dopo 3 secondi
      setTimeout(() => {
        setShowSuccess(false);
      }, 3000);
    } catch (err) {
      setError(err.message);
    } finally {
      setDeletingId(null);
    }
  };

  return (
    <>
      {/* Notifica di successo */}
      <div
        style={{
          position: "fixed",
          top: "20px",
          right: "20px",
          zIndex: 9999,
          transition: "opacity 0.5s ease-out",
          opacity: showSuccess ? 1 : 0,
          pointerEvents: showSuccess ? "auto" : "none",
        }}
      >
        <Alert variant="success" onClose={() => setShowSuccess(false)} dismissible>
          ✅ Messaggio eliminato con successo
        </Alert>
      </div>

      {/* Lista messaggi */}
      <div style={{ maxHeight: "400px", overflowY: "auto" }}>
        {loading && (
          <div className="text-center py-3">
            <Spinner animation="border" size="sm" /> Caricamento messaggi...
          </div>
        )}
        {error && <div className="text-danger text-center py-3">Errore: {error}</div>}
        {!loading && messages.length === 0 && <div className="text-center py-3">Nessun messaggio ricevuto.</div>}

        {!loading &&
          messages.length > 0 &&
          messages.map((msg) => (
            <Card key={msg.id} className="mb-3 position-relative shadow-sm">
              <Card.Body>
                <Card.Title>
                  {msg.nome} {msg.cognome}
                </Card.Title>
                <Card.Subtitle className="mb-2 text-muted">
                  {msg.email} | {msg.telefono}
                </Card.Subtitle>
                <Card.Text as="div">
                  <strong>Cliente/Utente:</strong> {msg.cognome} {msg.nome} <br />
                  <strong>Tipologia di Evento:</strong> {msg.tipologia} <br />
                  <strong>Data evento:</strong> {msg.dataEvento} <br />
                  <strong>Messaggio:</strong> {msg.messaggio} <br />
                  <strong>Ricevuto il:</strong>{" "}
                  {msg.receivedAt ? new Date(msg.receivedAt).toLocaleString() : "Data non disponibile"}
                </Card.Text>
              </Card.Body>

              {/* Pulsante Elimina in basso a destra */}
              <Button
                variant="danger"
                size="sm"
                className="position-absolute bottom-0 end-0 m-2 d-flex align-items-center gap-1"
                onClick={() => handleDelete(msg.id)}
                disabled={deletingId === msg.id}
              >
                {deletingId === msg.id ? (
                  <>
                    <Spinner as="span" animation="border" size="sm" /> Eliminando...
                  </>
                ) : (
                  <> Elimina</>
                )}
              </Button>
            </Card>
          ))}
      </div>
    </>
  );
};

export default GestisciMessaggi;
