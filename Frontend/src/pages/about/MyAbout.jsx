import "./MyAbout.css";
import { Card, Carousel, Col, Container, Row } from "react-bootstrap";
import { Link } from "react-router-dom";

const MyAbout = function () {
  return (
    <>
      <section className="py-5">
        <Container className="mb-5">
          <Row className="align-items-center">
            <Col xs={12} md={12} lg={6} className="mb-4 mb-md-0">
              <Container>
                <h3 className="bg-dark text-white p-3 m-3 ">
                  PER TRASFORMARE AUTENTICHE STORIE D'AMORE IN OPERE D'ARTE CHE DURANO PER SEMPRE
                </h3>
                <Carousel controls={false} indicators={false} fade interval={3500}>
                  <Carousel.Item>
                    <img
                      src="https://images.unsplash.com/photo-1549981832-2ba2ee913334?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                      alt="Foto profilo"
                      className="card-image-about "
                    />
                  </Carousel.Item>
                  <Carousel.Item>
                    <img
                      src="https://images.unsplash.com/photo-1512813498716-3e640fed3f39?q=80&w=1924&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                      alt="Foto profilo"
                      className="card-image-about "
                    />
                  </Carousel.Item>
                  <Carousel.Item>
                    <img
                      src="https://images.unsplash.com/photo-1611093793031-46db535bde90?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                      alt="Foto profilo"
                      className="card-image-about "
                    />
                  </Carousel.Item>
                </Carousel>
              </Container>
            </Col>
            <Col xs={12} md={12} lg={6} className="">
              <p>
                Ciao sono Bastone Francesca,la ragazza dietro la macchina fotografica, e la mia missione è catturare la
                magia della vostra storia d'amore attraverso il mio obiettivo. Il mio viaggio nella fotografia è
                iniziato con la pellicola, dove ogni scatto racchiudeva un senso di magia e attesa. Col passare del
                tempo, sono passata al digitale, mantenendo viva nel mio lavoro quella nostalgica qualità
                cinematografica. Si tratta di preservare quei momenti senza tempo che ti trasportano indietro nel tempo.
                Per me, si tratta di quei momenti autentici: gli sguardi rubati, gli abbracci sinceri, i piccoli
                dettagli che rendono il tuo giorno unico. È lì che avviene la magia.
              </p>
              <p>
                Al di fuori della fotografia, probabilmente mi troverete con il naso immerso in un libro, a passare
                troppo tempo a costruire un nuovo set di Lego comprato per capriccio, o a fare volontariato nel mio
                rifugio per animali preferito. E ovviamente a passare il tempo con i miei cuccioli Tala e Kota. Sono
                cresciuto vicino alla splendida Boulder, in Colorado, e da bambino ho trascorso la maggior parte del
                tempo a cercare nuovi posti per fare escursioni con la mia famiglia. Qualche anno fa, mi sono trasferito
                sulla costa occidentale a San Clemente, in California, dove mi sono innamorato di tutto ciò che l'oceano
                ha da offrire. Alla fine sono andato a Portland, dove mi sono innamorato di tutto ciò che il Pacifico
                nord-occidentale ha da offrire. Il mio amore per tutti e tre i luoghi mi ha portato a dividere il mio
                tempo tra tutti e tre. Oggi mi troverete a Denver, in Colorado, nella Contea di Orange, in California, e
                a Portland, in Oregon, ma la mia passione principale è esplorare nuovi luoghi, quindi sono disponibile a
                catturare la vostra storia d'amore in tutto il mondo! Organizziamo una giornata unica nella destinazione
                dei vostri sogni.
              </p>
              <p>
                Che stiate pianificando un matrimonio in grande stile vicino a casa o una fuga d'amore avventurosa
                dall'altra parte del mondo, sono qui per catturare l'essenza della vostra storia d'amore con calore,
                autenticità e un tocco di nostalgia. Intraprendiamo insieme questo viaggio, creando un'opera d'arte
                dalla vostra storia che sarà custodita per tutta la vita. Perché per me non si tratta solo di scattare
                foto, ma di catturare i momenti che rendono la vostra storia d'amore davvero indimenticabile.
              </p>
              <Link to="/contact">
                <button className="button-info px-2 my-3">Facciamo due chiacchiere</button>
              </Link>
            </Col>
          </Row>
        </Container>
        <hr />
        {/*    CARD SX - FUGHE D'AMORE   */}
        <Container className="p-5">
          <Row className="g-4 justify-content-center">
            <Col xs={12} sm={12} md={4} lg={4}>
              <Card className="custom-card border-0 px-2">
                <div className="card-content">
                  <Card.Img
                    variant="top"
                    src="https://images.unsplash.com/photo-1495345679747-53991aedf9c2?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                    className="card-image"
                  />
                </div>
              </Card>
            </Col>
            {/*    CARD CENTRALE - COPPIE   */}
            <Col xs={12} sm={12} md={4} lg={4}>
              <Card className="custom-card border-0 px-2">
                <div className="card-content">
                  <Card.Img
                    variant="top"
                    src="https://images.unsplash.com/photo-1543899161-d044e847c40f?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                    className="card-image"
                  />
                </div>
              </Card>
            </Col>
            {/*    CARD DESTRA - MATRIMONI   */}
            <Col xs={12} sm={12} md={4} lg={4}>
              <Card className="custom-card border-0 px-2">
                <div className="card-content">
                  <Card.Img
                    variant="top"
                    src="https://images.unsplash.com/photo-1532932371123-928bc0091ec0?q=80&w=1978&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                    className="card-image"
                  />
                </div>
              </Card>
            </Col>
          </Row>
        </Container>
      </section>
    </>
  );
};

export default MyAbout;
