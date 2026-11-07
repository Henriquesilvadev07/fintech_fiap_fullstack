import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import api from '../../services/api';

export default function Contas() {
    const [contas, setContas] = useState([]);
    const [agencia, setAgencia] = useState('');
    const [numero, setNumero] = useState('');
    const [idCliente, setIdCliente] = useState('');

    useEffect(() => { carregarContas(); }, []);

    async function carregarContas() {
        try {
            const res = await api.get('/contas');
            setContas(res.data);
        } catch (e) { console.error(e); }
    }

    async function salvar(e) {
        e.preventDefault();
        try {
            await api.post('/contas', { nrAgencia: agencia, nrConta: numero, cliente: { id: idCliente } });
            alert('Conta cadastrada!');
            setAgencia(''); setNumero(''); setIdCliente('');
            carregarContas();
        } catch (e) { alert('Erro ao cadastrar conta'); }
    }

    return (
        <div style={{ padding: '20px' }}>
            <Link to="/home">← Voltar para Home</Link>
            <h2>Módulo de Contas</h2>
            <form onSubmit={salvar} style={{ display: 'flex', gap: '10px', marginBottom: '20px' }}>
                <input placeholder="Agência" value={agencia} onChange={e => setAgencia(e.target.value)} required />
                <input placeholder="Número da Conta" value={numero} onChange={e => setNumero(e.target.value)} required />
                <input placeholder="ID do Cliente" value={idCliente} onChange={e => setIdCliente(e.target.value)} required />
                <button type="submit">Adicionar Conta</button>
            </form>
            <ul>
                {contas.map(ct => <li key={ct.id_conta || ct.id}>Ag: {ct.nr_agencia || ct.nrAgencia} | Conta: {ct.nr_conta || ct.nrConta}</li>)}
            </ul>
        </div>
    );
}