import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './pages/Login/Login';
import Register from './pages/Register/Register';
import Home from './pages/Home/Home';
import NoPage from './pages/Nopage/NoPage';
import Layout from './pages/Layout/Layout';

import './App.css';
import './style/flex.css'

function App() {
  return (
    <Layout>
    <BrowserRouter>
      <Routes>
          <Route path="/" element={<Login />} exact />
          <Route path="/login" element={<Login />} />
          <Route path="/home" element={<Home />} />
          <Route path="/register" element={<Register />} />
          <Route path="*" element={<NoPage />} />
      </Routes>
    </BrowserRouter>
    </Layout>
  );
}

export default App;
