import { useEffect } from "react";
import { useLocation } from "react-router-dom";

const ScrollToTop = () => {
  const { pathname } = useLocation();

  useEffect(() => {
    window.scrollTo(0, 0);
  }, [pathname]);

  return null;
};

export default ScrollToTop;

// Quando usando React (e React Router) navighi tra le pagine, se il contenuto della nuova pagina è più lungo dello schermo, il browser mantiene la posizione di scroll precedente, e questo può dare l’impressione che la nuova pagina parta “da sotto”.

// Per risolvere questo problema, devi resettare lo scroll in alto ogni volta che cambi pagina.
