# 🔐 Authentication & Card Manager App

An Android app (Kotlin) that demonstrates a **secure local authentication system** combined with a simple **bank card capture & transaction manager**.  

The app is split into two main modules:  
1. **Authentication** → User Registration, Login, Forgot & Reset Password  
2. **Card Manager** → Bank selection, card image capture, and amount confirmation  

---

## 📱 Screenshots

<p align="center">
  <img src="https://github.com/user-attachments/assets/eaa5d98d-9374-4ff5-9400-9172682d0f83" alt="Login Screen"  width="220"/>
  <img src="https://github.com/user-attachments/assets/11fb6613-d401-4007-9e04-0e5f7a00274d" alt="Register Screen"  width="220"/>
  <img src="https://github.com/user-attachments/assets/bc1c6349-5895-4969-bec5-fb8580d28bf8" alt="Forgot Password Screen" width="220"/>
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/e4b13179-fae5-4df2-b077-3127dc03a83e" alt="Reset Password Screen" width="220"/>
  <img src="https://github.com/user-attachments/assets/4e76f9a4-7baf-473a-957f-4617b90b9b24" alt="Reset Password Success" width="220"/>
  <img src="https://github.com/user-attachments/assets/708116ca-ded1-47f3-bbe5-90a032c43c0f" alt="Card Processing Screen" width="220"/>
  <img src="https://github.com/user-attachments/assets/2c602a77-66ce-4175-8e2b-63195d31577d" alt="Card Confirmation Screen" width="220"/>
</p>

---

## ✨ Features

### 🔑 Authentication
- **Register User**
  - Full name, email, phone, password, confirm password.
  - Email uniqueness validation.
  - Password rules (6+ characters, match confirmation).
- **Login**
  - Validate email + password.
  - Display error messages for invalid credentials.
- **Forgot Password**
  - Generate secure **reset token** valid for 1 hour.
  - Redirect user to reset password screen.
- **Reset Password**
  - Enter new password & confirm.
  - Password is updated in local storage if token is valid.
- **Persistence**
  - Users are saved in a local JSON file (`users.json`) inside app’s internal storage.
  - Powered by **Gson** for JSON parsing.

### 💳 Card Manager
- **Bank Selection**
  - Choose from multiple Jordanian banks (spinner dropdown).
- **Capture Card Images**
  - Take pictures of front & back card sides with camera.
- **Process Card**
  - Display bank + captured card images on confirmation screen.
- **Confirm Transaction**
  - Enter deduction amount → app shows success Toast.

---

## 🛠 Tech Stack
- **Language:** Kotlin  
- **UI:** ViewBinding, ConstraintLayout  
- **Data Storage:** Local JSON (`users.json`) with Gson  
- **Camera API:** `MediaStore.ACTION_IMAGE_CAPTURE`  
- **Architecture:** Activity-based navigation  

---

## 📂 Project Structure
```plaintext
app/
 ├─ src/main/java/com/example/m5/
 │   ├─ AuthHelper.kt              # Core authentication helper
 │   ├─ User.kt                    # Data model for users
 │   ├─ LoginActivity.kt           # Login screen
 │   ├─ RegistrationActivity.kt    # Registration screen
 │   ├─ ForgotPasswordActivity.kt  # Request reset link
 │   ├─ ResetPasswordActivity.kt   # Reset password with token
 │   ├─ MainActivity.kt            # Bank card capture
 │   ├─ card_info.kt               # Card info + transaction confirmation
 │
 ├─ res/layout/                    # XML layouts
 ├─ res/drawable/                  # Assets (logos, backgrounds)
 ├─ res/values/                    # Strings, colors, styles
 └─ README.md
