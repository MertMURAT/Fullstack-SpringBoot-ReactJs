import React from 'react'

const Header: React.FC = () => {
  return (
    <header className='bg-indigo-950 text-white py-3'>
        <div className='container mx-auto flex justify-between items-center px-4'>
            <h1 className='text-3xl font-bold'>imageLite</h1>
        </div>
    </header>
  )
}

export default Header