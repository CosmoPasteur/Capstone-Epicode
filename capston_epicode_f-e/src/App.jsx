import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { Route, Routes } from "react-router-dom";

import { AuthProvider } from "./context/AuthContext";

import TopBar from "./components/topbar/TopBar";
import MyFooter from "./components/footer/MyFooter";

import MyHome from "./pages/home/MyHome";
import MyBlog from "./pages/Blog/MyBlog";
import MyAbout from "./pages/about/MyAbout";
import MyPackages from "./pages/packages/MyPackages";
import MyPortfolio from "./pages/portfolio/MyPortfolio";
import GalleryPage from "./pages/galleryPages/GalleryPage";
import ContactForm from "./pages/contact/ContactForm";
import Login from "./pages/login/Login";
import Dashboard from "./pages/dashboard/Dashboard";

import ScrollToTop from "./components/layout/ScrollToTop";

function App() {
  return (
    <AuthProvider>
      <div>
        <TopBar />
        <ScrollToTop />
        <Routes>
          <Route path="/" element={<MyHome />} />
          <Route path="/blog" element={<MyBlog />} />
          <Route path="/about" element={<MyAbout />} />
          <Route path="/packages" element={<MyPackages />} />
          <Route path="/portfolio" element={<MyPortfolio />} />
          <Route path="/portfolio/:slug" element={<GalleryPage />} />
          <Route path="/contact" element={<ContactForm />} />
          <Route path="/login" element={<Login />} />
          <Route path="/admin" element={<Dashboard />} />
        </Routes>
        <MyFooter />
      </div>
    </AuthProvider>
  );
}

export default App;
