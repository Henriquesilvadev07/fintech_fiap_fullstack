import { Link } from 'react-router-dom';

export default function Home() {
    return (
        <div style={{ padding: '40px', textAlign: 'center' }}>
            <h1>Bem-vindo à Fintech FIAP</h1>
            <p>Gerencie seus dados de forma rápida e segura.</p>
            <div style={{ display: 'flex', justifyContent: 'center', gap: '20px', marginTop: '30px' }}>
                <Link to="/clientes"><button>Gerenciar Clientes</button></Link>
                <Link to="/contas"><button>Gerenciar Contas</button></Link>
                <Link to="/transacoes"><button>Gerenciar Transações</button></Link>
            </div>
        </div>
    );
}