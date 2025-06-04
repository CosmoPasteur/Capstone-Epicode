import "./GalleryPage.css";
import { Link, useParams } from "react-router-dom";
import { Modal, Container } from "react-bootstrap";
import { useState, useEffect } from "react";
import axios from "axios";

const GalleryPage = () => {
  const { slug } = useParams();
  const [gallery, setGallery] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const [showModal, setShowModal] = useState(false);
  const [selectedImage, setSelectedImage] = useState(null);

  const handleImageClick = (image) => {
    setSelectedImage(image);
    setShowModal(true);
  };

  useEffect(() => {
    const fetchGallery = async () => {
      try {
        const res = await axios.get(`http://localhost:8080/api/galleries/slug/${slug}`);
        setGallery(res.data.data);
      } catch {
        setError("Galleria non trovata o errore di caricamento");
      } finally {
        setLoading(false);
      }
    };

    fetchGallery();
  }, [slug]);

  if (loading) return <h2 className="text-center my-5">Caricamento...</h2>;
  if (error) return <h2 className="text-center my-5">{error}</h2>;
  if (!gallery) return <h2 className="text-center my-5">Galleria non trovata</h2>;
  return (
    <>
      <Container fluid>
        <img
          src={
            gallery.coverImage
              ? `http://localhost:8080${gallery.coverImage}`
              : gallery.backgroundImage
              ? `http://localhost:8080${gallery.backgroundImage}`
              : "/placeholder.jpg"
          }
          alt={gallery.title}
          className="sfondoAlbum"
          onError={(e) => {
            console.error("Errore nel caricare l'immagine:", e.target.src);
            e.target.src = "/placeholder.jpg";
          }}
        />
        <h1 className="titolo-sopra-sfondo">{gallery.title}</h1>
        <hr />
        <hr />
        <div className="m-5">
          {gallery.images && gallery.images.length > 0 ? (
            <div className="row">
              {gallery.images.map((image) => (
                <div className="col-12 col-sm-6 col-md-4 mb-4" key={image.id}>
                  <div className="card mb-4 image-card">
                    <img
                      src={`http://localhost:8080${image.url}`}
                      alt={image.title}
                      className="card-img-top card-image-gallery"
                      style={{ cursor: "pointer" }}
                      onClick={() => handleImageClick(image)}
                    />
                    {/* <div className="card-body image-details">
                      <h5 className="card-title">{image.title}</h5>
                      <p className="card-text">{image.description}</p>
                      <small>Location: {image.location}</small>
                    </div> */}
                  </div>
                </div>
              ))}
            </div>
          ) : (
            <p>Nessuna immagine disponibile per questa galleria.</p>
          )}
        </div>
      </Container>

      <Container fluid className="py-1 text-center dark my-3">
        <span className="px-3 h4">PRENOTA ORA 2025 - 2026</span>
        <Link to="/contact">
          <button className="button-info px-3">CONTATTO</button>
        </Link>
      </Container>

      <Modal show={showModal} onHide={() => setShowModal(false)} centered size="xl" className="lightbox-modal">
        <Modal.Body className="p-0 bg-black text-center position-relative">
          {selectedImage && (
            <>
              <img
                src={`http://localhost:8080${selectedImage.url}`}
                alt={selectedImage.title}
                className="img-fluid"
                style={{ maxHeight: "90vh", objectFit: "contain" }}
              />
              <div className="text-light mt-2">
                <h5>{selectedImage.title}</h5>
                <p className="mb-1">{selectedImage.description}</p>
                <small>{selectedImage.location}</small>
              </div>
            </>
          )}
        </Modal.Body>
      </Modal>
    </>
  );
};

export default GalleryPage;
