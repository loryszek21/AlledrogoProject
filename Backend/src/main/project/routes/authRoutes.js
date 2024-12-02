const express = require('express');
const { register, login, recoverPassword, resetPassword } = require('../controllers/authController');
const captchaMiddleware = require('../middlewares/captchaMiddleware');
const router = express.Router();

router.post('/register', captchaMiddleware, register);
router.post('/login', captchaMiddleware, login);
router.post('/recover', recoverPassword);
router.post('/reset', resetPassword);

module.exports = router;
