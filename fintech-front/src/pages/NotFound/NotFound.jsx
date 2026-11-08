import { Link } from 'react-router-dom';

export default function NotFound() {
    return (
        <div style={{ textAlign: 'center', marginTop: '100px' }}>
            <h1>404</h1>
            <p>Ops! A página que você está procurando não existe.</p>
            <Link to="/home">Voltar para a Página Inicial</Link>
        </div>
    );
}