import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../../services/api';

export default function Login() {
    const [isRegistering, setIsRegistering] = useState(false);
    const [login, setLogin] = useState('');
    const [senha, setSenha] = useState('');
    const navigate = useNavigate();

    async function handleSubmit(e) {
        e.preventDefault();
        try {
            if (isRegistering) {
                // Rota de Registro (ajuste para /auth/register ou /auth/registro conforme seu Controller Java)
                await api.post('/auth/registro', { login, senha });
                alert('Usuário cadastrado com sucesso! Agora faça o login.');
                setIsRegistering(false); // Volta para a tela de login
            } else {
                // Rota de Login
                const res = await api.post('/auth/login', { login, senha });
                localStorage.setItem('token', res.data.token || res.data);
                navigate('/home');
            }
        } catch (error) {
            alert(isRegistering ? 'Erro ao registrar usuário.' : 'Erro no login. Verifique suas credenciais.');
        }
    }

    return (
        <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', marginTop: '80px' }}>
            <h2>{isRegistering ? 'Criar Nova Conta' : 'Login Fintech FIAP'}</h2>

            <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', width: '300px', gap: '10px', marginTop: '20px' }}>
                <input
                    placeholder="E-mail / Login"
                    value={login}
                    onChange={e => setLogin(e.target.value)}
                    required
                />
                <input
                    type="password"
                    placeholder="Senha"
                    value={senha}
                    onChange={e => setSenha(e.target.value)}
                    required
                />
                <button type="submit">
                    {isRegistering ? 'Cadastrar' : 'Entrar'}
                </button>
            </form>

            <button
                onClick={() => setIsRegistering(!isRegistering)}
                style={{ background: 'none', border: 'none', color: 'blue', cursor: 'pointer', marginTop: '15px', textDecoration: 'underline' }}
            >
                {isRegistering ? 'Já tem uma conta? Faça login' : 'Não tem conta? Cadastre-se'}
            </button>
        </div>
    );
}