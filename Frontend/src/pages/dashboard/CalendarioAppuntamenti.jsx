import React, { useState, useEffect } from "react";
import { Button, Form, Col, Row, Card } from "react-bootstrap";
import { Calendar, momentLocalizer } from "react-big-calendar";
import moment from "moment";
import "react-big-calendar/lib/css/react-big-calendar.css";
import axios from "axios";

const localizer = momentLocalizer(moment);

const CalendarioAppuntamenti = ({ onClose }) => {
  const [eventi, setEventi] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [currentEvent, setCurrentEvent] = useState(null);

  const defaultEvent = {
    title: "",
    start: new Date(),
    end: new Date(),
    desc: "",
  };

  const [formData, setFormData] = useState(defaultEvent);

  // Carica gli appuntamenti dal backend all'avvio
  useEffect(() => {
    fetchAppuntamenti();
  }, []);

  const fetchAppuntamenti = async () => {
    try {
      const res = await axios.get("http://localhost:8080/api/appuntamenti");
      const mappedEvents = res.data.map((app) => ({
        id: app.id,
        title: app.titolo,
        start: new Date(app.data),
        end: new Date(app.data),
        desc: app.descrizione || "",
      }));
      setEventi(mappedEvents);
    } catch (err) {
      console.error("Errore nel recupero degli appuntamenti", err);
    }
  };

  // Apri form per nuovo evento o modifica
  const openForm = (event) => {
    if (event) {
      setCurrentEvent(event);
      setFormData({ ...event });
    } else {
      setCurrentEvent(null);
      setFormData({ ...defaultEvent });
    }
    setShowForm(true);
  };

  // Cambio nei campi input
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  // Cambio data/ora
  const handleDateChange = (field, date) => {
    const updatedDate = new Date(date);
    setFormData((prev) => ({
      ...prev,
      [field]: updatedDate,
    }));
  };

  // Invia dati al backend
  const handleSubmit = async (e) => {
    e.preventDefault();

    const appuntamentoDaInviare = {
      titolo: formData.title,
      data: formData.start.toISOString(),
      descrizione: formData.desc,
    };

    try {
      if (currentEvent && currentEvent.id) {
        // Modifica
        await axios.put(`http://localhost:8080/api/appuntamenti/${currentEvent.id}`, appuntamentoDaInviare);
      } else {
        // Creazione
        await axios.post("http://localhost:8080/api/appuntamenti", appuntamentoDaInviare);
      }

      fetchAppuntamenti();
      resetForm();
    } catch (err) {
      console.error("Errore nell'invio dell'appuntamento", err);
    }
  };

  // Elimina appuntamento
  const handleDelete = async () => {
    if (!currentEvent || !currentEvent.id) return;

    if (window.confirm("Sei sicuro di voler eliminare questo appuntamento?")) {
      try {
        await axios.delete(`http://localhost:8080/api/appuntamenti/${currentEvent.id}`);
        fetchAppuntamenti();
        resetForm();
      } catch (err) {
        console.error("Errore nell'eliminazione dell'appuntamento", err);
      }
    }
  };

  // Resetta il form
  const resetForm = () => {
    setShowForm(false);
    setFormData({ ...defaultEvent });
    setCurrentEvent(null);
  };

  return (
    <div>
      {!showForm ? (
        <>
          <h5 className="bastoneStudio h2">Bastone Studio</h5>
          <Button variant="success" onClick={() => openForm()}>
            Nuovo Appuntamento
          </Button>

          <div style={{ height: "400px", marginTop: "20px" }}>
            <Calendar
              localizer={localizer}
              events={eventi}
              startAccessor="start"
              endAccessor="end"
              views={["month", "week", "day"]}
              defaultView="month"
              selectable
              onSelectEvent={(evento) => openForm(evento)}
              onSelectSlot={(slotInfo) => {
                openForm({
                  title: "",
                  start: slotInfo.start,
                  end: slotInfo.end,
                  desc: "",
                  id: null,
                });
              }}
            />
          </div>
          <button className="btn btn-secondary mt-3" onClick={onClose}>
            Chiudi
          </button>
        </>
      ) : (
        <Form onSubmit={handleSubmit}>
          <h5>{currentEvent ? "Modifica Appuntamento" : "Nuovo Appuntamento"}</h5>

          {/* Titolo */}
          <Form.Group className="mb-3" controlId="formTitle">
            <Form.Label>Titolo</Form.Label>
            <Form.Control
              type="text"
              name="title"
              value={formData.title}
              onChange={handleChange}
              required
              placeholder="Inserisci titolo"
            />
          </Form.Group>

          {/* Descrizione */}
          <Form.Group className="mb-3" controlId="formDesc">
            <Form.Label>Descrizione</Form.Label>
            <Form.Control
              as="textarea"
              rows={3}
              name="desc"
              value={formData.desc}
              onChange={handleChange}
              placeholder="Dettagli appuntamento..."
            />
          </Form.Group>

          {/* Data Inizio */}
          <Row className="mb-3">
            <Col md={6}>
              <Form.Group controlId="formDataInizio">
                <Form.Label>Data Inizio</Form.Label>
                <Form.Control
                  type="date"
                  value={moment(formData.start).format("YYYY-MM-DD")}
                  onChange={(e) => {
                    const newDate = moment(formData.start).set({
                      year: moment(e.target.value).year(),
                      month: moment(e.target.value).month(),
                      date: moment(e.target.value).date(),
                    });
                    handleDateChange("start", newDate.toDate());
                  }}
                />
              </Form.Group>
            </Col>
            <Col md={6}>
              <Form.Group controlId="formOraInizio">
                <Form.Label>Ora Inizio</Form.Label>
                <Form.Control
                  type="time"
                  value={moment(formData.start).format("HH:mm")}
                  onChange={(e) => {
                    const [hours, minutes] = e.target.value.split(":");
                    const newDate = moment(formData.start).set({
                      hour: hours,
                      minute: minutes,
                    });
                    handleDateChange("start", newDate.toDate());
                  }}
                />
              </Form.Group>
            </Col>
          </Row>

          {/* Data Fine */}
          <Row className="mb-3">
            <Col md={6}>
              <Form.Group controlId="formDataFine">
                <Form.Label>Data Fine</Form.Label>
                <Form.Control
                  type="date"
                  value={moment(formData.end).format("YYYY-MM-DD")}
                  onChange={(e) => {
                    const newDate = moment(formData.end).set({
                      year: moment(e.target.value).year(),
                      month: moment(e.target.value).month(),
                      date: moment(e.target.value).date(),
                    });
                    handleDateChange("end", newDate.toDate());
                  }}
                />
              </Form.Group>
            </Col>
            <Col md={6}>
              <Form.Group controlId="formOraFine">
                <Form.Label>Ora Fine</Form.Label>
                <Form.Control
                  type="time"
                  value={moment(formData.end).format("HH:mm")}
                  onChange={(e) => {
                    const [hours, minutes] = e.target.value.split(":");
                    const newDate = moment(formData.end).set({
                      hour: hours,
                      minute: minutes,
                    });
                    handleDateChange("end", newDate.toDate());
                  }}
                />
              </Form.Group>
            </Col>
          </Row>

          {/* Pulsanti */}
          <div className="d-flex justify-content-between">
            {currentEvent && currentEvent.id && (
              <Button variant="danger" onClick={handleDelete}>
                Elimina
              </Button>
            )}
            <Button variant="primary" type="submit">
              Salva Appuntamento
            </Button>
            <Button variant="secondary" type="button" onClick={resetForm}>
              Annulla
            </Button>
          </div>
        </Form>
      )}
    </div>
  );
};

export default CalendarioAppuntamenti;
