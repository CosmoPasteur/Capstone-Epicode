import React, { useState } from "react";
import { Form, Button } from "react-bootstrap";

const CaricaImmagineForm = ({ galleryId }) => {
  const [file, setFile] = useState(null);
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!file) {
      alert("Seleziona un file!");
      return;
    }

    try {
      const formData = new FormData();
      formData.append("file", file);
      formData.append("title", title);
      formData.append("description", description);
      formData.append("galleryId", galleryId);

      const response = await fetch("http://localhost:8080/api/images/upload", {
        method: "POST",
        body: formData,
      });

      if (!response.ok) {
        throw new Error("Errore nel caricamento immagine");
      }

      const savedImage = await response.json();
      console.log("Immagine salvata:", savedImage);
      alert("Immagine caricata con successo!");

      // Reset form
      setFile(null);
      setTitle("");
      setDescription("");
      e.target.reset();
    } catch (error) {
      console.error(error);
      alert("Errore durante il caricamento dell'immagine");
    }
  };

  return (
    <Form onSubmit={handleSubmit}>
      <Form.Group className="mb-3" controlId="formFile">
        <Form.Label>Seleziona immagine</Form.Label>
        <Form.Control type="file" accept="image/*" onChange={handleFileChange} required />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formTitle">
        <Form.Label>Titolo</Form.Label>
        <Form.Control
          type="text"
          placeholder="Titolo immagine"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          required
        />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formDescription">
        <Form.Label>Descrizione</Form.Label>
        <Form.Control
          as="textarea"
          rows={3}
          placeholder="Descrizione (opzionale)"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />
      </Form.Group>

      <Button variant="primary" type="submit">
        Carica Immagine
      </Button>
    </Form>
  );
};

export default CaricaImmagineForm;
