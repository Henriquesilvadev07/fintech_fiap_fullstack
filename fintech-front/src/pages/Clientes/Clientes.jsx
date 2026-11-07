import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import api from '../../services/api';

export default function Clientes() {
    const [clientes, setClientes] = useState([]);
    const [nome, setNome] = useState('');
    const [email, setEmail] = useState('');
    const [cpf, setCpf] = useState('');

    useEffect(() => { carregarClientes(); }, []);

    async function carregarClientes() {
        try {
            const res = await api.get('/clientes');
            setClientes(res.data);
        } catch (e) { console.error(e); }
    }

    async function salvar(e) {
        e.preventDefault();
        try {
            await api.post('/clientes', { nmCliente: nome, dsEmail: email, dsCpf: cpf });
            alert('Cliente cadastrado!');
            setNome(''); setEmail(''); setCpf('');
            carregarClientes();
        } catch (e) { alert('Erro ao cadastrar'); }
    }

    return (
        <div style={{ padding: '20px' }}>
            <Link to="/home">← Voltar para Home</Link>
            <h2>Módulo de Clientes</h2>
            <form onSubmit={salvar} style={{ display: 'flex', gap: '10px', marginBottom: '20px' }}>
                <input placeholder="Nome" value={nome} onChange={e => setNome(e.target.value)} required />
                <input placeholder="E-mail" value={email} onChange={e => setEmail(e.target.value)} required />
                <input placeholder="CPF" value={cpf} onChange={e => setCpf(e.target.value)} required />
                <button type="submit">Adicionar</button>
            </form>
            <ul>
                {clientes.map(c => <li key={c.id_cliente || c.id}>{c.nm_cliente || c.nmCliente} - {c.ds_email || c.dsEmail}</li>)}
            </ul>
        </div>
    );
}