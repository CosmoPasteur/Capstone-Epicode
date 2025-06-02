import React, { useState, useEffect } from "react";
import { Button, Form, ListGroup } from "react-bootstrap";

const GestisciGallerie = ({ onClose }) => {
  const [galleries, setGalleries] = useState([]);
  const [newTitle, setNewTitle] = useState("");
  const [newSlug, setNewSlug] = useState("");
  const [coverFile, setCoverFile] = useState(null);
  const [backgroundFile, setBackgroundFile] = useState(null); // NEW
  const [galleryImages, setGalleryImages] = useState([]);

  const fetchGalleries = async () => {
    try {
      const res = await fetch("/api/galleries");
      if (!res.ok) throw new Error("Errore nella risposta del server");

      const data = await res.json();
      if (!data.success) throw new Error(data.message || "Errore sconosciuto");

      setGalleries(data.data);
    } catch (err) {
      console.error(err);
      alert("Errore nel recupero delle gallerie: " + err.message);
    }
  };

  useEffect(() => {
    fetchGalleries();
  }, []);

  const handleAddGallery = async (e) => {
    e.preventDefault();
    try {
      const formData = new FormData();
      formData.append("title", newTitle);
      formData.append("slug", newSlug);
      if (coverFile) formData.append("coverImage", coverFile);
      if (backgroundFile) formData.append("backgroundImage", backgroundFile);
      if (galleryImages.length > 0) {
        for (let i = 0; i < galleryImages.length; i++) {
          formData.append("images", galleryImages[i]);
        }
      }

      const res = await fetch("http://localhost:8080/api/galleries/create-with-images", {
        method: "POST",
        body: formData,
      });

      const resText = await res.text();
      if (!res.ok) throw new Error("Errore nella creazione della galleria: " + resText);

      setNewTitle("");
      setNewSlug("");
      setCoverFile(null);
      setBackgroundFile(null);
      setGalleryImages([]);
      fetchGalleries();
    } catch (err) {
      console.error("Dettagli errore:", err);
      alert("Errore nella creazione della galleria: " + err.message);
    }
  };

  const handleDeleteGallery = async (id) => {
    if (!window.confirm("Sei sicuro di voler eliminare questa galleria?")) return;
    try {
      const res = await fetch(`/api/galleries/${id}`, {
        method: "DELETE",
      });
      if (!res.ok) throw new Error("Errore nell'eliminazione della galleria");
      fetchGalleries();
    } catch (err) {
      console.error(err);
      alert("Errore nell'eliminazione della galleria");
    }
  };

  return (
    <div>
      <h5>Crea nuova galleria</h5>
      <Form onSubmit={handleAddGallery} className="mb-3">
        <Form.Group>
          <Form.Label>Titolo</Form.Label>
          <Form.Control type="text" value={newTitle} onChange={(e) => setNewTitle(e.target.value)} required />
        </Form.Group>
        <Form.Group>
          <Form.Label>Slug</Form.Label>
          <Form.Control type="text" value={newSlug} onChange={(e) => setNewSlug(e.target.value)} required />
        </Form.Group>
        <Form.Group>
          <Form.Label>Immagine Copertina (file)</Form.Label>
          <Form.Control type="file" accept="image/*" onChange={(e) => setCoverFile(e.target.files[0])} />
        </Form.Group>
        <Form.Group>
          <Form.Label>Immagine Background (file)</Form.Label> {/* NEW */}
          <Form.Control type="file" accept="image/*" onChange={(e) => setBackgroundFile(e.target.files[0])} />
        </Form.Group>
        <Form.Group>
          <Form.Label>Immagini Galleria (multipli)</Form.Label>
          <Form.Control type="file" multiple accept="image/*" onChange={(e) => setGalleryImages([...e.target.files])} />
        </Form.Group>
        <Button type="submit" className="mt-2">
          Aggiungi Galleria
        </Button>
      </Form>

      <h5>Gallerie Esistenti</h5>
      <ListGroup>
        {galleries.map((gallery) => (
          <ListGroup.Item key={gallery.id} className="d-flex justify-content-between align-items-center">
            <div>
              <strong>{gallery.title}</strong> ({gallery.slug})
            </div>
            <Button variant="danger" size="sm" onClick={() => handleDeleteGallery(gallery.id)}>
              Elimina
            </Button>
          </ListGroup.Item>
        ))}
      </ListGroup>

      <div className="mt-3 text-end">
        <Button variant="secondary" onClick={onClose}>
          Chiudi
        </Button>
      </div>
    </div>
  );
};

export default GestisciGallerie;
