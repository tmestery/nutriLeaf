import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'

import NotFoundPage from './components/NotFoundPage'
import HomePage from './components/HomePage'
import LoginPage from './components/LoginPage'
import SignupPage from './components/SignupPage'

function App() {
  return (
    <Router>
      <Routes>
        <Route path='/'>
          <Route index element={<HomePage />} />
        </Route>
        <Route path='/auth/'>
          <Route path='login' element={<LoginPage />} />
          <Route path='signup' element={<SignupPage />} />
        </Route>
        <Route path='*' element={<NotFoundPage />} />
      </Routes>
    </Router>
  )
}

export default App
