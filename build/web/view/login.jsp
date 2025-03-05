<%-- 
    Document   : login
    Created on : Feb 26, 2025, 10:18:49 PM
    Author     : A
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styleLogin.css"/>
    </head>
    <body>
        <div class="container">
            <div class="form-container">
                
                <div class="signin-signup">
                    
                    <form action="${pageContext.request.contextPath}/Login" method="post" class="sign-in-form">
                        <% String error = (String) request.getAttribute("errorMessage"); %>
                        <h2 class="title">Sign in</h2>
                        <div class="input-field">
                            <i class="bi bi-person-circle"></i>
                            <input class="input-text" type="text" placeholder="Username" name="user">
                        </div>

                        <div class="input-field">
                            <i class="bi bi-key-fill"></i>
                            <input class="input-text" type="password" placeholder="Password" name="password">
                        </div>

                        <input type="submit" value="Login" class="btn1 solid">
                        <p><a style="font-size:13px" href="forgot-password.jsp">Forgot Password?</a></p>

<!--                        <p class="social-text">Or Sign in with social platforms</p>
                        <div class="social-media">
                            <a href="#" class="social-icon">
                                <i class="bi bi-facebook"></i>
                            </a>
                            <a href="#" class="social-icon">
                                <i class="bi bi-google"></i>
                            </a>
                            <a href="#" class="social-icon">
                                <i class="bi bi-linkedin"></i>
                            </a>
                        </div>-->
                    </form>

                    <form action="${pageContext.request.contextPath}/SendOtpServlet" method="post" class="sign-up-form">
                        <h2 class="title">Sign up</h2>
                        <div class="input-field">
                            <i class="bi bi-person-circle"></i>
                            <input class="input-text" type="text" placeholder="Username" name="user_su" required>
                        </div>

                        <div class="input-field">
                            <i class="bi bi-envelope"></i>
                            <input class="input-text" type="email" placeholder="Email" name="email" required>
                        </div>

                        <div class="input-field">
                            <i class="bi bi-key-fill"></i>
                            <input class="input-text" type="password" placeholder="Password" name="password_su" required>
                        </div>

                        <input type="submit" value="Send OTP" class="btn1 solid">
                    </form>
                </div>
            </div>

            <div class="panels-container">
                <div class="panel left-panel">
                    <div class="content">
                        <h3>Welcome Back, Trendsetter!</h3>
                        <p>Ready to join the spotlight? Sign up now to access exclusive fashion workshops, modeling opportunities, and runway shows!</p>
                        <button class="btn1 transparent" id="sign-up-btn">Sign up</button>
                    </div>

                </div>

                <div class="panel right-panel">
                    <div class="content">
                        <h3>Ready to Shine?</h3>
                        <p>Welcome back to the FPTU Fashion & Model Club! Log in to explore upcoming events, workshops, and connect with other fashion enthusiasts.</p>
                        <button class="btn1 transparent" id="sign-in-btn">Sign in</button>
                    </div>

                </div>
            </div>
        </div>

        <script>
    const sign_in_btn = document.querySelector("#sign-in-btn");
    const sign_up_btn = document.querySelector("#sign-up-btn");
    const container = document.querySelector(".container");

    // Lấy giá trị lỗi từ request attribute
    var error = "<%= error %>";

    // Kiểm tra nếu error không null và không rỗng, và trang đã được tải lại
    if (error && error.trim() !== "" && error !== "null" && localStorage.getItem("isPageReloaded") === "true") {
        alert(error);
    }

    // Đặt cờ isPageReloaded thành true sau khi trang được tải
    localStorage.setItem("isPageReloaded", "true");

    sign_up_btn.addEventListener('click', (e) => {
        container.classList.add("sign-up-mode");
    });

    sign_in_btn.addEventListener('click', (e) => {
        container.classList.remove("sign-up-mode");
    });
</script>


    </body>
</html>