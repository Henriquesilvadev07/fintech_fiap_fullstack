import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import api from '../../services/api';

export default function Transacoes() {
    const [transacoes, setTransacoes] = useState([]);
    const [tipo, setTipo] = useState('');
    const [valor, setValor] = useState('');
    const [idConta, setIdConta] = useState('');

    useEffect(() => { carregarTransacoes(); }, []);

    async function carregarTransacoes() {
        try {
            const res = await api.get('/transacoes');
            setTransacoes(res.data);
        } catch (e) { console.error(e); }
    }

    async function salvar(e) {
        e.preventDefault();
        try {
            await api.post('/transacoes', { tpTransacao: tipo, vlTransacao: Number(valor), conta: { id: idConta } });
            alert('Transação registrada!');
            setTipo(''); setValor(''); setIdConta('');
            carregarTransacoes();
        } catch (e) { alert('Erro ao registrar transação'); }
    }

    return (
        <div style={{ padding: '20px' }}>
            <Link to="/home">← Voltar para Home</Link>
            <h2>Módulo de Transações</h2>
            <form onSubmit={salvar} style={{ display: 'flex', gap: '10px', marginBottom: '20px' }}>
                <input placeholder="Tipo (ex: DEPOSITO)" value={tipo} onChange={e => setTipo(e.target.value)} required />
                <input type="number" placeholder="Valor" value={valor} onChange={e => setValor(e.target.value)} required />
                <input placeholder="ID da Conta" value={idConta} onChange={e => setIdConta(e.target.value)} required />
                <button type="submit">Registrar</button>
            </form>
            <ul>
                {transacoes.map(t => <li key={t.id_transacao || t.id}>{t.tp_transacao || t.tpTransacao}: R$ {t.vl_transacao || t.vlTransacao}</li>)}
            </ul>
        </div>
    );
}