import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './pages/Login/Login';
import Home from './pages/Home/Home';
import Clientes from './pages/Clientes/Clientes';
import Contas from './pages/Contas/Contas';
import Transacoes from './pages/Transacoes/Transacoes';
import NotFound from './pages/NotFound/NotFound';

export function AppRoutes() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/home" element={<Home />} />
                <Route path="/clientes" element={<Clientes />} />
                <Route path="/contas" element={<Contas />} />
                <Route path="/transacoes" element={<Transacoes />} />
                <Route path="*" element={<NotFound />} />
            </Routes>
        </BrowserRouter>
    );
}