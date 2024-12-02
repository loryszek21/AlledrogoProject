//========================REGISTER USER========================

const User = require('../models/userModel');
const { validationResult } = require('express-validator');

exports.register = async (req, res) => {
  try {
    const { username, email, password } = req.body;

    // Basic validation
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
      return res.status(400).json({ errors: errors.array() });
    }

    // Check for existing user
    const existingUser = await User.findOne({ email });
    if (existingUser) {
      return res.status(400).json({ message: 'Email is already registered.' });
    }

    // Save user
    const newUser = new User({ username, email, password });
    await newUser.save();

    res.status(201).json({ message: 'User registered successfully!' });
  } catch (err) {
    res.status(500).json({ error: 'Server error' });
  }
};

//========================LOGIN USER========================

const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');

exports.login = async (req, res) => {
  try {
    const { email, password } = req.body;

    const user = await User.findOne({ email });
    if (!user) {
      return res.status(404).json({ message: 'User not found' });
    }

    const isMatch = await bcrypt.compare(password, user.password);
    if (!isMatch) {
      return res.status(401).json({ message: 'Invalid credentials' });
    }

    const token = jwt.sign({ userId: user._id }, process.env.JWT_SECRET, { expiresIn: '1h' });

    res.status(200).json({ token });
  } catch (err) {
    res.status(500).json({ error: 'Server error' });
  }
};

//========================PASSWORD RECOVERY========================

const crypto = require('crypto');
const sendEmail = require('../utils/sendEmail');

exports.recoverPassword = async (req, res) => {
  try {
    const { email } = req.body;

    const user = await User.findOne({ email });
    if (!user) {
      return res.status(404).json({ message: 'User not found' });
    }

    // Generate token
    const resetToken = crypto.randomBytes(32).toString('hex');
    user.resetToken = resetToken;
    await user.save();

    // Send email
    const resetLink = `${process.env.FRONTEND_URL}/reset-password/${resetToken}`;
    await sendEmail(email, 'Password Reset', `Click this link to reset your password: ${resetLink}`);

    res.status(200).json({ message: 'Password reset link sent!' });
  } catch (err) {
    res.status(500).json({ error: 'Server error' });
  }
};

//========================PASSWORD RESET========================

exports.resetPassword = async (req, res) => {
    try {
      const { token, newPassword } = req.body;
  
      const user = await User.findOne({ resetToken: token });
      if (!user) {
        return res.status(404).json({ message: 'Invalid token' });
      }
  
      user.password = newPassword;
      user.resetToken = null; // Clear token
      await user.save();
  
      res.status(200).json({ message: 'Password reset successfully' });
    } catch (err) {
      res.status(500).json({ error: 'Server error' });
    }
  };
  