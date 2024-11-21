const verifyCaptcha = async (token) => {
    const response = await fetch(`https://www.google.com/recaptcha/api/siteverify`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      body: `secret=${process.env.CAPTCHA_SECRET}&response=${token}`,
    });
    return await response.json();
  };
  
  module.exports = async (req, res, next) => {
    const { captchaToken } = req.body;
    const result = await verifyCaptcha(captchaToken);
  
    if (!result.success) {
      return res.status(400).json({ message: 'CAPTCHA verification failed' });
    }
  
    next();
  };
  