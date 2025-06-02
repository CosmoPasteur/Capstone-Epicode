import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useContext } from "react";
import AuthContext from "../../context/AuthContext";
import { Container, Row, Col, Form, Button, Card } from "react-bootstrap";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();
  const { login } = useContext(AuthContext);

  const handleLogin = (e) => {
    e.preventDefault();

    const validUsername = "admin";
    const validPassword = "password123";

    if (username === validUsername && password === validPassword) {
      login();
      navigate("/admin");
    } else {
      setError("Credenziali errate. Riprova.");
    }
  };

  return (
    <Container className="d-flex align-items-center justify-content-center min-vh-100">
      <Row className="w-100 mt-5">
        <Col md={{ span: 6, offset: 3 }}>
          <Card className="shadow-sm p-4">
            <h2 className="text-center mb-4">Accedi all'Area Admin</h2>
            <Form onSubmit={handleLogin}>
              <Form.Group controlId="username" className="mb-3">
                <Form.Label>Username</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="Inserisci username"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                  required
                />
              </Form.Group>

              <Form.Group controlId="password" className="mb-3">
                <Form.Label>Password</Form.Label>
                <Form.Control
                  type="password"
                  placeholder="Inserisci password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                />
              </Form.Group>

              {error && <p className="text-danger text-center">{error}</p>}

              <Button variant="dark" type="submit" className="w-100 mt-3">
                Accedi
              </Button>
            </Form>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default Login;
